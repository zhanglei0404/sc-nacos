package com.example.scconsumerother;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-provider", fallback = HahaHystrix.class)
public interface HahaService {
    @GetMapping("/hi")
    String sayHaha(@RequestParam("name") String name);
}
