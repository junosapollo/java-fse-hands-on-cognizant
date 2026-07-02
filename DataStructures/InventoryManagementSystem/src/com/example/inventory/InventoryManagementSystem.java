package com.example.inventory;

import java.util.HashMap;
import java.util.Map;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // getters and setters
    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName + ", Qty=" + quantity + ", Price=$" + price + "]";
    }
}

public class InventoryManagementSystem {
    // we choose hashmap for average time complexity for add update delete
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Added: " + product.getProductName());
    }

    public void updateProduct(String productId, int newQuantity, double newPrice) {
        if (inventory.containsKey(productId)) {
            Product p = inventory.get(productId);
            p.setQuantity(newQuantity);
            p.setPrice(newPrice);
            System.out.println("Updated: " + p.getProductName());
        } else {
            System.out.println("Product ID " + productId + " not found for update.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.remove(productId) != null) {
            System.out.println("Deleted product with ID: " + productId);
        } else {
            System.out.println("Product ID " + productId + " not found for deletion.");
        }
    }
    
    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Product p : inventory.values()) {
            System.out.println(p);
        }
        System.out.println("--------------------");
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        Product p1 = new Product("P101", "Laptop", 50, 999.99);
        Product p2 = new Product("P102", "Mouse", 200, 19.50);

        ims.addProduct(p1);
        ims.addProduct(p2);
        
        ims.displayInventory();

        ims.updateProduct("P102", 150, 18.00);
        
        ims.displayInventory();

        ims.deleteProduct("P101");
        
        ims.displayInventory();
        
        // adding time complexity with hashmap
        // updating to find and then update fields
        // deleting to remove by key
        // hashmap is extremely efficient compared to arraylist which would require to find an item by id
    }
}
