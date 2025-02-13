package com.chenpp.deepadmin.auth.service.impl;

import com.chenpp.deepadmin.auth.dao.RoleDao;
import com.chenpp.deepadmin.auth.dto.RoleDTO;
import com.chenpp.deepadmin.auth.entity.RoleDO;
import com.chenpp.deepadmin.auth.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * @author April.Chen
 * @date 2025/2/13 19:00
 */
@Service
public class RoleServiceImpl extends AbstractCommonService<RoleDao, RoleDTO, RoleDO> implements RoleService {
}
