package com.kelvin.oocl.springsecurityjwt.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

    @RequestMapping("/print")
    public String print(){
        SecurityContext context = SecurityContextHolder.getContext();
        context.getAuthentication();
        return "secured resources";
    }
}
