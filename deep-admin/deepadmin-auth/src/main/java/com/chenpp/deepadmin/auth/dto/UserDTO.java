package com.chenpp.deepadmin.auth.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author April.Chen
 * @date 2024/9/30 14:40
 */
@Data
public class UserDTO extends BaseDTO {
    private static final long serialVersionUID = -2513518954781761850L;

    /**
     * 账号
     */
    private String account;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;

    /**
     * 性别 0:未知;1:男;2:女
     */
    private Integer gender;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 过期时间
     */
    private Date expiration;

    /**
     * 用户状态 0:正常
     */
    private Integer status;

    private List<RoleDTO> roles;
}
