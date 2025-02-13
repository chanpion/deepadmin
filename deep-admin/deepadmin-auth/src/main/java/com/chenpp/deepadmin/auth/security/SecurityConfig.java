package com.chenpp.deepadmin.auth.security;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author April.Chen
 * @date 2025/2/13 15:06
 */
//@ConditionalOnProperty(prefix = "auth.jwt", name = "enabled", havingValue = "true")
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Resource
    private JwtProperties jwtProperties;


    @Resource
    private JwtTokenProvider jwtTokenProvider;

    /**
     * 对密码进行编码
     */
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 从 AuthenticationConfiguration 实例中检索 Authentication Manager。
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.httpBasic().disable()
                // 关闭csrf
        http .csrf().disable()
                //禁用session，JWT校验不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                // 设置URL的授权
                .authorizeRequests()
                .antMatchers("/auth/login", "/user/*", "/h2-console/**").permitAll()
                //hasRole()表示需要指定的角色才能访问资源
                .antMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/user").hasRole("USER")
                // anyRequest() 所有请求   authenticated() 必须被认证
                .anyRequest().authenticated()
                .and()
                .formLogin().loginProcessingUrl("/user/login").permitAll().and()
                .logout().logoutUrl("/user/logout").permitAll().and()
                .addFilterBefore(new JwtTokenAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
                //应用登录过滤器的配置，配置分离
//                .apply(new JwtSecurityConfigurer(jwtTokenProvider));


        return http.build();
    }
}
