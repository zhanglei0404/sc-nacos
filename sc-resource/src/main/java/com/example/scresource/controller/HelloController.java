package com.example.scresource.controller;

import com.alibaba.fastjson.JSON;
import com.example.scresource.model.UserDTO;
import com.example.scresource.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @Autowired(required = false)
    HelloService helloService;

    /**
     * 测试分布式链路追踪接口
     * @param name
     * @return
     */
    @GetMapping("/sleuth/hi")
    public String sayHi(@RequestParam("name") String name) {
        return helloService.sayhi(name);
    }

    /**
     * 认证授权测试接口
     * @return
     */
    @GetMapping("/r/r1")
    @PreAuthorize("hasAnyAuthority('p1')")
    public String r1() {
        //获取用户身份信息
        UserDTO  userDTO = getUser();
        return userDTO.getFullname()+"访问资源1";
    }

    public UserDTO getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal!=null) {
            return JSON.parseObject(principal.toString(), UserDTO.class);
        }
        return null;

    };
}
