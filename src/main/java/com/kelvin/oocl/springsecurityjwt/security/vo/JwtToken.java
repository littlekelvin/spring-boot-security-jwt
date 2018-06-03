package com.kelvin.oocl.springsecurityjwt.security.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.jsonwebtoken.Claims;

public class JwtToken {
    private String rawToken;
    @JsonIgnore private Claims claims;

    public JwtToken(String rawToken, Claims claims) {
        this.rawToken = rawToken;
        this.claims = claims;
    }

    public JwtToken(String rawToken) {
        this.rawToken = rawToken;
    }

    public String getRawToken() {
        return rawToken;
    }

    public void setRawToken(String rawToken) {
        this.rawToken = rawToken;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }
}
