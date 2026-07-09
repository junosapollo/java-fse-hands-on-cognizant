package com.cognizant.microservices.billing;

import java.math.BigDecimal;

public record Invoice(Long id, Long customerId, BigDecimal amount, String status) {
}
