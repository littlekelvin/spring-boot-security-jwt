package com.kelvin.oocl.springsecurityjwt.security.jwt;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/jwt-setting.properties", ignoreResourceNotFound = true)
@ConfigurationProperties(prefix = "jwt", ignoreInvalidFields = true, ignoreUnknownFields = true)
public class JwtSettings {
    private Integer tokenExpirationTime;

    private String tokenIssuer;

    private String tokenSigningKey;

    private String tokenRefreshExpirationTime;

    public Integer getTokenExpirationTime() {
        return tokenExpirationTime;
    }

    public void setTokenExpirationTime(Integer tokenExpirationTime) {
        this.tokenExpirationTime = tokenExpirationTime;
    }

    public String getTokenIssuer() {
        return tokenIssuer;
    }

    public void setTokenIssuer(String tokenIssuer) {
        this.tokenIssuer = tokenIssuer;
    }

    public String getTokenSigningKey() {
        return tokenSigningKey;
    }

    public void setTokenSigningKey(String tokenSigningKey) {
        this.tokenSigningKey = tokenSigningKey;
    }

    public String getTokenRefreshExpirationTime() {
        return tokenRefreshExpirationTime;
    }

    public void setTokenRefreshExpirationTime(String tokenRefreshExpirationTime) {
        this.tokenRefreshExpirationTime = tokenRefreshExpirationTime;
    }
}
