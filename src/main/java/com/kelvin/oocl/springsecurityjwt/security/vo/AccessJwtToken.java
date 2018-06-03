package com.kelvin.oocl.springsecurityjwt.security.vo;

public class AccessJwtToken {
    private String rawToken;

    public AccessJwtToken(String rawToken) {
        this.rawToken = rawToken;
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }
}
