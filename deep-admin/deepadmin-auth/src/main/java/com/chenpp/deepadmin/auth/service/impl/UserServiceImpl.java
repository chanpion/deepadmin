package com.chenpp.deepadmin.auth.service.impl;

import com.chenpp.deepadmin.auth.dao.UserDao;
import com.chenpp.deepadmin.auth.dto.RoleDTO;
import com.chenpp.deepadmin.auth.dto.UserDTO;
import com.chenpp.deepadmin.auth.entity.UserDO;
import com.chenpp.deepadmin.auth.service.RoleService;
import com.chenpp.deepadmin.auth.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author April.Chen
 * @date 2024/9/30 17:16
 */
@Service
public class UserServiceImpl extends AbstractCommonService<UserDao, UserDTO, UserDO> implements UserService {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private RoleService roleService;

    @Override
    public boolean add(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return super.add(userDTO);
    }

    @Override
    public UserDTO selectOne(Map<String, Object> params) {
        UserDTO userDTO = super.selectOne(params);
        RoleDTO role = new RoleDTO();
        List<RoleDTO> roles = roleService.list(role);
        userDTO.setRoles(roles);
        return userDTO;
    }
}
