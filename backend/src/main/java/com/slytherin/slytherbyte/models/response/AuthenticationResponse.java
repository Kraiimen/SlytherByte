package com.slytherin.slytherbyte.models.response;

public class AuthenticationResponse {
    private int userAccountId;
    private String token;

    public AuthenticationResponse(int userAccountId, String token) {
        this.userAccountId = userAccountId;
        this.token = token;
    }

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
