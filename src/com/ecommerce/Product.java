package com.ecommerce;

import java.util.Objects;

/**
 * Represents a product available for purchase.
 */
public class Product {
    private final String productID;
    private String name;
    private double price;

    public Product(String productID, String name, double price) {
        if (productID == null || productID.isBlank()) throw new IllegalArgumentException("productID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        if (price < 0) throw new IllegalArgumentException("price cannot be negative");
        this.productID = productID;
        this.name = name;
        this.price = price;
    }

    public String getProductID() { return productID; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.name = name;
    }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("price cannot be negative");
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s - $%.2f", productID, name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product p = (Product) o;
        return productID.equals(p.productID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productID);
    }
}

