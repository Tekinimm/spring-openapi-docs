package com.example.productapi.controller;

import com.example.productapi.exception.ProductNotFoundException;
import com.example.productapi.model.Product;
import com.example.productapi.model.ProductRequest;
import com.example.productapi.repository.ProductRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@Tag(name = "Ürünler", description = "Ürün katalogunu yöneten CRUD uçları")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "Tüm ürünleri listele", description = "Katalogdaki bütün ürünleri döner.")
    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Id ile ürün getir")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ürün bulundu"),
            @ApiResponse(responseCode = "404", description = "Ürün yok")
    })
    @GetMapping("/{id}")
    public Product getById(@Parameter(description = "Ürün kimliği", example = "1") @PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Operation(summary = "Yeni ürün oluştur")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Ürün oluşturuldu"))
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = repository.save(new Product(null, request.name(), request.price(), request.description()));
        return ResponseEntity.created(URI.create("/api/products/" + product.id())).body(product);
    }

    @Operation(summary = "Ürünü güncelle")
    @PutMapping("/{id}")
    public Product update(
            @Parameter(description = "Ürün kimliği") @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        if (repository.findById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return repository.save(new Product(id, request.name(), request.price(), request.description()));
    }

    @Operation(summary = "Ürünü sil")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter(description = "Ürün kimliği") @PathVariable Long id) {
        if (!repository.deleteById(id)) {
            throw new ProductNotFoundException(id);
        }
    }
}
