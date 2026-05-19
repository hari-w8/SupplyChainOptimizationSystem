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
	private String createdDate;
	private String lastUpdated;
	
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
	public String getCreatedDate() {
		return createdDate;
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
	public void setCreatedDate(String createdDate) {
		this.createdDate=createdDate;
	}
	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated=lastUpdated;
	}
	
	//constructor
	public Warehouse(String warehouseId, String name, String location, int capacity,
            int currentStock, int availableSpace, double storageUsage,
            String status, String createdDate, String lastUpdated) {
this.warehouseId    = warehouseId;
this.name           = name;
this.location       = location;
this.capacity       = capacity;
this.currentStock   = currentStock;
this.availableSpace = availableSpace;
this.storageUsage   = storageUsage;
this.status         = status;
this.createdDate    = createdDate;
this.lastUpdated    = lastUpdated;
}
}
