package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author April.Chen
 * @date 2024/9/30 14:46
 */
@TableName("sys_menu")
@Data
public class MenuDO extends BaseDO {

    private Long parentId;
    private String name;
    private String title;
    private Integer level;

}
