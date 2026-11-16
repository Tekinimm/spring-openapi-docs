package com.example.productapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * Validation rules are carried into the OpenAPI schema as well:
 * @NotBlank and @PositiveOrZero show up in Swagger UI as "required" and
 * "minimum: 0" respectively.
 */
public record ProductRequest(
        @NotBlank(message = "Product name must not be blank") String name,
        @NotNull(message = "Price is required") @PositiveOrZero BigDecimal price,
        String description
) {
}
