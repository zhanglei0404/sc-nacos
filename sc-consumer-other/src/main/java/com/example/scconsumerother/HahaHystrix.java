package com.example.scconsumerother;

import org.springframework.stereotype.Component;

@Component
public class HahaHystrix implements HahaService {
    @Override
    public String sayHaha(String name) {
        return "sorry hystrix";
    }
}
