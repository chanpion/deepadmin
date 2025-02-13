package com.chenpp.deepadmin.auth.service;

/**
 * @author April.Chen
 * @date 2024/10/10 16:43
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return token
     */
    String login(String username, String password);

}
