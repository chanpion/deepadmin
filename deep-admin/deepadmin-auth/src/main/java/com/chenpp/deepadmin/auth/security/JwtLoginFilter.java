package com.chenpp.deepadmin.auth.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author April.Chen
 * @date 2025/2/18 10:46
 */
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
//        response.getOutputStream().println(JSONResult.fillResultString(0, "", JWT));
    }

}
