package com.chenpp.deepadmin.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;

/**
 * 步骤2：
 * 用于生成、验证 JWT 以及从 JWT 中提取信息
 *
 * @author April.Chen
 * @date 2024/9/30 15:32
 */
@Component
public class JwtTokenProvider {
    private static Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);


    @Autowired
    private JwtProperties jwtProperties;

    @Autowired
    private UserDetailsService userDetailsService;

    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(jwtProperties.getSecretKey().getBytes());
    }


    /**
     * 从 Jwt token 获取用户名
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(JwtUtil.key())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }



    public Authentication getAuthentication(String token, HttpServletRequest request) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return authenticationToken;
    }


}
