package com.springsecurity.config;

import com.springsecurity.filter.CaptchaFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;


@Configuration
public class SecurityConfig {

    @Resource
    private CaptchaFilter captchaFilter;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.formLogin().loginPage("/toLogin")  //登录页，需要controller接收这个请求
                .loginProcessingUrl("/userLogin")
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/toLogin"
                        ,"/userLogin"
                        ,"/toLogout",
                        "/captchaCode"
                        ,"/js/**")    //表示这些请求不需要拦截登录，可以直接访问
                .permitAll()    //表示允许以上所有
                .anyRequest().authenticated()   //表示以上路径以外的所有请求都需要登录（认证）
                .and()
                .csrf().disable()  //禁用跨域访问保护
                .build();
    }
}
