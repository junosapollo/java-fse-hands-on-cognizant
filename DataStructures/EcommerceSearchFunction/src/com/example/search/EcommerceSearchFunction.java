package com.example.search;

import java.util.Arrays;
import java.util.Comparator;

class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    public String getProductId() { return productId; }
    public String getProductName() { return productName; }
    public String getCategory() { return category; }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName + ", Category=" + category + "]";
    }
}

public class EcommerceSearchFunction {

    // Linear Search: O(n)
    public static Product linearSearch(Product[] products, String targetName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(targetName)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search: O(log n) - requires sorted array
    public static Product binarySearch(Product[] products, String targetName) {
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(targetName);

            if (comparison == 0) {
                return products[mid];
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P1", "Laptop", "Electronics"),
            new Product("P2", "Tablet", "Electronics"),
            new Product("P3", "Smartwatch", "Wearable"),
            new Product("P4", "Headphones", "Audio"),
            new Product("P5", "Smartphone", "Electronics")
        };

        // Linear Search Test
        System.out.println("Linear Search for 'Smartwatch':");
        Product resultLinear = linearSearch(products, "Smartwatch");
        System.out.println(resultLinear != null ? "Found: " + resultLinear : "Not Found");

        // Binary Search Test requires sorted array
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        
        System.out.println("\nSorted Array for Binary Search:");
        for (Product p : products) System.out.println(p.getProductName());

        System.out.println("\nBinary Search for 'Smartphone':");
        Product resultBinary = binarySearch(products, "Smartphone");
        System.out.println(resultBinary != null ? "Found: " + resultBinary : "Not Found");
        
        // Linear Search: Best Case O(1), Average O(n), Worst O(n). Suitable for unsorted or small datasets.
        // Binary Search: Best Case O(1), Average O(log n), Worst O(log n). Suitable for large, sorted datasets.
    }
}
