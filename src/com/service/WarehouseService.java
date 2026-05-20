package com.service;

import com.exception.SupplyChainException;
import com.management.WarehouseManagement;
import com.model.Warehouse;
import com.util.ApplicationUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WarehouseService {

	
    private final WarehouseManagement warehouseManagement =
            new WarehouseManagement();

    private List<Warehouse> warehouseList =
            new ArrayList<>();

    // Status Constants
    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_MODERATELY_OCCUPIED = "Moderately Occupied";
    public static final String STATUS_NEAR_CAPACITY = "Near Capacity";
    public static final String STATUS_CAPACITY_CRITICAL = "Capacity Critical";
    public static final String STATUS_FULL = "Full";

    // Build Warehouse List
    public List<Warehouse> buildWarehouseList()
            throws SupplyChainException {

        warehouseList =
                warehouseManagement.getAllWarehouses();

        return warehouseList;
    }

    // Add Warehouse
    public void addWarehouse(String name,
                             String location,
                             int capacity)
            throws SupplyChainException {

        validateWarehouseInput(name, location, capacity);

        buildWarehouseList();

        List<String> existingIds = warehouseList.stream()
                .map(Warehouse::getWarehouseId)
                .collect(Collectors.toList());

        String warehouseId =
                ApplicationUtil.generateWarehouseId(existingIds);

        int currentStock = 0;

        int availableSpace =
                calculateAvailableSpace(capacity, currentStock);

        double storageUsage =
                calculateStorageUsage(capacity, currentStock);

        String status =
                determineStatus(storageUsage);

        String currentDate =
                ApplicationUtil.getCurrentTimestamp();

        Warehouse warehouse = new Warehouse(
                warehouseId,
                name.trim(),
                location.trim(),
                capacity,
                currentStock,
                availableSpace,
                storageUsage,
                status,
                currentDate,
                currentDate
        );

        warehouseManagement.insertWarehouse(warehouse);

        warehouseList.add(warehouse);

        System.out.println(
                "Warehouse Added : " + warehouseId);
    }

    // Get Warehouse By ID
    public Warehouse getWarehouseById(String warehouseId)
            throws SupplyChainException {

        if (warehouseId == null ||
                warehouseId.trim().isEmpty()) {

            throw new SupplyChainException(
                    "Warehouse ID cannot be empty.");
        }

        Warehouse warehouse =
                warehouseManagement.getWarehouseById(warehouseId);

        if (warehouse == null) {

            throw new SupplyChainException(
                    "Warehouse not found.");
        }

        return warehouse;
    }

    // Get All Warehouses
    public List<Warehouse> getAllWarehouses()
            throws SupplyChainException {

        return buildWarehouseList();
    }

    // Search By Location
    public List<Warehouse> searchByLocation(String location)
            throws SupplyChainException {

        if (location == null ||
                location.trim().isEmpty()) {

            throw new SupplyChainException(
                    "Location cannot be empty.");
        }

        return warehouseManagement
                .getWarehousesByLocation(location);
    }

    // Update Stock
    public void updateWarehouseStock(String warehouseId,
                                     int newStock)
            throws SupplyChainException {

        if (newStock < 0) {

            throw new SupplyChainException(
                    "Stock cannot be negative.");
        }

        Warehouse warehouse =
                getWarehouseById(warehouseId);

        validateCapacity(
                warehouse.getCapacity(),
                newStock);

        int availableSpace =
                calculateAvailableSpace(
                        warehouse.getCapacity(),
                        newStock);

        double storageUsage =
                calculateStorageUsage(
                        warehouse.getCapacity(),
                        newStock);

        String status =
                determineStatus(storageUsage);

        warehouse.setCurrentStock(newStock);
        warehouse.setAvailableSpace(availableSpace);
        warehouse.setStorageUsage(storageUsage);
        warehouse.setStatus(status);
        warehouse.setLastUpdated(
                ApplicationUtil.getCurrentTimestamp());

        warehouseManagement
                .updateWarehouseStock(warehouse);

        if (storageUsage >= 90.0) {

            generateOvercapacityAlert(warehouse);
        }

        System.out.println(
                "Warehouse Stock Updated.");
    }

    // Update Capacity
    public void updateCapacity(String warehouseId,
                               int newCapacity)
            throws SupplyChainException {

        if (newCapacity <= 0) {

            throw new SupplyChainException(
                    "Invalid capacity.");
        }

        Warehouse warehouse =
                getWarehouseById(warehouseId);

        if (newCapacity <
                warehouse.getCurrentStock()) {

            throw new SupplyChainException(
                    "Capacity less than current stock.");
        }

        int availableSpace =
                calculateAvailableSpace(
                        newCapacity,
                        warehouse.getCurrentStock());

        double storageUsage =
                calculateStorageUsage(
                        newCapacity,
                        warehouse.getCurrentStock());

        String status =
                determineStatus(storageUsage);

        warehouse.setCapacity(newCapacity);
        warehouse.setAvailableSpace(availableSpace);
        warehouse.setStorageUsage(storageUsage);
        warehouse.setStatus(status);
        warehouse.setLastUpdated(
                ApplicationUtil.getCurrentTimestamp());

        warehouseManagement
                .updateCapacity(warehouse);

        System.out.println(
                "Warehouse Capacity Updated.");
    }

    // Validate Capacity
    public void validateCapacity(int capacity,
                                 int stockLevel)
            throws SupplyChainException {

        if (stockLevel > capacity) {

            throw new SupplyChainException(
                    "Stock exceeds capacity.");
        }
    }

    // Monitor Storage Usage
    public void monitorStorageUsage()
            throws SupplyChainException {

        List<Warehouse> warehouses =
                buildWarehouseList();

        if (warehouses.isEmpty()) {

            System.out.println(
                    "No warehouse records found.");

            return;
        }

        for (Warehouse warehouse : warehouses) {

            System.out.printf(
                    "%-10s | %-20s | %.2f%% Used | %s%n",
                    warehouse.getWarehouseId(),
                    warehouse.getName(),
                    warehouse.getStorageUsage(),
                    warehouse.getStatus()
            );
        }
    }

    // Generate Alerts
    public void generateOvercapacityAlerts()
            throws SupplyChainException {

        List<Warehouse> warehouses =
                buildWarehouseList();

        boolean alertFound = false;

        for (Warehouse warehouse : warehouses) {

            if (warehouse.getStorageUsage() >= 90.0) {

                generateOvercapacityAlert(warehouse);

                alertFound = true;
            }
        }

        if (!alertFound) {

            System.out.println(
                    "All warehouses are safe.");
        }
    }

    // Calculate Available Space
    public int calculateAvailableSpace(int capacity,
                                       int currentStock) {

        return capacity - currentStock;
    }

    // Calculate Storage Usage
    public double calculateStorageUsage(int capacity,
                                        int currentStock) {

        if (capacity == 0) {

            return 0.0;
        }

        return ((double) currentStock / capacity) * 100;
    }

    // Determine Status
    public String determineStatus(double storageUsage) {

        if (storageUsage >= 100.0) {
            return STATUS_FULL;
        }

        if (storageUsage > 90.0) {
            return STATUS_CAPACITY_CRITICAL;
        }

        if (storageUsage >= 70.0) {
            return STATUS_NEAR_CAPACITY;
        }

        if (storageUsage >= 50.0) {
            return STATUS_MODERATELY_OCCUPIED;
        }

        return STATUS_AVAILABLE;
    }

    // Generate Alert
    private void generateOvercapacityAlert(
            Warehouse warehouse) {

        System.out.printf(
                "[ALERT] %s | %.2f%% Used | %s%n",
                warehouse.getWarehouseId(),
                warehouse.getStorageUsage(),
                warehouse.getStatus()
        );
    }

    // Input Validation
    private void validateWarehouseInput(String name,
                                        String location,
                                        int capacity)
            throws SupplyChainException {

        if (name == null ||
                name.trim().isEmpty()) {

            throw new SupplyChainException(
                    "Warehouse name cannot be empty.");
        }

        if (location == null ||
                location.trim().isEmpty()) {

            throw new SupplyChainException(
                    "Warehouse location cannot be empty.");
        }

        if (capacity <= 0) {

            throw new SupplyChainException(
                    "Capacity must be greater than zero.");
        }
    }

    // Getter
    public List<Warehouse> getWarehouseList() {

        return warehouseList;
    }
}