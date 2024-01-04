package uk.novastudios.ytapi.auth.oauth.tokens;

public class BaseToken {
    public String access_token;
    public int expires_in;
    public final String scope = "https://www.googleapis.com/auth/youtube";
    public final String token_type = "Bearer";
}
