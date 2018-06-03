package com.kelvin.oocl.springsecurityjwt.temp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelvin.oocl.springsecurityjwt.common.ErrorResponse;
import com.kelvin.oocl.springsecurityjwt.common.ResponseStatus;
import org.junit.Test;
import org.springframework.http.HttpStatus;

public class MapperTest {
    @Test
    public void name() throws Exception {
        String str = new ObjectMapper().writeValueAsString(ErrorResponse.of(ResponseStatus.SUCCESS.value(), "sss", HttpStatus.BAD_REQUEST));
        System.out.println(str);
    }
}
