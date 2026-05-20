package com.service;

import com.exception.SupplyChainException;
import com.management.InventoryManagement;
import com.management.OrderManagement;
import com.model.Inventory;
import com.model.Order;
import com.util.ApplicationUtil;

import java.util.List;
import java.util.stream.Collectors;

public class OrderService {

    private final OrderManagement orderManagement;
    private final InventoryManagement inventoryManagement;

    public OrderService(OrderManagement orderManagement,
                        InventoryManagement inventoryManagement) {
        this.orderManagement     = orderManagement;
        this.inventoryManagement = inventoryManagement;
    }


    // ─────────────────────────────────────────────
    // PLACE ORDER
    // ─────────────────────────────────────────────

    public void placeOrder(String customerId,
                           String productId,
                           int quantity,
                           double unitPrice)
            throws SupplyChainException {

        validateCustomerId(customerId);
        validateProductId(productId);
        validateQuantity(quantity);
        validateUnitPrice(unitPrice);

        Inventory inv = inventoryManagement.getInventoryByProductId(productId);

        if (inv == null) {
            throw new SupplyChainException(
                    "Product not available in inventory: " + productId);
        }

        if (inv.getQuantityInStock() < quantity) {
            throw new SupplyChainException(
                    "Insufficient stock. Available: "
                    + inv.getQuantityInStock() + ", Requested: " + quantity);
        }

        List<String> existingOrderIds =
                orderManagement.getAllOrders()
                        .stream()
                        .map(Order::getOrderId)
                        .collect(Collectors.toList());

        String newOrderId = ApplicationUtil.generateOrderId(existingOrderIds);

        double totalAmount = quantity * unitPrice;

        Order order = new Order(
                newOrderId,
                customerId,
                ApplicationUtil.getCurrentDateTime(),
                totalAmount,
                OrderManagement.STATUS_NEW
        );

        orderManagement.insertOrder(order);

        int updatedStock = inv.getQuantityInStock() - quantity;
        inventoryManagement.updateStock(productId, updatedStock);

        System.out.println("Order placed successfully. Order ID: " + newOrderId);
    }


    // ─────────────────────────────────────────────
    // CANCEL ORDER
    // ─────────────────────────────────────────────

    public void cancelOrder(String orderId)
            throws SupplyChainException {

        validateOrderId(orderId);

        Order order = orderManagement.getOrderById(orderId);

        if (order == null) {
            throw new SupplyChainException("Order not found: " + orderId);
        }

        if (!OrderManagement.STATUS_NEW.equals(order.getStatus())
                && !OrderManagement.STATUS_CONFIRMED.equals(order.getStatus())) {
            throw new SupplyChainException(
                    "Order cancellation not allowed. Current status: "
                    + order.getStatus());
        }

        orderManagement.cancelOrder(orderId);
        System.out.println("Order cancelled successfully: " + orderId);
    }


    // ─────────────────────────────────────────────
    // GET ORDER BY ID
    // ─────────────────────────────────────────────

    public Order getOrderById(String orderId)
            throws SupplyChainException {

        validateOrderId(orderId);

        Order order = orderManagement.getOrderById(orderId);

        if (order == null) {
            throw new SupplyChainException("Order not found: " + orderId);
        }

        return order;
    }


    // ─────────────────────────────────────────────
    // GET ALL ORDERS BY CUSTOMER
    // ─────────────────────────────────────────────

    public List<Order> getOrdersByCustomer(String customerId)
            throws SupplyChainException {

        validateCustomerId(customerId);

        return orderManagement.getAllOrders()
                .stream()
                .filter(o -> o.getCustomerId().equalsIgnoreCase(customerId))
                .collect(Collectors.toList());
    }


    // ─────────────────────────────────────────────
    // UPDATE ORDER STATUS
    // ─────────────────────────────────────────────

    public void updateOrderStatus(String orderId, String newStatus)
            throws SupplyChainException {

        validateOrderId(orderId);

        if (!OrderManagement.VALID_STATUSES.contains(newStatus)) {
            throw new SupplyChainException("Invalid order status: " + newStatus);
        }

        Order order = orderManagement.getOrderById(orderId);

        if (order == null) {
            throw new SupplyChainException("Order not found: " + orderId);
        }

        if (OrderManagement.STATUS_CANCELLED.equals(order.getStatus())) {
            throw new SupplyChainException("Cannot update a cancelled order.");
        }

        order.setStatus(newStatus);
        orderManagement.updateOrder(order);
        System.out.println("Order " + orderId + " status updated to: " + newStatus);
    }


    // ─────────────────────────────────────────────
    // BUILD ORDER LIST FROM RAW INPUT
    // ─────────────────────────────────────────────

    public List<Order> buildOrderList(List<String> rawRecords)
            throws SupplyChainException {

        return ApplicationUtil.buildOrderList(rawRecords);
    }


    // ─────────────────────────────────────────────
    // PRIVATE VALIDATION HELPERS
    // ─────────────────────────────────────────────

    private void validateOrderId(String orderId) throws SupplyChainException {
        if (orderId == null || orderId.trim().isEmpty()) {
            throw new SupplyChainException("Order ID must not be null or empty.");
        }
    }

    private void validateCustomerId(String customerId) throws SupplyChainException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new SupplyChainException("Customer ID must not be null or empty.");
        }
    }

    private void validateProductId(String productId) throws SupplyChainException {
        if (productId == null || productId.trim().isEmpty()) {
            throw new SupplyChainException("Product ID must not be null or empty.");
        }
    }

    private void validateQuantity(int quantity) throws SupplyChainException {
        if (quantity <= 0) {
            throw new SupplyChainException("Quantity must be greater than zero.");
        }
    }

    private void validateUnitPrice(double unitPrice) throws SupplyChainException {
        if (unitPrice <= 0) {
            throw new SupplyChainException("Unit price must be greater than zero.");
        }
    }
}