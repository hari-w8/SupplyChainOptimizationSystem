package com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.Order;
import com.util.ApplicationUtil;

public class OrderService {

    private ArrayList<Order> orderList = new ArrayList<Order>();
    private static int orderCount = 5001;

    public String generateOrderId() {
        return ApplicationUtil.generateId("O", orderCount++);
    }

    public void placeOrder(String orderId, String customerId, String productId, int quantity,
                           ProductService productService, InventoryService inventoryService)
            throws SupplyChainException {

        if (customerId == null || customerId.trim().isEmpty()) {
            throw new SupplyChainException("Customer ID cannot be empty!");
        }

        if (quantity <= 0) {
            throw new SupplyChainException("Quantity must be greater than 0!");
        }

        double price = productService.getProductPrice(productId);
        double totalAmount = price * quantity;

        inventoryService.reduceStock(productId, quantity);

        Order order = new Order(
                orderId,
                customerId,
                productId,
                quantity,
                LocalDateTime.now(),
                totalAmount,
                "Confirmed"
        );

        orderList.add(order);

        System.out.println("Order placed successfully!");
        System.out.println("Generated Order ID : " + orderId);
        System.out.println("Total Amount       : " + totalAmount);
    }

    public void viewOrders() {

        if (orderList.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orderList) {
            System.out.println("--------------------------------");
            order.displayOrder();
        }
    }

    public boolean orderExists(String orderId) {

        for (Order order : orderList) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return true;
            }
        }

        return false;
    }

    public Order getOrderById(String orderId) throws SupplyChainException {

        for (Order order : orderList) {
            if (order.getOrderId().equalsIgnoreCase(orderId)) {
                return order;
            }
        }

        throw new SupplyChainException("Order not found!");
    }

    public void cancelOrder(String orderId) throws SupplyChainException {

        Order order = getOrderById(orderId);

        if (order.getStatus().equalsIgnoreCase("Delivered")) {
            throw new SupplyChainException("Delivered order cannot be cancelled!");
        }

        if (order.getStatus().equalsIgnoreCase("Cancelled")) {
            throw new SupplyChainException("Order already cancelled!");
        }

        order.setStatus("Cancelled");
        System.out.println("Order cancelled successfully!");
    }
}