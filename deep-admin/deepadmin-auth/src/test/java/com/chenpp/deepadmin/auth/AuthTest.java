package com.chenpp.deepadmin.auth;

import com.chenpp.deepadmin.auth.security.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @author April.Chen
 * @date 2025/2/13 17:37
 */
public class AuthTest {

    @Test
    public void testEncodePassword() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        System.out.println(passwordEncoder.encode("123456"));
    }

    @Test
    public void testJwt() {
        SecretKey key =  JwtUtil.key();
        String token = Jwts.builder().subject("admin").issuedAt(new Date()).signWith(key).compact();
        token = JwtUtil.generateToken("admin", 3600 * 1000);
        Jws<Claims> jws = Jwts.parser().verifyWith(JwtUtil.key()).build().parseSignedClaims(token);
        assert jws.getPayload().getSubject().equals("admin");
    }
}
