package com.cognizant.microservices.products;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ProductRequest(
        @NotBlank String name,
        @NotBlank String sku,
        @Min(0) int stockQuantity) {
}
