package com.example.scquartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ScQuartzApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScQuartzApplication.class, args);
    }

}
