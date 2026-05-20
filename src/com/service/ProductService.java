package com.service;
import java.util.List;
import java.util.stream.Collectors;
import com.exception.SupplyChainException;
import com.management.*;
import com.model.*;
import com.util.ApplicationUtil;
/**
 * SERVICE CLASS: ProductService
 *
 * The SERVICE layer sits between the UserInterface and Management (DAO) layer.
 * It handles BUSINESS LOGIC:
 *  - ID generation before calling the DAO
 *  - Parsing CSV strings to Product objects
 *  - Applying extra business rules
 *
 * Flow:  UserInterface → ProductService → ProductManagement (DAO) → Database
 */
public class ProductService {

    // Service classes hold a reference to the DAO
    private final ProductManagement productManagement = new ProductManagement();

    // ═══════════════════════════════════════════════════════════════════════════
    //  ADD PRODUCT
    // ═══════════════════════════════════════════════════════════════════════════

    /**
     * Adds a new product.
     * Generates a unique ID automatically before inserting.
     */
    public void addProduct(String name, String description, double unitPrice,
                           String category, String supplierId) throws SupplyChainException {

        // Get all existing product IDs so we can calculate the next one
        List<String> existingIds = productManagement.getAllProducts()
            .stream().map(Product::getProductId).collect(Collectors.toList());

        String newId = ApplicationUtil.generateProductId(existingIds);

        // Create the appropriate subclass based on category
        Product product;
        if ("ConsumerGoods".equalsIgnoreCase(category)) {
            product = new Product.ConsumerGoods(newId, name, description, unitPrice, supplierId, "General");
        } else if ("IndustrialGoods".equalsIgnoreCase(category)) {
            product = new Product.IndustrialGoods(newId, name, description, unitPrice, supplierId, "General");
        } else {
            throw new SupplyChainException("Invalid category. Use 'ConsumerGoods' or 'IndustrialGoods'.");
        }

        productManagement.insertProduct(product);
        System.out.println("Product added successfully with ID: " + newId);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  DELETE PRODUCT
    // ═══════════════════════════════════════════════════════════════════════════

    public void deleteProduct(String productId) throws SupplyChainException {
        productManagement.deleteProduct(productId);
        System.out.println("Product deleted: " + productId);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  RETRIEVE
    // ═══════════════════════════════════════════════════════════════════════════

    public Product getProductById(String productId) throws SupplyChainException {
        Product p = productManagement.getProductById(productId);
        if (p == null) throw new SupplyChainException("Product not found: " + productId);
        return p;
    }

    public List<Product> getAllProducts() throws SupplyChainException {
        return productManagement.getAllProducts();
    }

    public List<Product> getProductsByCategory(String category) throws SupplyChainException {
        return productManagement.getProductsByCategory(category);
    }

    // ═══════════════════════════════════════════════════════════════════════════
    //  BUILD FROM CSV (bulk loading)
    //  CSV format: "name,description,unitPrice,category,supplierId"
    // ═══════════════════════════════════════════════════════════════════════════

    public void addProductFromCSV(String csvLine) throws SupplyChainException {
        List<String> fields = ApplicationUtil.parseCSV(csvLine);
        if (fields.size() < 5) {
            throw new SupplyChainException("Invalid CSV. Expected: name,description,price,category,supplierId");
        }

        String name        = fields.get(0);
        String description = fields.get(1);
        double unitPrice;
        try {
            unitPrice = Double.parseDouble(fields.get(2));
        } catch (NumberFormatException e) {
            throw new SupplyChainException("Invalid price value: " + fields.get(2));
        }
        String category   = fields.get(3);
        String supplierId = fields.get(4);

        addProduct(name, description, unitPrice, category, supplierId);
    }
}
