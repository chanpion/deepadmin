package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author April.Chen
 * @date 2024/9/30 15:22
 */
@TableName("sys_role_permission")
@Data
public class RolePermissionDO extends BaseDO {

    private Long roleId;
    private Long permissionId;
}
