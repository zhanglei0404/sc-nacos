package com.example.scprovider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @GetMapping("/hi")
    public String sayHi(@RequestParam("name") String name) {
        return "hi," + name + ". port=" + port;
    }
}
