package com.cognizant.microservices.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LoggingGlobalFilter implements GlobalFilter, Ordered {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingGlobalFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        long start = System.currentTimeMillis();
        String method = String.valueOf(exchange.getRequest().getMethod());
        String path = exchange.getRequest().getURI().getPath();
        LOGGER.info("Gateway request: {} {}", method, path);

        return chain.filter(exchange)
                .doFinally(signal -> LOGGER.info("Gateway response: {} {} status={} durationMs={}",
                        method,
                        path,
                        exchange.getResponse().getStatusCode(),
                        System.currentTimeMillis() - start));
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
