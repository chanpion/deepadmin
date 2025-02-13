package com.chenpp.deepadmin.auth.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author April.Chen
 * @date 2025/2/13 14:46
 */
@Slf4j
public class JwtUtil {
    private static SecretKey key(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }

    public static String generateToken(String username, long expiration, String secretKey) {
        Date now = new Date();
        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(key(secretKey))
                .compact();
    }

    public static String getUsernameFromToken(String token, String secretKey) {
        return Jwts.parser()
                .verifyWith(key(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public static boolean validateToken(String token, String secretKey) {
        try {
            Jws<Claims> claims = Jwts.parser()
                    .verifyWith(key(secretKey))
                    .build()
                    .parseSignedClaims(token);
            return !claims.getPayload().getExpiration().before(new Date());
        } catch (Exception e) {
            log.error("validate token error", e);
            return false;
        }
    }
}
