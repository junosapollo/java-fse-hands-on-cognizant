package com.cognizant.microservices.inventory;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record InventoryRequest(
        @NotNull Long productId,
        @Min(0) int availableQuantity,
        @Min(0) int reservedQuantity) {
}
