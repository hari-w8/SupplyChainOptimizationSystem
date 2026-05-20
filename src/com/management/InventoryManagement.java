package com.management;

import com.exception.SupplyChainException;
import com.model.Inventory;
//import com.util.ApplicationUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MANAGEMENT (DAO) CLASS: InventoryManagement
 *
 * Handles all SQL for the Inventory table:
 *  - insertInventory   → add stock record for a product
 *  - updateStock       → increase or decrease stock quantity
 *  - getByProductId    → get inventory for a specific product
 *  - getLowStockItems  → find products with stock below a threshold
 *  - getAllInventory    → full inventory list
 */
public class InventoryManagement {

    private static final int LOW_STOCK_THRESHOLD = 10; // items below this are flagged

    public void insertInventory(Inventory inventory) throws SupplyChainException {
        if (inventory.getQuantityInStock() < 0) {
            throw new SupplyChainException("Stock quantity cannot be negative.");
        }

        String sql = "INSERT INTO Inventory (inventoryId, productId, quantityInStock, lastUpdated) "
                   + "VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, inventory.getInventoryId());
            ps.setString(2, inventory.getProductId());
            ps.setInt(3, inventory.getQuantityInStock());
            ps.setString(4, inventory.getLastUpdated());
            ps.executeUpdate();
            System.out.println("[Inventory] Added for product: " + inventory.getProductId());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SupplyChainException("Product ID '" + inventory.getProductId() + "' does not exist.");
        } catch (Exception e) {
            throw new SupplyChainException("Failed to insert inventory: " + e.getMessage(), e);
        }
    }

    /**
     * Updates the quantity for an existing inventory record.
     * Also updates the lastUpdated timestamp.
     *
     * @param productId   the product whose stock to update
     * @param newQuantity the new absolute quantity (not a delta)
     */
    public void updateStock(String productId, int newQuantity) throws SupplyChainException {
        if (newQuantity < 0) {
            throw new SupplyChainException("Stock quantity cannot be negative.");
        }

        String sql = "UPDATE Inventory SET quantityInStock = ?, lastUpdated = ? WHERE productId = ?";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, newQuantity);
            ps.setString(2, ApplicationUtil.getCurrentDateTime());
            ps.setString(3, productId);

            int rows = ps.executeUpdate();
            if (rows == 0) {
                throw new SupplyChainException("No inventory record found for product: " + productId);
            }
            System.out.println("[Inventory] Updated stock for product: " + productId + " → " + newQuantity);
        } catch (SupplyChainException e) {
            throw e;
        } catch (Exception e) {
            throw new SupplyChainException("Failed to update stock: " + e.getMessage(), e);
        }
    }

    public Inventory getInventoryByProductId(String productId) throws SupplyChainException {
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Inventory WHERE productId = ?");
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            return null;
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching inventory: " + e.getMessage(), e);
        }
    }

    /**
     * Returns all inventory records where stock < LOW_STOCK_THRESHOLD.
     * These are flagged as critical for reorder.
     */
    public List<Inventory> getLowStockItems() throws SupplyChainException {
        List<Inventory> list = new ArrayList<>();
        String sql = "SELECT * FROM Inventory WHERE quantityInStock < ?";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, LOW_STOCK_THRESHOLD);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching low stock items: " + e.getMessage(), e);
        }
        return list;
    }

    public List<Inventory> getAllInventory() throws SupplyChainException {
        List<Inventory> list = new ArrayList<>();
        try {
            Connection conn = DBConnectionManager.getConnection();
            ResultSet rs = conn.createStatement()
                .executeQuery("SELECT * FROM Inventory ORDER BY productId");
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) 
        {
            throw new SupplyChainException("Error fetching inventory: " + e.getMessage(), e);
        }
        return list;
    }

    private Inventory mapRow(ResultSet rs) throws SQLException {
        return new Inventory(
            rs.getString("inventoryId"),
            rs.getString("productId"),
            rs.getInt("quantityInStock"),
            rs.getString("lastUpdated")
        );
    }
}
