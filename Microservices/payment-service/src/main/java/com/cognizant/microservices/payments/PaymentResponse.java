package com.cognizant.microservices.payments;

public record PaymentResponse(String status, String reference, String message) {
}
