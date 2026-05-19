package com.model;

public class Warehouse{
	private String warehouseId;
	private String name;
	private String location;
	private int capacity;
	private int availableSpace;
	private int currentStock;
	private double storageUsage;
	private String status;
	
	//getter
	public String getWarehouseId() {
		return warehouseId;
}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public int getCapacity() {
		return capacity;
	}
	public int getAvailableSpace() {
		return availableSpace;
	}
	public int getCurrentStock() {
		return currentStock;
	}
	public double getStorageUsage() {
		return storageUsage;
	}
	public String getStatus() {
		return status;
	}

	//setter
	public void setWarehouseId(String warehouseId) {
		this.warehouseId=warehouseId;
	}
	public void setName(String name) {
		this.name=name;
	}
	public void setLocation(String location) {
		this.location=location;
	}
	public void setCapacity(int capacity) {
		this.capacity=capacity;
	}
	public void setavailableSpace(int availableSpace) {
		this.availableSpace=availableSpace;
	}
	public void setCurrentStock(int currentStock) {
		this.currentStock=currentStock;
	}
	public void setStorageUsage(double storageUsage) {
		this.storageUsage=storageUsage;
	}
	public void setStatus(String status) {
		this.status=status;
	}   
}
