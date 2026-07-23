package com.cognizant.microservices.customers;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final AtomicLong sequence = new AtomicLong(2);
    private final Map<Long, Customer> customers = new ConcurrentHashMap<>(Map.of(
            1L, new Customer(1L, "Asha Rao", "asha@example.com"),
            2L, new Customer(2L, "Ravi Kumar", "ravi@example.com")));

    @GetMapping
    public Collection<Customer> findAll() {
        return customers.values();
    }

    @GetMapping("/{id}")
    public Customer findById(@PathVariable Long id) {
        Customer customer = customers.get(id);
        if (customer == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found: " + id);
        }
        return customer;
    }

    @PostMapping
    public Customer create(@RequestBody Customer request) {
        long id = sequence.incrementAndGet();
        Customer customer = new Customer(id, request.name(), request.email());
        customers.put(id, customer);
        return customer;
    }
}
