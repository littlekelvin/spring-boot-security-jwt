package com.kelvin.oocl.springsecurityjwt.security.jwt.extractor;

import com.kelvin.oocl.springsecurityjwt.common.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenExtractor implements TokenExtrator{

    @Override
    public String extract(String header) {
        if(StringUtils.isBlank(header)){
            throw new AuthenticationServiceException("No jwt token found in request header");
        }
        if(header.length() < Constants.JWT_TOKEN_PREFIX.length()){
            throw new AuthenticationServiceException("invalid jwt token");
        }
        return header.substring(Constants.JWT_TOKEN_PREFIX.length());
    }
}
