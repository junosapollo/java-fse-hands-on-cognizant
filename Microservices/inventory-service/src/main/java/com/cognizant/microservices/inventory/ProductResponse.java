package com.cognizant.microservices.inventory;

public record ProductResponse(Long id, String name, String sku, int stockQuantity) {
}
