package com.kelvin.oocl.springsecurityjwt.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelvin.oocl.springsecurityjwt.common.ErrorResponse;
import com.kelvin.oocl.springsecurityjwt.common.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void writeSuccessReponse(HttpServletResponse httpServletResponse, SuccessResponse response) throws IOException {
        httpServletResponse.setStatus(HttpStatus.OK.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(httpServletResponse.getWriter(), response);
    }

    public static void writeErrorReponse(HttpServletResponse httpServletResponse, ErrorResponse response, HttpStatus httpStatus) throws IOException {
        httpServletResponse.setStatus(httpStatus.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(httpServletResponse.getWriter(), response);
    }
}
