package com.chenpp.deepadmin.auth.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author April.Chen
 * @date 2024/9/30 14:35
 */
@Data
public class BaseDO {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String createBy;
    private String updateBy;
    private Date createTime;
    private Date updateTime;
}
