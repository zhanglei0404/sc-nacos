package com.example.scconsumerother;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-consumer", fallback = HahahiHystrix.class)
public interface HahahiService {
    @GetMapping("/hi")
    String sayHahahi(@RequestParam("name") String name);
}
