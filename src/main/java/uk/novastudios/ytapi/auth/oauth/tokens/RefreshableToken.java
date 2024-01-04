package uk.novastudios.ytapi.auth.oauth.tokens;

public class RefreshableToken extends BaseToken {
    public int expires_at;
    public String refresh_token;
}
