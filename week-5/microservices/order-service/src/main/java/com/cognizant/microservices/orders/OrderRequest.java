package com.cognizant.microservices.orders;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OrderRequest(
        @NotNull Long userId,
        @NotBlank String itemName,
        @Min(1) int quantity) {
}
