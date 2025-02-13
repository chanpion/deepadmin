package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author April.Chen
 * @date 2024/9/30 14:42
 */
@TableName("sys_role")
@Data
public class RoleDO extends BaseDO {

    private String name;
    private String description;
    private Integer status;
}
