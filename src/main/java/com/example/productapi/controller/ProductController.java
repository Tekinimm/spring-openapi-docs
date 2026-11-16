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
@Tag(name = "Products", description = "CRUD endpoints managing the product catalog")
public class ProductController {

    private final ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @Operation(summary = "List all products", description = "Returns every product in the catalog.")
    @GetMapping
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Operation(summary = "Get a product by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @GetMapping("/{id}")
    public Product getById(@Parameter(description = "Product identifier", example = "1") @PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }

    @Operation(summary = "Create a new product")
    @ApiResponses(@ApiResponse(responseCode = "201", description = "Product created"))
    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = repository.save(new Product(null, request.name(), request.price(), request.description()));
        return ResponseEntity.created(URI.create("/api/products/" + product.id())).body(product);
    }

    @Operation(summary = "Update a product")
    @PutMapping("/{id}")
    public Product update(
            @Parameter(description = "Product identifier") @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        if (repository.findById(id).isEmpty()) {
            throw new ProductNotFoundException(id);
        }
        return repository.save(new Product(id, request.name(), request.price(), request.description()));
    }

    @Operation(summary = "Delete a product")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter(description = "Product identifier") @PathVariable Long id) {
        if (!repository.deleteById(id)) {
            throw new ProductNotFoundException(id);
        }
    }
}
