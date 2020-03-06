package com.example.scconsumerother;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix // 开启监控
public class ScConsumerOtherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScConsumerOtherApplication.class, args);
    }

}
