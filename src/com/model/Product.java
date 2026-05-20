	 package com.model;

public class Product {

    private String productId;
    private String name;
    private String description;
    private double unitPrice;
    private String category;
    private String supplierId;

    public Product(String productId, String name, String description, double unitPrice, String category, String supplierId) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.unitPrice = unitPrice;
        this.category = category;
        this.supplierId = supplierId;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getCategory() {
        return category;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void displayProduct() {
        System.out.println("Product ID   : " + productId);
        System.out.println("Name         : " + name);
        System.out.println("Description  : " + description);
        System.out.println("Unit Price   : " + unitPrice);
        System.out.println("Category     : " + category);
        System.out.println("Supplier ID  : " + supplierId);
    }
}