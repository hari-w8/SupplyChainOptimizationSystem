package com.model;

public class Warehouse {

    private String warehouseId;
    private String name;
    private String location;
    private int capacity;

    public Warehouse(String warehouseId, String name, String location, int capacity) {
        this.warehouseId = warehouseId;
        this.name = name;
        this.location = location;
        this.capacity = capacity;
    }

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

    public void displayWarehouse() {
        System.out.println("Warehouse ID : " + warehouseId);
        System.out.println("Name         : " + name);
        System.out.println("Location     : " + location);
        System.out.println("Capacity     : " + capacity);
    }
}