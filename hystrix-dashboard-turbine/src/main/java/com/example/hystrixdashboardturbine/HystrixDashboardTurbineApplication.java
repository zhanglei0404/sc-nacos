package com.example.hystrixdashboardturbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableHystrixDashboard // 开启hystrix仪表盘
@EnableTurbine // 开启turbine监控数据聚合
public class HystrixDashboardTurbineApplication {

    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardTurbineApplication.class, args);
        System.out.println("聚合监控仪表盘==》快点它！：http://localhost:11000/hystrix/monitor?stream=http://localhost:11000/turbine.stream");
    }

}
