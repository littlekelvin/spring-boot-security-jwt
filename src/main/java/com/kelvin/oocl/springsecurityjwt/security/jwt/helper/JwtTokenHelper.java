package com.kelvin.oocl.springsecurityjwt.security.jwt.helper;

import com.kelvin.oocl.springsecurityjwt.exception.JwtExpiredTokenException;
import com.kelvin.oocl.springsecurityjwt.security.jwt.JwtSettings;
import com.kelvin.oocl.springsecurityjwt.security.vo.JwtToken;
import com.kelvin.oocl.springsecurityjwt.security.vo.UserContext;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtTokenHelper implements TokenHelper{
    private static Logger logger = LoggerFactory.getLogger(JwtTokenHelper.class);

    @Autowired
    JwtSettings jwtSettings;

    @Override
    public JwtToken createAccessToken(UserContext userContext) {
        if(StringUtils.isBlank(userContext.getUsername())){
            throw new IllegalArgumentException("Can not create token without username");
        }
        if(null == userContext.getAuthorities() || userContext.getAuthorities().isEmpty()){
            throw new IllegalArgumentException("user doesn't have any privileges");
        }

        Claims claims = Jwts.claims().setSubject(userContext.getUsername());
        claims.put("scopes", userContext.getAuthorities().stream().map(s -> s.toString()).collect(Collectors.toList()));

        LocalDateTime currentTime = LocalDateTime.now();

        String token = Jwts.builder()
                .setClaims(claims)
                .setIssuer(jwtSettings.getTokenIssuer())
                .setIssuedAt(Date.from(currentTime.atZone(ZoneId.systemDefault()).toInstant()))
                .setExpiration(Date.from(currentTime.plusMinutes(jwtSettings.getTokenExpirationTime())
                        .atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS512, jwtSettings.getTokenSigningKey())
                .compact();
        return new JwtToken(token, claims);
    }

    @Override
    public Jws<Claims> parseClaims(String signingKey, String accessToken) {
        try {
            return Jwts.parser().setSigningKey(signingKey).parseClaimsJws(accessToken);
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredTokenException("Authentication Failed: JWT token expired");
        } catch (UnsupportedJwtException | IllegalArgumentException | MalformedJwtException | SignatureException e) {
            throw new BadCredentialsException("Authentication Failed: Invalid JWT token");
        }
    }
}
