package uk.novastudios.ytapi.auth.oauth.tokens;

public class Token extends RefreshableToken {
    public String scope;
    public String token_type;

    public String access_token;
    public String refresh_token;
    public int expires_at;
    public int expires_in;

    public RefreshableToken asRefToken() {
        return this;
    }

    public String asAuth() {
        return String.format("%s %s", this.token_type, this.access_token);
    }

    public boolean isExpiring() {
        return this.expires_in < 60;
    }
}
