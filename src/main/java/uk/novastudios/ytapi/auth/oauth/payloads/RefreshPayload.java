package uk.novastudios.ytapi.auth.oauth.payloads;

public class RefreshPayload {
    public String client_secret;
    public String grant_type;
    public String refresh_token;

    public RefreshPayload(String client_secret, String refresh_token) {
        this.client_secret = client_secret;
        this.grant_type = "refresh_token";
        this.refresh_token = refresh_token;
    }
}
