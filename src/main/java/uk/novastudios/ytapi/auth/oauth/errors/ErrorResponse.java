package uk.novastudios.ytapi.auth.oauth.errors;

public class ErrorResponse {
    public String error;
    public String error_description;


    public static final String UNAUTHORIZED_CLIENT = "unauthorized_client";
    public static final String INVALID_CLIENT = "invalid_client";
}
