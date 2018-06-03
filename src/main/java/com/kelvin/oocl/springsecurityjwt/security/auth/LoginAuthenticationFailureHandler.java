package com.kelvin.oocl.springsecurityjwt.security.auth;

import com.kelvin.oocl.springsecurityjwt.common.ErrorResponse;
import com.kelvin.oocl.springsecurityjwt.common.ResponseStatus;
import com.kelvin.oocl.springsecurityjwt.exception.AuthMethodNotSupportException;
import com.kelvin.oocl.springsecurityjwt.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        String errorMsg;
        if(e instanceof BadCredentialsException){
            errorMsg = "Invalid username or password";
        }else if(e instanceof AuthMethodNotSupportException) {
            errorMsg = "method not supported";
        }else {
            errorMsg = e.getMessage();
        }
//        httpServletResponse.sendRedirect("/toLogin");
        ErrorResponse errorResponse = ErrorResponse.of(ResponseStatus.Fail.value(), errorMsg, HttpStatus.UNAUTHORIZED);
        ResponseUtil.writeErrorReponse(httpServletResponse, errorResponse, HttpStatus.UNAUTHORIZED);
    }
}
