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

    // Object creation for accessing order methods
    OrderManagement orderManagement = new OrderManagement();

    // Object creation for inventory methods
    InventoryManagement inventoryManagement = new InventoryManagement();


  
    // PLACE ORDER METHOD
  
    public void placeOrder(String customerId,
                           String productId,
                           int quantity,
                           double unitPrice)
            throws SupplyChainException {

        // Get inventory details using product id
        Inventory inv =
                inventoryManagement.getInventoryByProductId(productId);

        // If inventory not found
        if (inv == null) {
            throw new SupplyChainException(
                    "Product not available in inventory");
        }

 
        // Check stock availability
        if (inv.getQuantityInStock() < quantity) {

            throw new SupplyChainException(
                    "Insufficient stock");
        }

        // Get all existing orders
        List<String> existingOrderIds =
                orderManagement.getAllOrders()
                        .stream()
                        .map(Order::getOrderId)
                        .collect(Collectors.toList());

        // Generate new order id
        String newOrderId =
                ApplicationUtil.generateOrderId(existingOrderIds);



        // Calculate total amount
        double totalAmount = quantity * unitPrice;


        
        // Create order object
        Order order = new Order(
                newOrderId,
                customerId,
                ApplicationUtil.getCurrentDateTime(),
                totalAmount,
                OrderManagement.STATUS_NEW
        );


     
        // Insert order into database
        orderManagement.insertOrder(order);


     
        // Reduce stock after placing order
        int updatedStock =
                inv.getQuantityInStock() - quantity;

        inventoryManagement.updateStock(
                productId,
                updatedStock
        );

        System.out.println("Order placed successfully");
    }



   
    // CANCEL ORDER
   
    public void cancelOrder(String orderId)
            throws SupplyChainException {

        orderManagement.cancelOrder(orderId);
    }



   
    // GET ORDER BY ID
 
    public Order getOrderById(String orderId)
            throws SupplyChainException {

        Order order =
                orderManagement.getOrderById(orderId);

        if (order == null) {

            throw new SupplyChainException(
                    "Order not found");
        }

        return order;
    }

