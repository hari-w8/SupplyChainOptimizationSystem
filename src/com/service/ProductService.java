package com.service;

import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.ConsumerGoods;
import com.model.IndustrialGoods;
import com.model.Product;
import com.util.ApplicationUtil;

public class ProductService {

    private ArrayList<Product> productList = new ArrayList<Product>();
    private static int productCount = 1001;

    public String generateProductId() {
        return ApplicationUtil.generateId("P", productCount++);
    }

    public Product createProduct(String id, String name, String description,
                                 double unitPrice, String category, String supplierId) {

        if (category.equalsIgnoreCase("ConsumerGoods")) {
            return new ConsumerGoods(id, name, description, unitPrice, supplierId);
        } else {
            return new IndustrialGoods(id, name, description, unitPrice, supplierId);
        }
    }

    public void addProduct(Product product, SupplierService supplierService) throws SupplyChainException {

        if (product.getUnitPrice() <= 0) {
            throw new SupplyChainException("Unit price must be greater than 0!");
        }

        if (!supplierService.supplierExists(product.getSupplierId())) {
            throw new SupplyChainException("Invalid supplier ID. Add supplier first!");
        }

        productList.add(product);
        System.out.println("Product added successfully!");
    }

    public void viewProducts() {

        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        for (Product product : productList) {
            System.out.println("--------------------------------");
            product.displayProduct();
        }
    }

    public Product getProductById(String productId) throws SupplyChainException {

        for (Product product : productList) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return product;
            }
        }

        throw new SupplyChainException("Product not found!");
    }

    public double getProductPrice(String productId) throws SupplyChainException {
        return getProductById(productId).getUnitPrice();
    }

    public boolean productExists(String productId) {

        for (Product product : productList) {
            if (product.getProductId().equalsIgnoreCase(productId)) {
                return true;
            }
        }

        return false;
    }
}