package com.kelvin.oocl.springsecurityjwt.security.auth;

import com.kelvin.oocl.springsecurityjwt.model.User;
import com.kelvin.oocl.springsecurityjwt.repository.UserRepository;
import com.kelvin.oocl.springsecurityjwt.security.vo.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.notNull(authentication, "No authentication data provided");

        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        List<User> users = userRepository.findByUsername(username);
        if(null == users || users.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        if(!encoder.matches(password, users.get(0).getPassword())) {
            throw new BadCredentialsException("Authentication failed. password not valid");
        }

        List<GrantedAuthority> authorities = users.get(0).getRoles().stream().map(authority -> new SimpleGrantedAuthority(authority.getRole())).collect(Collectors.toList());
        UserContext userContext = new UserContext(username, authorities);

        return new UsernamePasswordAuthenticationToken(userContext, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
