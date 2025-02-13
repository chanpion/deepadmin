package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author April.Chen
 * @date 2024/9/30 14:47
 */
@TableName("sys_resource")
@Data
public class ResourceDO extends BaseDO {

    private String name;
    private String url;
    private String description;
}
