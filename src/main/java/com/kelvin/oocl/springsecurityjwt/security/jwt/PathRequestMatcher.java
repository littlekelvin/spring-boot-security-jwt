package com.kelvin.oocl.springsecurityjwt.security.jwt;

import io.jsonwebtoken.lang.Assert;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

public class PathRequestMatcher implements RequestMatcher {
    private OrRequestMatcher skipRequestMatcher;
    private RequestMatcher securedMatcher;

    public PathRequestMatcher(List<String> skipPath, String securedPath) {
        Assert.notNull(skipPath);
        List<RequestMatcher> skipMatchers = skipPath.stream()
                .map(path -> new AntPathRequestMatcher(path)).collect(Collectors.toList());
        skipRequestMatcher = new OrRequestMatcher(skipMatchers);
        securedMatcher = new AntPathRequestMatcher(securedPath);
    }

    @Override
    public boolean matches(HttpServletRequest request) {
        if(skipRequestMatcher.matches(request)){
            return false;
        }
        return securedMatcher.matches(request) ? true : false;
    }
}
