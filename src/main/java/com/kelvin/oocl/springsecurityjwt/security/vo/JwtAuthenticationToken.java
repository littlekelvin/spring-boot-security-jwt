package com.kelvin.oocl.springsecurityjwt.security.vo;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtAuthenticationToken extends AbstractAuthenticationToken {
    private AccessJwtToken accessJwtToken;
    private UserContext userContext;

    public JwtAuthenticationToken(AccessJwtToken accessJwtToken) {
        super(null);
        this.accessJwtToken = accessJwtToken;
        this.setAuthenticated(false);
    }

    public JwtAuthenticationToken(UserContext userContext, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.userContext = userContext;
        this.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.accessJwtToken;
    }

    @Override
    public Object getPrincipal() {
        return this.userContext;
    }
}
