package uk.novastudios.ytapi.auth.oauth.payloads;

public class TokenPayload {
    public String client_secret;
    public String grant_type;
    public String code;

    public TokenPayload(String client_secret, String grant_type, String code) {
        this.client_secret = client_secret;
        this.grant_type = grant_type;
        this.code = code;
    }
}
