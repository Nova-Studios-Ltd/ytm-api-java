package uk.novastudios.ytapi.auth.oauth.errors;

public class UnauthorizedOAuthClient extends Exception {
    public UnauthorizedOAuthClient(String message) {
        super(message);
    }
}
