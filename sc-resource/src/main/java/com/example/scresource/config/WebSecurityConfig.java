package com.example.scresource.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    //安全拦截机制（最重要）
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/sleuth/**").permitAll()// /sleuth/**的请求不需要认证
                .antMatchers("/**").access("#oauth2.hasScope('ROLE_ADMIN')")// /** 需要有改权限范围
                .anyRequest().authenticated()// 其他所有接口都需要认证
                .and().csrf().disable() // 跨域配置
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
