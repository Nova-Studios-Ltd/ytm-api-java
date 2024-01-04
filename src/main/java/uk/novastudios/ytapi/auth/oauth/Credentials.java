package uk.novastudios.ytapi.auth.oauth;

import uk.novastudios.ytapi.auth.oauth.tokens.AuthCode;
import uk.novastudios.ytapi.auth.oauth.tokens.BaseToken;
import uk.novastudios.ytapi.auth.oauth.tokens.RefreshableToken;

public abstract class Credentials {
    protected String ClientID;
    protected String ClientSecret;

    /**
     * Obtains a new user auth code
     * @return A Youtube auth code
     */
    public abstract AuthCode GetCode();

    /**
     * Verifies user auth code
     * @param deviceCode Code used to verify
     * @return A refreshable Youtube auth token
     */
    public abstract RefreshableToken TokenFromCode(String deviceCode);

    /**
     * Request a new access token using a given refreshToken
     * @param refreshToken Token used to refresh
     * @return A Youtube auth token
     */
    public abstract BaseToken RefreshToken(String refreshToken);
}
