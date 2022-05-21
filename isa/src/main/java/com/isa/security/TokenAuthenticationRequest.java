package com.isa.security;

public class TokenAuthenticationRequest {

    private String username;
    private String password;

    public TokenAuthenticationRequest() {
        super();
    }

    public TokenAuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
