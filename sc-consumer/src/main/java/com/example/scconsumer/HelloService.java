package com.example.scconsumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = HelloHystrix.class)
public interface HelloService {
    @GetMapping("/hi")
    String sayHi(@RequestParam("name") String name);
}
