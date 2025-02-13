package com.chenpp.deepadmin.auth.service.impl;

import com.chenpp.deepadmin.auth.security.JwtProperties;
import com.chenpp.deepadmin.auth.security.JwtUtil;
import com.chenpp.deepadmin.auth.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author April.Chen
 * @date 2024/10/10 16:44
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private AuthenticationManager authenticationManager;

    @Resource
    private JwtProperties jwtProperties;

    @Override
    public String login(String username, String password) {
        log.info("username: {}, password: {}", username, password);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationToken);
        log.info("login success");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return JwtUtil.generateToken(username, jwtProperties.getValidityInMs(), jwtProperties.getSecretKey());
    }
}
