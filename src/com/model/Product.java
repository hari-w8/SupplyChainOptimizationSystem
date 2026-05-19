package com.model;
	import java.time.LocalDate;

	public class Product {

	    private String productId;
	    private String productName;
	    private String description;
	    private double price;
	    private String category;
	    private String supplierId;
	    private int quantity;
	    private LocalDate expiryDate;
	    private String warranty;

	    public Product() {
	    }

	    public Product(String productId,
	                   String productName,
	                   String description,
	                   double price,
	                   String category,
	                   String supplierId,
	                   int quantity,
	                   LocalDate expiryDate,
	                   String warranty) {

	        this.productId = productId;
	        this.productName = productName;
	        this.description = description;
	        this.price = price;
	        this.category = category;
	        this.supplierId = supplierId;
	        this.quantity = quantity;
	        this.expiryDate = expiryDate;
	        this.warranty = warranty;
	    }

	    public String getProductId() {
	        return productId;
	    }

	    public void setProductId(String productId) {
	        this.productId = productId;
	    }

	    public String getProductName() {
	        return productName;
	    }

	    public void setProductName(String productName) {
	        this.productName = productName;
	    }

	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description) {
	        this.description = description;
	    }

	    public double getPrice() {
	        return price;
	    }

	    public void setPrice(double price) {
	        this.price = price;
	    }

	    public String getCategory() {
	        return category;
	    }

	    public void setCategory(String category) {
	        this.category = category;
	    }

	    public String getSupplierId() {
	        return supplierId;
	    }

	    public void setSupplierId(String supplierId) {
	        this.supplierId = supplierId;
	    }

	    public int getQuantity() {
	        return quantity;
	    }

	    public void setQuantity(int quantity) {
	        this.quantity = quantity;
	    }

	    public LocalDate getExpiryDate() {
	        return expiryDate;
	    }

	    public void setExpiryDate(LocalDate expiryDate) {
	        this.expiryDate = expiryDate;
	    }

	    public String getWarranty() {
	        return warranty;
	    }

	    public void setWarranty(String warranty) {
	        this.warranty = warranty;
	    }

	    @Override
	    public String toString() {

	        return "Product ID : " + productId +
	                "Product Name : " + productName +
	                "Description : " + description +
	                "Price : " + price +
	                "Category : " + category +
	                "Supplier ID : " + supplierId +
	                "Quantity : " + quantity +
	                "Expiry Date : " + expiryDate +
	                "Warranty : " + warranty;
	    }
	}
