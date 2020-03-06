package com.example.scconsumerother;

import org.springframework.stereotype.Component;

@Component
public class HahahiHystrix implements HahahiService {
    @Override
    public String sayHahahi(String name) {
        return "hahahi";
    }
}
