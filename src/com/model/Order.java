package com.model;
public class Order{
	private String orderId;
	private String customerId;
	private String orderDate;
	private double totalAmount;
	private String status;
	
	public Order(String orderId,String customerId,String orderDate,double totalAmount,String status) {
		this.orderId=orderId;
		this.customerId=customerId;
		this.orderDate=orderDate;
		this.totalAmount=totalAmount;
		this.status=status;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
