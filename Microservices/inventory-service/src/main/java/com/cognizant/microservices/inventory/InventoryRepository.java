package com.cognizant.microservices.inventory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryRecord, Long> {

    Optional<InventoryRecord> findByProductId(Long productId);
}
