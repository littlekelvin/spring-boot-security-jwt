package com.kelvin.oocl.springsecurityjwt.security.jwt;

import com.kelvin.oocl.springsecurityjwt.common.Constants;
import com.kelvin.oocl.springsecurityjwt.security.jwt.extractor.JwtTokenExtractor;
import com.kelvin.oocl.springsecurityjwt.security.vo.AccessJwtToken;
import com.kelvin.oocl.springsecurityjwt.security.vo.JwtAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationProcessFilter extends AbstractAuthenticationProcessingFilter {

    private JwtTokenExtractor jwtTokenExtractor;
    private AuthenticationFailureHandler failureHandler;

    public JwtAuthenticationProcessFilter(JwtTokenExtractor jwtTokenExtractor, AuthenticationFailureHandler failureHandler,
              RequestMatcher matcher) {
        super(matcher);
        this.failureHandler = failureHandler;
        this.jwtTokenExtractor = jwtTokenExtractor;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        String tokenPayload = httpServletRequest.getHeader(Constants.JWT_TOKEN_HEADER);
        AccessJwtToken jwtToken = new AccessJwtToken(this.jwtTokenExtractor.extract(tokenPayload));
        return this.getAuthenticationManager().authenticate(new JwtAuthenticationToken(jwtToken));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
        securityContext.setAuthentication(authResult);
        SecurityContextHolder.setContext(securityContext);
        chain.doFilter(request, response);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        SecurityContextHolder.clearContext();
        failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
