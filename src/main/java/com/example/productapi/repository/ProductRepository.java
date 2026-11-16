package com.example.productapi.repository;

import com.example.productapi.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ProductRepository {

    private final Map<Long, Product> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    public Product save(Product product) {
        long id = product.id() != null ? product.id() : idGenerator.incrementAndGet();
        Product saved = new Product(id, product.name(), product.price(), product.description());
        store.put(id, saved);
        return saved;
    }

    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Product> findAll() {
        return List.copyOf(store.values());
    }

    public boolean deleteById(Long id) {
        return store.remove(id) != null;
    }
}
