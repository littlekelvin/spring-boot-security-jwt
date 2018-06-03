package com.kelvin.oocl.springsecurityjwt.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kelvin.oocl.springsecurityjwt.controller.RestAuthenticationEntryPoint;
import com.kelvin.oocl.springsecurityjwt.security.auth.LoginAuthenticationProvider;
import com.kelvin.oocl.springsecurityjwt.security.auth.LoginProcessFilter;
import com.kelvin.oocl.springsecurityjwt.security.jwt.JwtAuthenticationProcessFilter;
import com.kelvin.oocl.springsecurityjwt.security.jwt.JwtAuthenticationProvider;
import com.kelvin.oocl.springsecurityjwt.security.jwt.PathRequestMatcher;
import com.kelvin.oocl.springsecurityjwt.security.jwt.extractor.JwtTokenExtractor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final static String SECURED_API = "/secured/**";
    private final static String LOGIN_API = "/login"; //"permi/*"

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationSuccessHandler successHandler;
    @Autowired
    private AuthenticationFailureHandler failureHandler;
    @Autowired
    private LoginAuthenticationProvider loginAuthenticationProvider;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;
    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;
    @Autowired
    private JwtTokenExtractor jwtTokenExtractor;


    protected LoginProcessFilter buildLoginProcessFilter() {
        LoginProcessFilter loginProcessFilter = new LoginProcessFilter(LOGIN_API, successHandler, failureHandler, objectMapper);
        loginProcessFilter.setAuthenticationManager(this.authenticationManager);
        return loginProcessFilter;
    }

    protected JwtAuthenticationProcessFilter buildJwtAuthenticationProcessFilter() {
        List<String> skipPaths = Arrays.asList(LOGIN_API);
        PathRequestMatcher matcher = new PathRequestMatcher(skipPaths, SECURED_API);
        JwtAuthenticationProcessFilter filter = new JwtAuthenticationProcessFilter(jwtTokenExtractor, failureHandler, matcher);
        filter.setAuthenticationManager(this.authenticationManager);
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(loginAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(this.authenticationEntryPoint)
                .and()
                .authorizeRequests()
                .antMatchers(LOGIN_API).permitAll()
                .and()
                .authorizeRequests()
                .antMatchers(SECURED_API).authenticated()
                .and()
                .addFilterBefore(buildLoginProcessFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(buildJwtAuthenticationProcessFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
