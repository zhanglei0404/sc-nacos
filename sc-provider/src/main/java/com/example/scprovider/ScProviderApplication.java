package com.example.scprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableHystrix
public class ScProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScProviderApplication.class, args);
    }

}
