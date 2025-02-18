package com.chenpp.deepadmin.auth.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 步骤3：
 * 拦截传入的 HTTP 请求并验证包含在 Authorization 头中的 JWT Token。如果 Token 有效，Filter 就会在 SecurityContext 中设置当前用户的 Authentication
 *
 * @author April.Chen
 * @date 2024/9/30 15:31
 */
public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;

    public JwtTokenAuthenticationFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        logger.info("jwt token filter");
        // 从 request 获取 JWT token
        String token = JwtUtil.resolveToken(request);
        // 校验 token
        if (StringUtils.hasText(token) && JwtUtil.validateToken(token)) {
            String username = JwtUtil.getUsernameFromToken(token);
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            if (authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}