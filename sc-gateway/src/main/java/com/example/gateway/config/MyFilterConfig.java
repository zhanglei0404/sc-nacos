package com.example.gateway.config;

import com.example.gateway.filter.factory.ElapsedGatewayFilterFactory;
import com.example.gateway.filter.MyTimeFilter;
import com.example.gateway.filter.TokenFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFilterConfig {

    @Bean
    public RouteLocator customerRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/fluent/consumer/**")
                        .filters(f -> f.stripPrefix(2) // 删除几级路径 /fluent/consumer=2级
                                .filter(new MyTimeFilter()) // 局部过滤器
                                .addResponseHeader("X-Response-Default-Foo", "fluent_consumer_service"))
                        .uri("lb://SERVICE-CONSUMER")
                        .id("fluent_consumer_service")
                ).build();
    }


    /**
     * TODO 疑问：经过/fluent/customer/**局部过滤器 为什么就不通过ElapsedGatewayFilterFactory了？？？
     *
     * TODO 疑问：经过/customer/**局部过滤器 就通过ElapsedGatewayFilterFactory,所以yml配置的局部过滤器和config配置的不一样？？？
     * @return
     */
    @Bean
    public ElapsedGatewayFilterFactory elapsedGatewayFilterFactory() {
        return new ElapsedGatewayFilterFactory();
    }
}
