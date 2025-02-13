package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author April.Chen
 * @date 2024/9/30 14:45
 */
@TableName("sys_permission")
@Data
public class PermissionDO extends BaseDO {

    private String name;
    private String description;
    private Integer type;
    private Integer status;
}
