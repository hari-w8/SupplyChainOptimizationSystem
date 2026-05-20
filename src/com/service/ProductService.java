package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import com.exception.SupplyChainException;
import com.model.Product;
import com.util.ApplicationUtil;

public class ProductService {

    // Product Storage
    private List<Product> productList = new ArrayList<>();

    // =========================================
    // ADD PRODUCT
    // =========================================

    public void addProduct(String productId,
    		               String productName,
                           String description,
                           double price,
                           String category,
                           String supplierId,
                           int quantity,
                   	       String expiryDate,
                   	       String warranty)
            throws SupplyChainException {

        // Validation
        if(productName == null || productName.isEmpty()) {
            throw new SupplyChainException(
                    "Product name cannot be empty"
            );
        }

        if(price <= 0) {
            throw new SupplyChainException(
                    "Invalid Product Price"
            );
        }

        // Existing IDs
        List<String> existingIds = productList
                .stream()
                .map(Product::getProductId)
                .collect(Collectors.toList());

        // Auto Generate Product ID
        String newId =
                ApplicationUtil.generateProductId(
                        existingIds
                );

        Product product;

        // Category Wise Object Creation
        if("ConsumerGoods"
                .equalsIgnoreCase(category)) {

            product = new Product(
            		productId,
            	    productName,
            	    description,
            	    price,
            	    category,
            	    supplierId,
            	    quantity,
            	    expiryDate,
            	    warranty
            );

        }
        else if("IndustrialGoods"
                .equalsIgnoreCase(category)) {

            product = new Product(
            		productId,
            	    productName,
            	    description,
            	    price,
            	    category,
            	    supplierId,
            	    quantity,
            	    expiryDate,
            	    warranty
            );
        }
        else {

            throw new SupplyChainException(
                    "Invalid Category"
            );
        }

        // Add Product
        productList.add(product);

        System.out.println(
                "Product Added Successfully : "
                        + newId
        );
    }

    // =========================================
    // DELETE PRODUCT
    // =========================================

    public void deleteProduct(String productId)
            throws SupplyChainException {

        Product product =
                getProductById(productId);

        productList.remove(product);

        System.out.println(
                "Product Deleted : "
                        + productId
        );
    }

    // =========================================
    // GET PRODUCT BY ID
    // =========================================

    public Product getProductById(
            String productId)
            throws SupplyChainException {

        for(Product p : productList) {

            if(p.getProductId()
                    .equalsIgnoreCase(productId)) {

                return p;
            }
        }

        throw new SupplyChainException(
                "Product Not Found : "
                        + productId
        );
    }

    // =========================================
    // GET ALL PRODUCTS
    // =========================================

    public List<Product> getAllProducts() {

        return productList;
    }

    // =========================================
    // GET PRODUCTS BY CATEGORY
    // =========================================

    public List<Product> getProductsByCategory(
            String category)
            throws SupplyChainException {

        List<Product> filteredProducts =
                new ArrayList<>();

        for(Product p : productList) {

            if(p.getCategory()
                    .equalsIgnoreCase(category)) {

                filteredProducts.add(p);
            }
        }

        if(filteredProducts.isEmpty()) {

            throw new SupplyChainException(
                    "No Products Found In Category : "
                            + category
            );
        }

        return filteredProducts;
    }
}