package com.cognizant.microservices.products;

import java.net.URI;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return loadProduct(id);
    }

    @PostMapping
    public ResponseEntity<Product> create(@Valid @RequestBody ProductRequest request) {
        Product product = productRepository.save(new Product(
                request.name(), request.sku(), request.stockQuantity()));
        return ResponseEntity.created(URI.create("/products/" + product.getId())).body(product);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @Valid @RequestBody ProductRequest request) {
        Product product = loadProduct(id);
        product.setName(request.name());
        product.setSku(request.sku());
        product.setStockQuantity(request.stockQuantity());
        return productRepository.save(product);
    }

    @PatchMapping("/{id}/stock")
    public Product adjustStock(@PathVariable Long id, @Valid @RequestBody StockAdjustmentRequest request) {
        Product product = loadProduct(id);
        int nextStock = product.getStockQuantity() + request.delta();
        if (nextStock < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Stock cannot be negative");
        }
        product.setStockQuantity(nextStock);
        return productRepository.save(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Product product = loadProduct(id);
        productRepository.delete(product);
        return ResponseEntity.noContent().build();
    }

    private Product loadProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found: " + id));
    }
}
