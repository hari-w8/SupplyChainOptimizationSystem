package com.model;

/**
 * MODEL CLASS: Inventory
 * Tracks how much stock is available for each product.
 * Updated automatically whenever an order is placed or stock is restocked.
 */
public class Inventory {

    private String inventoryId;
    private String productId;       // FK → Product.productId
    private int    quantityInStock;
    private String lastUpdated;     // Formatted: dd-MM-yyyy HH:mm:ss

    public Inventory() {}

    public Inventory(String inventoryId, String productId,
                     int quantityInStock, String lastUpdated) {
        this.inventoryId      = inventoryId;
        this.productId        = productId;
        this.quantityInStock  = quantityInStock;
        this.lastUpdated      = lastUpdated;
    }

    // ── Getters & Setters ──────────────────────────────────────────────────────
    public String getInventoryId()                       { return inventoryId; }
    public void   setInventoryId(String inventoryId)     { this.inventoryId = inventoryId; }

    public String getProductId()                   { return productId; }
    public void   setProductId(String productId)   { this.productId = productId; }

    public int  getQuantityInStock()                       { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock)    { this.quantityInStock = quantityInStock; }

    public String getLastUpdated()                     { return lastUpdated; }
    public void   setLastUpdated(String lastUpdated)   { this.lastUpdated = lastUpdated; }

    @Override
    public String toString() {
        return "Inventory [ID=" + inventoryId + ", ProductID=" + productId +
               ", Stock=" + quantityInStock + ", LastUpdated=" + lastUpdated + "]";
    }
}
