package uk.novastudios.ytapi.auth.oauth.tokens;

import uk.novastudios.ytapi.Constants;

public class BaseToken {
    public String access_token;
    public int expires_in;
    public final String scope = Constants.OAUTH_SCOPE;
    public final String token_type = "Bearer";
}
