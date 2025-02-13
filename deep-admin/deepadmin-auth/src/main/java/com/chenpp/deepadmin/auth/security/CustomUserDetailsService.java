package com.chenpp.deepadmin.auth.security;

import com.chenpp.deepadmin.auth.dto.UserDTO;
import com.chenpp.deepadmin.auth.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author April.Chen
 * @date 2024/9/30 15:39
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Map<String, Object> params = new HashMap<>(1);
        params.put("username", username);
        UserDTO user = userService.selectOne(params);
        if (user == null) {
            throw new UsernameNotFoundException("User not exists by username");
        }
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());

        return new User(username, user.getPassword(), authorities);
    }
}
