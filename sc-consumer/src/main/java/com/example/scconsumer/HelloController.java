package com.example.scconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired(required = false)
    HelloService helloService;

    @GetMapping("/hi")
    public String sayHi(@RequestParam("name") String name) {
        return helloService.sayHi(name);
    }
}
