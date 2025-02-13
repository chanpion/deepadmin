package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author April.Chen
 * @date 2024/9/30 14:34
 */
@TableName("sys_user")
@Data
public class UserDO extends BaseDO {

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

}
