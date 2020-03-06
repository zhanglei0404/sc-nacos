package com.example.scconsumerother;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HahaController {
    @Autowired(required = false)
    HahaService helloService;

    @Autowired(required = false)
    HahahiService hellohiService;

    @GetMapping("/haha")
    public String sayHaha(@RequestParam("name") String name) {
        return helloService.sayHaha(name);
    }

    @GetMapping("/hahahi")
    public String sayHahahi(@RequestParam("name") String name) {
        return hellohiService.sayHahahi(name);
    }
}
