package com.example.productapi.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

/**
 * Girdi doğrulama (validation) kurallarını da OpenAPI şemasına taşır:
 * @NotBlank ve @PositiveOrZero anotasyonları Swagger UI'da "required" ve
 * "minimum: 0" olarak görünür.
 */
public record ProductRequest(
        @NotBlank(message = "Ürün adı boş olamaz") String name,
        @NotNull(message = "Fiyat zorunludur") @PositiveOrZero BigDecimal price,
        String description
) {
}
