package com.cognizant.microservices.payments;

import java.time.Duration;
import java.util.UUID;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class PaymentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentService.class);

    private final WebClient thirdPartyPaymentClient;

    public PaymentService(WebClient thirdPartyPaymentClient) {
        this.thirdPartyPaymentClient = thirdPartyPaymentClient;
    }

    @CircuitBreaker(name = "thirdPartyPayment", fallbackMethod = "fallbackPayment")
    public PaymentResponse pay(PaymentRequest request) {
        return thirdPartyPaymentClient.post()
                .uri("/payments")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(PaymentResponse.class)
                .block(Duration.ofSeconds(2));
    }

    public PaymentResponse fallbackPayment(PaymentRequest request, Throwable throwable) {
        String reference = "FALLBACK-" + UUID.randomUUID();
        LOGGER.warn("Fallback payment created for orderId={} reference={} reason={}",
                request.orderId(), reference, throwable.toString());
        return new PaymentResponse(
                "PENDING_MANUAL_REVIEW",
                reference,
                "Payment provider is unavailable. Request has been queued for manual review.");
    }
}
