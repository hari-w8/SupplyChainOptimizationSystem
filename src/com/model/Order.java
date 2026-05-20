package com.model;

import java.time.LocalDateTime;

public class Order {

    private String orderId;
    private String customerId;
    private String productId;
    private int quantity;
    private LocalDateTime orderDate;
    private double totalAmount;
    private String status;

    public Order(String orderId, String customerId, String productId, int quantity,
                 LocalDateTime orderDate, double totalAmount, String status) {

        this.orderId = orderId;
        this.customerId = customerId;
        this.productId = productId;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayOrder() {
        System.out.println("Order ID     : " + orderId);
        System.out.println("Customer ID  : " + customerId);
        System.out.println("Product ID   : " + productId);
        System.out.println("Quantity     : " + quantity);
        System.out.println("Order Date   : " + orderDate);
        System.out.println("Total Amount : " + totalAmount);
        System.out.println("Status       : " + status);
    }
}