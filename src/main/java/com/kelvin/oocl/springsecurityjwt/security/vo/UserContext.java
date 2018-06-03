package com.kelvin.oocl.springsecurityjwt.security.vo;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserContext {
    private String username;
    private List<GrantedAuthority> authorities;

    public UserContext(String username, List<GrantedAuthority> authorities) {
        this.username = username;
        this.authorities = authorities;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }
}
