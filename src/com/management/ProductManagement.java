package com.management;

import com.exception.SupplyChainException;
import com.model.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement {

    
    //  INSERT
   
    public void insertProduct(Product product) throws SupplyChainException {
        // Validation before hitting the database
        if (product.getPrice() <= 0) {
            throw new SupplyChainException("Unit price must be greater than 0.");
        }
        if (!product.getCategory().equals("ConsumerGoods") &&
            !product.getCategory().equals("IndustrialGoods")) {
            throw new SupplyChainException("Category must be 'ConsumerGoods' or 'IndustrialGoods'.");
        }

        String sql = "INSERT INTO Product (productId, name, description, unitPrice, category, supplierId, quantity, expiryDate, warranty) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, product.getProductId());
            ps.setString(2, product.getProductName());
            ps.setString(3, product.getDescription());
            ps.setDouble(4, product.getPrice());
            ps.setString(5, product.getCategory());
            ps.setString(6, product.getSupplierId());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("[Product] Inserted: " + product.getProductId());
            }
        } catch (SQLIntegrityConstraintViolationException e) {
             throw new SupplyChainException("Supplier ID '" + product.getSupplierId() + "' does not exist.");
        } catch (Exception e) {
            throw new SupplyChainException("Failed to insert product: " + e.getMessage(), e);
        }
    }

    //  DELETE
    
   
    public void deleteProduct(String productId) throws SupplyChainException {
        // First check: does this product exist?
        Product existing = getProductById(productId);
        if (existing == null) {
            throw new SupplyChainException("Product ID '" + productId + "' not found.");
        }

        
        String checkSql = "SELECT COUNT(*) FROM Inventory WHERE productId = ?";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement check = conn.prepareStatement(checkSql);
            check.setString(1, productId);
            ResultSet rs = check.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                throw new SupplyChainException(
                    "Cannot delete product '" + productId + "': it is referenced in Inventory.");
            }

            // Safe to delete
            String deleteSql = "DELETE FROM Product WHERE productId = ?";
            PreparedStatement ps = conn.prepareStatement(deleteSql);
            ps.setString(1, productId);
            ps.executeUpdate();
            System.out.println("[Product] Deleted: " + productId);

        } catch (SupplyChainException e) {
            throw e; 
        } catch (Exception e) {
            throw new SupplyChainException("Failed to delete product: " + e.getMessage(), e);
        }
    }

    //  RETRIEVE BY ID

    public Product getProductById(String productId) throws SupplyChainException {
        String sql = "SELECT * FROM Product WHERE productId = ?";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, productId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapRow(rs);  
            }
            return null;  // not found
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching product: " + e.getMessage(), e);
        }
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  RETRIEVE ALL
    // ═══════════════════════════════════════════════════════════════════════════

    public List<Product> getAllProducts() throws SupplyChainException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product ORDER BY productId";
        try {
            Connection conn = DBConnectionManager.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching products: " + e.getMessage(), e);
        }
        return list;
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  RETRIEVE BY CATEGORY
    // ═══════════════════════════════════════════════════════════════════════════

    public List<Product> getProductsByCategory(String category) throws SupplyChainException {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product WHERE category = ?";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching products by category: " + e.getMessage(), e);
        }
        return list;
    }

    
    //  HELPER: mapRow
    //  Converts a single ResultSet row into a Product object
  

    private Product mapRow(ResultSet rs) throws SQLException {
        return new Product(
            rs.getString("productId"),
            rs.getString("name"),
            rs.getString("description"),
            rs.getDouble("unitPrice"),
            rs.getString("category"),
            rs.getString("supplierId"),
            rs.getInt("quantity"),
            rs.getString("expiryDate"),
            rs.getString("warranty")
            );
    }
}
