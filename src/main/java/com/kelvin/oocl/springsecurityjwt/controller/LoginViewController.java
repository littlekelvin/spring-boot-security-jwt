package com.kelvin.oocl.springsecurityjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginViewController {

    @RequestMapping(value = "/toLogin",method = RequestMethod.GET)
    public String index(HttpServletRequest request, Map<String,Object> result) {
        result.put("basePath", getBasePath(request));
        return "login";
    }

    private String getBasePath(HttpServletRequest request) {
        String basePath = request.getScheme() + "://" + request.getServerName()
                + ":" + request.getServerPort() + request.getContextPath();
        return basePath;
    }

}
