package com.chenpp.deepadmin.auth.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author April.Chen
 * @date 2024/10/10 16:21
 */
@Data
public class RoleDTO implements Serializable {
    private static final long serialVersionUID = -5817270880790560364L;

    private String name;
}
