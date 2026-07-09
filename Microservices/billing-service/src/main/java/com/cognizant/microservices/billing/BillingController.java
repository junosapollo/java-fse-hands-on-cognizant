package com.cognizant.microservices.billing;

import java.math.BigDecimal;
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
@RequestMapping("/api/billing")
public class BillingController {

    private final AtomicLong sequence = new AtomicLong(2);
    private final Map<Long, Invoice> invoices = new ConcurrentHashMap<>(Map.of(
            1L, new Invoice(1L, 1L, new BigDecimal("1999.00"), "PAID"),
            2L, new Invoice(2L, 2L, new BigDecimal("799.00"), "PENDING")));

    @GetMapping
    public Collection<Invoice> findAll() {
        return invoices.values();
    }

    @GetMapping("/{id}")
    public Invoice findById(@PathVariable Long id) {
        Invoice invoice = invoices.get(id);
        if (invoice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invoice not found: " + id);
        }
        return invoice;
    }

    @PostMapping
    public Invoice create(@RequestBody Invoice request) {
        long id = sequence.incrementAndGet();
        Invoice invoice = new Invoice(id, request.customerId(), request.amount(), "PENDING");
        invoices.put(id, invoice);
        return invoice;
    }
}
