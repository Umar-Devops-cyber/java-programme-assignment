package com.ecommerce;

import com.ecommerce.orders.Order;

import java.util.UUID;

/**
 * Represents a customer with a shopping cart.
 */
public class Customer {
    private final String customerID;
    private String name;
    private final ShoppingCart cart;

    public Customer(String customerID, String name) {
        if (customerID == null || customerID.isBlank()) throw new IllegalArgumentException("customerID required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.customerID = customerID;
        this.name = name;
        this.cart = new ShoppingCart();
    }

    public String getCustomerID() { return customerID; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.name = name;
    }

    public ShoppingCart getCart() { return cart; }

    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeFromCart(Product product, int quantity) {
        cart.removeProduct(product, quantity);
    }

    public double cartTotal() {
        return cart.calculateTotal();
    }

    /**
     * Place an order based on current cart contents.
     * Clears the cart after creating the order.
     */
    public Order placeOrder() {
        if (cart.isEmpty()) throw new IllegalStateException("Cart is empty. Add items before placing an order.");
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, this, cart.getItems());
        cart.clear();
        return order;
    }

    @Override
    public String toString() {
        return String.format("Customer[%s] %s", customerID, name);
    }
}

