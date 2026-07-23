package com.cognizant.microservices.orders;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderRepository orderRepository;
    private final WebClient userServiceClient;

    public OrderController(OrderRepository orderRepository, WebClient userServiceClient) {
        this.orderRepository = orderRepository;
        this.userServiceClient = userServiceClient;
    }

    @GetMapping
    public List<CustomerOrder> findAll(@RequestParam(required = false) Long userId) {
        if (userId == null) {
            return orderRepository.findAll();
        }
        return orderRepository.findByUserId(userId);
    }

    @GetMapping("/{id}")
    public CustomerOrder findById(@PathVariable Long id) {
        return loadOrder(id);
    }

    @PostMapping
    public ResponseEntity<CustomerOrder> create(@Valid @RequestBody OrderRequest request) {
        validateUser(request.userId());
        CustomerOrder order = orderRepository.save(new CustomerOrder(
                request.userId(), request.itemName(), request.quantity(), "PLACED"));
        return ResponseEntity.created(URI.create("/orders/" + order.getId())).body(order);
    }

    @PutMapping("/{id}/status/{status}")
    public CustomerOrder updateStatus(@PathVariable Long id, @PathVariable String status) {
        CustomerOrder order = loadOrder(id);
        order.setStatus(status.toUpperCase());
        return orderRepository.save(order);
    }

    private CustomerOrder loadOrder(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found: " + id));
    }

    private void validateUser(Long userId) {
        userServiceClient.get()
                .uri("/users/{id}", userId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "User not found: " + userId)))
                .bodyToMono(UserResponse.class)
                .block(Duration.ofSeconds(3));
    }
}
