package com.model;

import java.time.LocalDateTime;

public class Inventory {

    private String inventoryId;
    private String productId;
    private int quantityInStock;
    private LocalDateTime lastUpdated;

    public Inventory(String inventoryId, String productId, int quantityInStock, LocalDateTime lastUpdated) {
        this.inventoryId = inventoryId;
        this.productId = productId;
        this.quantityInStock = quantityInStock;
        this.lastUpdated = lastUpdated;
    }

    public String getInventoryId() {
        return inventoryId;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void displayInventory() {
        System.out.println("Inventory ID      : " + inventoryId);
        System.out.println("Product ID        : " + productId);
        System.out.println("Quantity In Stock : " + quantityInStock);
        System.out.println("Last Updated      : " + lastUpdated);
    }
}