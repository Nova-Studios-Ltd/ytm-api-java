package uk.novastudios.ytapi.auth.oauth.payloads;

public class CodePayload {
    public String scope;
    public String client_id;

    public CodePayload(String scope, String client_id) {
        this.scope = scope;
        this.client_id = client_id;
    }
}
