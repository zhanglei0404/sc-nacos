package com.example.scconsumer;

import org.springframework.stereotype.Component;

@Component
public class HelloHystrix implements HelloService {
    @Override
    public String sayHi(String name) {
        return "sorry hystrix";
    }
}
