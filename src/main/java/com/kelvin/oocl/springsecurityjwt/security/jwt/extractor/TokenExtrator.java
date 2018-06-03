package com.kelvin.oocl.springsecurityjwt.security.jwt.extractor;

public interface TokenExtrator {
    String extract(String header);
}
