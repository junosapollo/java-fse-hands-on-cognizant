package com.cognizant.microservices.inventory;

import java.net.URI;
import java.time.Duration;
import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;
    private final WebClient productClient;

    public InventoryController(InventoryRepository inventoryRepository, WebClient.Builder webClientBuilder) {
        this.inventoryRepository = inventoryRepository;
        this.productClient = webClientBuilder.baseUrl("http://product-service").build();
    }

    @GetMapping
    public List<InventoryRecord> findAll() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/product/{productId}")
    public InventoryRecord findByProductId(@PathVariable Long productId) {
        return inventoryRepository.findByProductId(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Inventory not found for product: " + productId));
    }

    @PostMapping
    public ResponseEntity<InventoryRecord> create(@Valid @RequestBody InventoryRequest request) {
        validateProduct(request.productId());
        InventoryRecord record = inventoryRepository.save(new InventoryRecord(
                request.productId(), request.availableQuantity(), request.reservedQuantity()));
        return ResponseEntity.created(URI.create("/inventory/product/" + record.getProductId())).body(record);
    }

    @PutMapping("/product/{productId}")
    public InventoryRecord update(@PathVariable Long productId, @Valid @RequestBody InventoryRequest request) {
        if (!productId.equals(request.productId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Path productId and body productId must match");
        }
        validateProduct(productId);
        InventoryRecord record = findByProductId(productId);
        record.setAvailableQuantity(request.availableQuantity());
        record.setReservedQuantity(request.reservedQuantity());
        return inventoryRepository.save(record);
    }

    private void validateProduct(Long productId) {
        productClient.get()
                .uri("/products/{id}", productId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "Product not found: " + productId)))
                .bodyToMono(ProductResponse.class)
                .block(Duration.ofSeconds(3));
    }
}
