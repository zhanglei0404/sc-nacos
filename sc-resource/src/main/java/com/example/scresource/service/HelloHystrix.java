package com.example.scresource.service;

import org.springframework.stereotype.Component;

@Component
public class HelloHystrix implements HelloService {
    @Override
    public String sayhi(String name) {
        return "sorry hystrix";
    }
}
