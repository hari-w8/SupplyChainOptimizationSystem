package com.model;

public class Warehouse {
	 private String warehouseId;
	    private String name;
	    private String location;
	    private int capacity;
	    private int availableSpace;
	    private int currentStock;
	    private String status;

	    public String getWarehouseId() {
	        return warehouseId;
	    }

	    public void setWarehouseId(String warehouseId) {
	        this.warehouseId = warehouseId;
	    }

	    public String getName() {
	        return name;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public String getLocation() {
	        return location;
	    }

	    public void setLocation(String location) {
	        this.location = location;
	    }

	    public int getCapacity() {
	        return capacity;
	    }

	    public void setCapacity(int capacity) {
	        this.capacity = capacity;
	    }

	    public int getAvailableSpace() {
	        return availableSpace;
	    }

	    public void setAvailableSpace(int availableSpace) {
	        this.availableSpace = availableSpace;
	    }

	    public int getCurrentStock() {
	        return currentStock;
	    }

	    public void setCurrentStock(int currentStock) {
	        this.currentStock = currentStock;
	    }

	    public String getStatus() {
	        return status;
	    }

	    public void setStatus(String status) {
	        this.status = status;
	    }
}
