package com.model;

public class Transportation
{
	private String shipmentId;
	private String orderId;

	private String carrierId;
	private String status;
	private String sourceLocation;
	private String destinationLocation;
	private String shipmentDate;
	private String deliveryDate;
	private double deliveryCharges;
	

public Transportation(String shipmentId, String orderId, String carrierId, String status, String sourceLocation,
		String destinationLocation, String shipmentDate, String deliveryDate, double deliveryCharges) {
	super();
	this.shipmentId = shipmentId;
	this.orderId = orderId;
	this.carrierId = carrierId;
	this.status = status;
	this.sourceLocation = sourceLocation;
	this.destinationLocation = destinationLocation;
	this.shipmentDate = shipmentDate;
	this.deliveryDate = deliveryDate;
	this.deliveryCharges = deliveryCharges;
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

public String getSourceLocation() {
	return sourceLocation;
}

public void setSourceLocation(String sourceLocation) {
	this.sourceLocation = sourceLocation;
}

public String getDestinationLocation() {
	return destinationLocation;
}

public void setDestinationLocation(String destinationLocation) {
	this.destinationLocation = destinationLocation;
}

public String getShipmentDate() {
	return shipmentDate;
}

public void setShipmentDate(String shipmentDate) {
	this.shipmentDate = shipmentDate;
}

public String getDeliveryDate() {
	return deliveryDate;
}

public void setDeliveryDate(String deliveryDate) {
	this.deliveryDate = deliveryDate;
}

public double getDeliveryCharges() {
	return deliveryCharges;
}

public void setDeliveryCharges(double deliveryCharges) {
	this.deliveryCharges = deliveryCharges;
}
}
