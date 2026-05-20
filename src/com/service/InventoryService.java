package com.service;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.Inventory;
import com.util.ApplicationUtil;

public class InventoryService {

    private ArrayList<Inventory> inventoryList = new ArrayList<Inventory>();
    private static int inventoryCount = 1001;

    public String generateInventoryId() {
        return ApplicationUtil.generateId("I", inventoryCount++);
    }

    public void addInventory(Inventory inventory, ProductService productService) throws SupplyChainException {

        if (!productService.productExists(inventory.getProductId())) {
            throw new SupplyChainException("Invalid product ID. Add product first!");
        }

        if (inventory.getQuantityInStock() < 0) {
            throw new SupplyChainException("Quantity cannot be negative!");
        }

        for (Inventory inv : inventoryList) {
            if (inv.getProductId().equalsIgnoreCase(inventory.getProductId())) {
                int newQty = inv.getQuantityInStock() + inventory.getQuantityInStock();
                inv.setQuantityInStock(newQty);
                inv.setLastUpdated(LocalDateTime.now());

                System.out.println("Inventory already exists. Stock updated successfully!");
                return;
            }
        }

        inventoryList.add(inventory);
        System.out.println("Inventory added successfully!");
    }

    public void viewInventory() {

        if (inventoryList.isEmpty()) {
            System.out.println("No inventory available.");
            return;
        }

        for (Inventory inventory : inventoryList) {
            System.out.println("--------------------------------");
            inventory.displayInventory();
        }
    }

    public int getStockByProductId(String productId) throws SupplyChainException {

        for (Inventory inventory : inventoryList) {
            if (inventory.getProductId().equalsIgnoreCase(productId)) {
                return inventory.getQuantityInStock();
            }
        }

        throw new SupplyChainException("Inventory not found for this product!");
    }

    public void reduceStock(String productId, int quantity) throws SupplyChainException {

        for (Inventory inventory : inventoryList) {
            if (inventory.getProductId().equalsIgnoreCase(productId)) {

                if (quantity > inventory.getQuantityInStock()) {
                    throw new SupplyChainException("Insufficient stock!");
                }

                inventory.setQuantityInStock(inventory.getQuantityInStock() - quantity);
                inventory.setLastUpdated(LocalDateTime.now());

                System.out.println("Stock updated successfully!");
                return;
            }
        }

        throw new SupplyChainException("Inventory not found for this product!");
    }
}