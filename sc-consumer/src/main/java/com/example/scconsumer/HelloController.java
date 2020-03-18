package com.example.scconsumer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "测试接口", tags = "测试接口")
public class HelloController {
    @Autowired(required = false)
    HelloService helloService;

    @GetMapping("/hi")
    @ApiOperation(value = "不登录接口")
    public String sayHi(@ApiParam(value = "名字", required = true, defaultValue = "谢广坤")
                            @RequestParam("name") String name) {
        return helloService.sayHi(name);
    }
}
