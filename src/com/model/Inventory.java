package com.model;

import java.time.LocalDateTime;

public class Inventory {

    private String packageinventoryId;
    private String productId;
    private int    quantityInStock;
    private LocalDateTime lastUpdated;

    public Inventory() {}

    public Inventory(String inventoryId, String productId,
                     int quantityInStock, LocalDateTime lastUpdated) {
        this.inventoryId     = inventoryId;
        this.productId       = productId;
        this.quantityInStock = quantityInStock;
        this.lastUpdated     = lastUpdated;
    }

    // Getters and Setters
    public String getInventoryId()                  { return inventoryId; }
    public void setInventoryId(String inventoryId)  { this.inventoryId = inventoryId; }

    public String getProductId()                    { return productId; }
    public void setProductId(String productId)      { this.productId = productId; }

    public int getQuantityInStock()                 { return quantityInStock; }
    public void setQuantityInStock(int qty)         { this.quantityInStock = qty; }

    public LocalDateTime getLastUpdated()           { return lastUpdated; }
    public void setLastUpdated(LocalDateTime dt)    { this.lastUpdated = dt; }

    public String toString() {
        return String.format(
            "Inventory [ID=%s, ProductID=%s, Qty=%d, LastUpdated=%s]",
            inventoryId, productId, quantityInStock, lastUpdated);
    }
}