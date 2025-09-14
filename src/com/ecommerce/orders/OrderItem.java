package com.ecommerce.orders;

import com.ecommerce.Product;

/**
 * Immutable OrderItem pairing a product and a quantity.
 */
public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        if (product == null) throw new IllegalArgumentException("product cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("quantity must be positive");
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getItemTotal() { return product.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%s x%d -> $%.2f", product.toString(), quantity, getItemTotal());
    }
}

