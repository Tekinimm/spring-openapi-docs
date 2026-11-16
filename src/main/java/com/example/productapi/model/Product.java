package com.example.productapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Katalogdaki bir ürün")
public record Product(
        @Schema(description = "Benzersiz ürün kimliği", example = "42") Long id,
        @Schema(description = "Ürün adı", example = "Kablosuz fare") String name,
        @Schema(description = "Fiyat (TL)", example = "499.99") BigDecimal price,
        @Schema(description = "Kısa açıklama", example = "USB alıcılı ergonomik fare") String description
) {
}
