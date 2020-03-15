package com.example.scresource.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-consumer", fallback = HelloHystrix.class)
public interface HelloService {
    @GetMapping("/hi")
    String sayhi(@RequestParam("name") String name);
}
