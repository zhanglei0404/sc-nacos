package com.example.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 接口计时（局部）
 * 实际在使用 Spring Cloud 的过程中，我们会使用 Sleuth+Zipkin 来进行耗时分析。
 */
public class MyTimeFilter implements GatewayFilter, Ordered {

    private final Logger logger = LoggerFactory.getLogger(MyTimeFilter.class);
    private static final String ELAPSED_TIME_BEGIN = "elapsedTimeBegin";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getAttributes().put(ELAPSED_TIME_BEGIN, System.currentTimeMillis());
        return chain.filter(exchange). then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(ELAPSED_TIME_BEGIN);
            if (startTime != null) {
                logger.info(exchange.getRequest().getURI().getRawPath() + ": " + (System.currentTimeMillis() - startTime) + "ms");
            }
        }));
    }

    @Override
    public int getOrder() {
        // 设置为最低优先级
        return Ordered.LOWEST_PRECEDENCE;
    }

}
