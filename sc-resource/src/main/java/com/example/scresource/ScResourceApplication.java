package com.example.scresource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix // 开启监控
public class ScResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScResourceApplication.class, args);
    }

}
