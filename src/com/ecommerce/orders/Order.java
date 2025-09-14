package com.ecommerce.orders;

import com.ecommerce.Customer;
import com.ecommerce.Product;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Represents an order placed by a customer.
 */
public class Order {
    public enum Status { PENDING, CONFIRMED, SHIPPED, DELIVERED, CANCELLED }

    private final String orderID;
    private final Customer customer;
    private final List<OrderItem> items;
    private final double total;
    private final LocalDateTime createdAt;
    private Status status;

    public Order(String orderID, Customer customer, Map<Product, Integer> cartItems) {
        if (orderID == null || orderID.isBlank()) throw new IllegalArgumentException("orderID required");
        if (customer == null) throw new IllegalArgumentException("customer required");
        if (cartItems == null || cartItems.isEmpty()) throw new IllegalArgumentException("cart cannot be empty");

        this.orderID = orderID;
        this.customer = customer;
        this.items = new ArrayList<>();
        double t = 0.0;
        for (Map.Entry<Product, Integer> e : cartItems.entrySet()) {
            OrderItem oi = new OrderItem(e.getKey(), e.getValue());
            items.add(oi);
            t += oi.getItemTotal();
        }
        this.total = t;
        this.createdAt = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public String getOrderID() { return orderID; }
    public Customer getCustomer() { return customer; }
    public List<OrderItem> getItems() { return Collections.unmodifiableList(items); }
    public double getTotal() { return total; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public Status getStatus() { return status; }

    public void updateStatus(Status newStatus) {
        if (newStatus == null) throw new IllegalArgumentException("status cannot be null");
        this.status = newStatus;
    }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Order ID: %s%n", orderID));
        sb.append(String.format("Customer: %s (%s)%n", customer.getName(), customer.getCustomerID()));
        sb.append(String.format("Created at: %s%n", createdAt));
        sb.append("Items:\n");
        for (OrderItem oi : items) sb.append("  ").append(oi).append("\n");
        sb.append(String.format("Order total: $%.2f%n", total));
        sb.append(String.format("Status: %s%n", status));
        return sb.toString();
    }

    @Override
    public String toString() {
        return String.format("Order[%s] Customer=%s Total=$%.2f Status=%s", orderID, customer.getName(), total, status);
    }
}

