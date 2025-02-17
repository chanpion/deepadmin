package com.chenpp.deepadmin.auth.controller;

import com.chenpp.deepadmin.auth.service.AuthService;
import com.chenpp.deepadmin.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author April.Chen
 * @date 2024/9/30 17:34
 */
@Slf4j
@RestController
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/auth/login")
    public Result<?> login(String username, String password) {
        log.info("do login {}", username);
        String token = authService.login(username, password);
        return Result.success(token);
    }

    @PostMapping("/auth/logout")
    public Result<?> logout() {
        return Result.success("logout");
    }
}
