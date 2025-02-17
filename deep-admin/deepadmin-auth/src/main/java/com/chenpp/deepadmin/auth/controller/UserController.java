package com.chenpp.deepadmin.auth.controller;

import com.chenpp.deepadmin.auth.dto.UserDTO;
import com.chenpp.deepadmin.auth.service.UserService;
import com.chenpp.deepadmin.common.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author April.Chen
 * @date 2024/10/10 17:19
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("add")
    public ResponseEntity<?> add(@RequestBody UserDTO userDTO) {
        userService.add(userDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("list")
    public Result<?> list() {
        return Result.success(userService.list());
    }
}
