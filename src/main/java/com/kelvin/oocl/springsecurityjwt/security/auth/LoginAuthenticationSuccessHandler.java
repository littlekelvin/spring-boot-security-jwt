package com.kelvin.oocl.springsecurityjwt.security.auth;

import com.kelvin.oocl.springsecurityjwt.common.ErrorResponse;
import com.kelvin.oocl.springsecurityjwt.common.ResponseStatus;
import com.kelvin.oocl.springsecurityjwt.common.SuccessResponse;
import com.kelvin.oocl.springsecurityjwt.security.jwt.helper.JwtTokenHelper;
import com.kelvin.oocl.springsecurityjwt.security.vo.JwtToken;
import com.kelvin.oocl.springsecurityjwt.security.vo.UserContext;
import com.kelvin.oocl.springsecurityjwt.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    JwtTokenHelper tokenHelper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        try {
            UserContext userContext = (UserContext) authentication.getPrincipal();
            JwtToken accessToken = tokenHelper.createAccessToken(userContext);
            Cookie tokenCookie = new Cookie("authentication", accessToken.getRawToken());
            tokenCookie.setMaxAge(20);
            httpServletResponse.addCookie(tokenCookie);
            SuccessResponse<String> successResponse = new SuccessResponse(ResponseStatus.SUCCESS.value(), accessToken);
            ResponseUtil.writeSuccessReponse(httpServletResponse, successResponse);
        } catch (Exception e) {
            ErrorResponse errorResponse = ErrorResponse.of(ResponseStatus.Fail.value(), e.getMessage(), HttpStatus.UNAUTHORIZED);
            ResponseUtil.writeErrorReponse(httpServletResponse, errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
}
