package com.example.scuaa;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.scuaa.dao")
public class ScUaaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScUaaApplication.class, args);
    }

}
