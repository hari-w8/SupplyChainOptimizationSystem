package com.model;

public class Transportation
{
	private String shipmentId;
	private String orderId;
	private String carrierId;
	private String status;



public Transportation(String shipmentId, String orderId, String carrierId, String status) {
	
	this.shipmentId = shipmentId;
	this.orderId = orderId;
	this.carrierId = carrierId;
	this.status = status;
}

public String getShipmentId() {
	return shipmentId;
}

public void setShipmentId(String shipmentId) {
	this.shipmentId = shipmentId;
}

public String getOrderId() {
	return orderId;
}

public void setOrderId(String orderId) {
	this.orderId = orderId;
}

public String getCarrierId() {
	return carrierId;
}

public void setCarrierId(String carrierId) {
	this.carrierId = carrierId;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}
}