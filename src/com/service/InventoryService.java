package com.service;

import com.exception.SupplyChainException;
import com.management.InventoryManagement;
import com.model.Inventory;
//import com.util.ApplicationUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * SERVICE CLASS: InventoryService
 */
public class InventoryService {

    private final InventoryManagement inventoryManagement = new InventoryManagement();

    public void addInventory(String productId,
                             int quantity)
            throws SupplyChainException {

        // Check if inventory already exists
        Inventory existing =
                inventoryManagement
                        .getInventoryByProductId(productId);

        if (existing != null) {

            // Update Existing Stock
            int newQty =
                    existing.getQuantityInStock()
                    + quantity;

            inventoryManagement.updateStock(
                    productId,
                    newQty);

            System.out.println(
                    "Stock increased for "
                    + productId
                    + " -> "
                    + newQty);

        }

        else {

            // Generate Inventory ID
            List<String> existingIds =
                    inventoryManagement
                            .getAllInventory()
                            .stream()
                            .map(Inventory::getInventoryId)
                            .collect(Collectors.toList());

            String newId = ApplicationUtil.generateInventoryId(existingIds);

            // Create New Inventory
            Inventory inv = new Inventory(newId,productId,quantity,ApplicationUtil.getCurrentDateTime());

            inventoryManagement.insertInventory(inv);

            System.out.println(
                    "New Inventory Added");
        }
    }

    public Inventory getInventoryByProductId(
            String productId)
            throws SupplyChainException {

        Inventory inv = inventoryManagement.getInventoryByProductId(productId);

        if (inv == null) {

            throw new SupplyChainException("No inventory record for product : "+ productId);
        }

        return inv;
    }

    public List<Inventory> getAllInventory()
            throws SupplyChainException {

        return inventoryManagement
                .getAllInventory();
    }

    public List<Inventory> getLowStockItems()
            throws SupplyChainException {

        List<Inventory> lowStock =
                inventoryManagement.getLowStockItems();

        if (lowStock.isEmpty()) {

            System.out.println("No low-stock items found.");
        }

        else 
        {
        System.out.println("LOW STOCK ALERT - "+ lowStock.size()+ " products need attention!");
        }

        return lowStock;
    }
}