package com.cognizant.microservices.products;

import jakarta.validation.constraints.NotNull;

public record StockAdjustmentRequest(@NotNull Integer delta) {
}
