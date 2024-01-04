package uk.novastudios.ytapi.auth.oauth;

import uk.novastudios.ytapi.auth.oauth.tokens.AuthCode;
import uk.novastudios.ytapi.auth.oauth.tokens.BaseToken;
import uk.novastudios.ytapi.auth.oauth.tokens.RefreshableToken;

import java.io.IOException;
import java.net.*;

public class OAuthCredentials extends Credentials {

    public OAuthCredentials(String clientID, String clientSecret) {
        super();

        this.ClientID = clientID;
        this.ClientSecret = clientSecret;
    }

    @Override
    public AuthCode GetCode() {
        return null;
    }

    @Override
    public RefreshableToken TokenFromCode(String deviceCode) {
        return null;
    }

    @Override
    public BaseToken RefreshToken(String refreshToken) {
        return null;
    }

    private String SendRequest(String url, Object data) throws IOException, URISyntaxException {
        HttpURLConnection conn = (HttpURLConnection) new URI(url).toURL().openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);

        return"";
    }
}
