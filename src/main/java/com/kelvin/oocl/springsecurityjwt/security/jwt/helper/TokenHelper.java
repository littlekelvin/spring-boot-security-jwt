package com.kelvin.oocl.springsecurityjwt.security.jwt.helper;

import com.kelvin.oocl.springsecurityjwt.security.vo.JwtToken;
import com.kelvin.oocl.springsecurityjwt.security.vo.UserContext;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface TokenHelper {
    JwtToken createAccessToken(UserContext userContext);

    Jws<Claims> parseClaims(String signingKey, String accessToken);
}
