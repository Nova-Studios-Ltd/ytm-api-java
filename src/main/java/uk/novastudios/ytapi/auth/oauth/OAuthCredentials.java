package uk.novastudios.ytapi.auth.oauth;

import com.google.gson.Gson;
import uk.novastudios.ytapi.Constants;
import uk.novastudios.ytapi.TestPayload;
import uk.novastudios.ytapi.auth.oauth.errors.BadOAuthClient;
import uk.novastudios.ytapi.auth.oauth.errors.ErrorResponse;
import uk.novastudios.ytapi.auth.oauth.errors.UnauthorizedOAuthClient;
import uk.novastudios.ytapi.auth.oauth.payloads.CodePayload;
import uk.novastudios.ytapi.auth.oauth.payloads.RefreshPayload;
import uk.novastudios.ytapi.auth.oauth.payloads.TokenPayload;
import uk.novastudios.ytapi.auth.oauth.tokens.AuthCode;
import uk.novastudios.ytapi.auth.oauth.tokens.BaseToken;
import uk.novastudios.ytapi.auth.oauth.tokens.RefreshableToken;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class OAuthCredentials extends Credentials {

    public OAuthCredentials(String clientID, String clientSecret) {
        super();

        this.ClientID = clientID;
        this.ClientSecret = clientSecret;
    }

    @Override
    public AuthCode getCode() throws Exception {
        return sendRequest(Constants.OAUTH_CODE_URL, new CodePayload(Constants.OAUTH_SCOPE, this.ClientID), AuthCode.class);
    }

    @Override
    public RefreshableToken tokenFromCode(String deviceCode) throws Exception {
        return sendRequest(Constants.OAUTH_TOKEN_URL, new TokenPayload(this.ClientSecret, "http://oauth.net/grant_type/device/1.0", deviceCode), RefreshableToken.class);
    }

    @Override
    public BaseToken refreshToken(String refreshToken) throws Exception {
        return sendRequest(Constants.OAUTH_TOKEN_URL, new RefreshPayload(this.ClientSecret, refreshToken), BaseToken.class);
    }

    @SuppressWarnings("unchecked")
    private <T> T sendRequest(String url, Object data, Class<T> tClass) throws Exception {
        HttpsURLConnection conn = (HttpsURLConnection) new URI(url).toURL().openConnection();
        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("User-Agent", Constants.OAUTH_USER_AGENT);

        conn.setDoOutput(true);
        conn.setDoInput(true);

        String d = new Gson().toJson(data);
        System.out.println(d);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(d);
        wr.flush();
        wr.close();

        if (conn.getResponseCode() == 401) {
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getErrorStream(), StandardCharsets.UTF_8));
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            ErrorResponse resp = new Gson().fromJson(response.toString(), ErrorResponse.class);

            if (resp.error.equals(ErrorResponse.UNAUTHORIZED_CLIENT)) {
                throw new UnauthorizedOAuthClient("Token refresh error. Most likely client/token mismatch");
            }
            else if (resp.error.equals(ErrorResponse.INVALID_CLIENT)) {
                throw new BadOAuthClient("OAuth client feature. Most likely client_id and client_secret mismatch or YoutubeData API is not enabled.");
            }
            else {
                throw new Exception(String.format("Oauth request error. status_code: %s, url: %s, content: %s", conn.getResponseCode(), url, data));
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
        StringBuilder response = new StringBuilder();
        String responseLine = null;
        while ((responseLine = br.readLine()) != null) {
            response.append(responseLine.trim());
        }

        return new Gson().fromJson(response.toString(), tClass);
    }
}
