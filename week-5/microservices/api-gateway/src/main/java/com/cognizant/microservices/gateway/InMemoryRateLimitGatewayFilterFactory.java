package com.cognizant.microservices.gateway;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

@Component
public class InMemoryRateLimitGatewayFilterFactory
        extends AbstractGatewayFilterFactory<InMemoryRateLimitGatewayFilterFactory.Config> {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    public InMemoryRateLimitGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("capacity", "refillPeriodSeconds");
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String key = keyFor(exchange);
            Bucket bucket = buckets.computeIfAbsent(key,
                    ignored -> new Bucket(config.getCapacity(), Instant.now().getEpochSecond()));

            if (!bucket.tryConsume(config.getCapacity(), config.getRefillPeriodSeconds())) {
                exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
                exchange.getResponse().getHeaders().add("X-Rate-Limit-Retry-After-Seconds",
                        String.valueOf(config.getRefillPeriodSeconds()));
                return exchange.getResponse().setComplete();
            }

            return chain.filter(exchange);
        };
    }

    private String keyFor(ServerWebExchange exchange) {
        String forwardedFor = exchange.getRequest().getHeaders().getFirst("X-Forwarded-For");
        if (forwardedFor != null && !forwardedFor.isBlank()) {
            return forwardedFor.split(",")[0].trim();
        }
        if (exchange.getRequest().getRemoteAddress() == null) {
            return "unknown";
        }
        return exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
    }

    public static class Config {

        private int capacity = 10;
        private int refillPeriodSeconds = 60;

        public int getCapacity() {
            return capacity;
        }

        public void setCapacity(int capacity) {
            this.capacity = capacity;
        }

        public int getRefillPeriodSeconds() {
            return refillPeriodSeconds;
        }

        public void setRefillPeriodSeconds(int refillPeriodSeconds) {
            this.refillPeriodSeconds = refillPeriodSeconds;
        }
    }

    private static final class Bucket {

        private int tokens;
        private long windowStartedAt;

        private Bucket(int tokens, long windowStartedAt) {
            this.tokens = tokens;
            this.windowStartedAt = windowStartedAt;
        }

        private synchronized boolean tryConsume(int capacity, int refillPeriodSeconds) {
            long now = Instant.now().getEpochSecond();
            if (now - windowStartedAt >= refillPeriodSeconds) {
                tokens = capacity;
                windowStartedAt = now;
            }
            if (tokens <= 0) {
                return false;
            }
            tokens--;
            return true;
        }
    }
}
