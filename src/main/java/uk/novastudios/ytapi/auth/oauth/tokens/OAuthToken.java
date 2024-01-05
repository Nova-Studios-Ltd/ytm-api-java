package uk.novastudios.ytapi.auth.oauth.tokens;

import java.time.Instant;

public class OAuthToken extends Token {
    public void update(BaseToken freshAccess) {
        this.access_token = freshAccess.access_token;
        this.expires_at = (int) (Instant.now().getEpochSecond() + freshAccess.expires_in);
    }

    @Override
    public boolean isExpiring() {
        return this.expires_at - Instant.now().getEpochSecond() < 60;
    }
}
