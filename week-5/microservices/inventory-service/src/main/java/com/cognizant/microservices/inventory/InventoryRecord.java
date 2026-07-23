package com.cognizant.microservices.inventory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "inventory")
public class InventoryRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long productId;

    private int availableQuantity;
    private int reservedQuantity;

    protected InventoryRecord() {
    }

    public InventoryRecord(Long productId, int availableQuantity, int reservedQuantity) {
        this.productId = productId;
        this.availableQuantity = availableQuantity;
        this.reservedQuantity = reservedQuantity;
    }

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public int getReservedQuantity() {
        return reservedQuantity;
    }

    public void setReservedQuantity(int reservedQuantity) {
        this.reservedQuantity = reservedQuantity;
    }
}
