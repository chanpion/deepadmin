package com.chenpp.deepadmin.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author April.Chen
 * @date 2025/2/13 14:46
 */
@Slf4j
public class JwtUtil {
    private static SecretKey key = Jwts.SIG.HS256.key().build();

    public static SecretKey key() {
        return key;
    }
    /**
     * 生成 JWT token
     */
    public static String generateToken(String username, long expiration) {
        return Jwts.builder().subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(key())
                .compact();
    }

    /**
     * 从 Jwt token 获取用户名
     */
    public static String getUsernameFromToken(String token) {
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: ", e);
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: ", e);
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: ", e);
        } catch (JwtException | IllegalArgumentException e) {
            log.error("JWT claims string is empty: ", e);
        } catch (Exception e) {
            log.error("validate token error", e);
        }
        return false;
    }


    public static String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return StringUtils.substringAfter(bearerToken, " ");
        }
        return null;
    }
}
