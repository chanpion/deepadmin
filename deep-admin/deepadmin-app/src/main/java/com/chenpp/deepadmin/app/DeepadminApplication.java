package com.chenpp.deepadmin.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author April.Chen
 * @date 2025/2/13 14:15
 */
@MapperScan("com.chenpp.deepadmin.**.dao")
@SpringBootApplication(scanBasePackages = "com.chenpp.deepadmin")
public class DeepadminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeepadminApplication.class, args);
    }
}
