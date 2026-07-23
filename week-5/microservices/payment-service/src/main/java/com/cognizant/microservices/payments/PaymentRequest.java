package com.cognizant.microservices.payments;

import java.math.BigDecimal;

public record PaymentRequest(Long orderId, BigDecimal amount, String currency) {
}
