package com.ecommerce;

import java.util.*;

/**
 * ShoppingCart stores products and their quantities.
 */
public class ShoppingCart {
    private final Map<Product, Integer> items = new LinkedHashMap<>();

    public void addProduct(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        items.merge(product, quantity, Integer::sum);
    }

    public void removeProduct(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        Integer current = items.get(product);
        if (current == null) return; // nothing to remove
        if (quantity >= current) items.remove(product);
        else items.put(product, current - quantity);
    }

    public Map<Product, Integer> getItems() {
        return Collections.unmodifiableMap(items);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            total += e.getKey().getPrice() * e.getValue();
        }
        return total;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        if (items.isEmpty()) return "Cart is empty.";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Product, Integer> e : items.entrySet()) {
            Product p = e.getKey();
            int q = e.getValue();
            sb.append(String.format("%s x%d -> $%.2f%n", p.toString(), q, p.getPrice() * q));
        }
        sb.append(String.format("Total: $%.2f", calculateTotal()));
        return sb.toString();
    }
}

