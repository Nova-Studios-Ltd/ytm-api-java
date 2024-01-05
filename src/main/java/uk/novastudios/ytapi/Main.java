package uk.novastudios.ytapi;

import uk.novastudios.ytapi.auth.oauth.OAuthCredentials;
import uk.novastudios.ytapi.auth.oauth.errors.UnauthorizedOAuthClient;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {
    public static void main(String[] args) throws Exception {

        System.out.println(new OAuthCredentials(Constants.OAUTH_CLIENT_ID, Constants.OAUTH_CLIENT_SECRET).getCode().user_code);
    }
}
