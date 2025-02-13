package com.chenpp.deepadmin.auth.security;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author April.Chen
 * @date 2024/9/30 15:32
 */
@Configuration
@ConfigurationProperties(prefix = "auth.jwt")
@Data
public class JwtProperties {
    private String secretKey;
    private long validityInMs;
}
