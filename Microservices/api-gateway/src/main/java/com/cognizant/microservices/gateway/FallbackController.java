package com.cognizant.microservices.gateway;

import java.time.Instant;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallbackController {

    @RequestMapping("/fallback/billing")
    public Mono<Map<String, Object>> billingFallback() {
        return Mono.just(Map.of(
                "status", "BILLING_SERVICE_UNAVAILABLE",
                "message", "Billing service is taking too long or is unavailable",
                "timestamp", Instant.now().toString()));
    }
}
