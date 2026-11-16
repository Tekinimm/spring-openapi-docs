package com.example.productapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "A product in the catalog")
public record Product(
        @Schema(description = "Unique product identifier", example = "42") Long id,
        @Schema(description = "Product name", example = "Wireless mouse") String name,
        @Schema(description = "Price", example = "499.99") BigDecimal price,
        @Schema(description = "Short description", example = "Ergonomic mouse with USB receiver") String description
) {
}
