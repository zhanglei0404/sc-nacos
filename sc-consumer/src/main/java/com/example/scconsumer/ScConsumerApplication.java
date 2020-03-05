package com.example.scconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ScConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScConsumerApplication.class, args);
    }

}
