package com.example.scconsumerother;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HahaController {
    @Autowired(required = false)
    HahaService helloService;

    @GetMapping("/haha")
    public String sayHaha(@RequestParam("name") String name) {
        return helloService.sayHaha(name);
    }
}
