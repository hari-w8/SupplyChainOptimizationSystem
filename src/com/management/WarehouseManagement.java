package com.service;

import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.Warehouse;
import com.util.ApplicationUtil;

public class WarehouseService {

    private ArrayList<Warehouse> warehouseList = new ArrayList<Warehouse>();
    private static int warehouseCount = 3001;

    public String generateWarehouseId() {
        return ApplicationUtil.generateId("W", warehouseCount++);
    }

    public void addWarehouse(Warehouse warehouse) throws SupplyChainException {

        if (warehouse.getCapacity() <= 0) {
            throw new SupplyChainException("Warehouse capacity must be greater than 0!");
        }

        warehouseList.add(warehouse);
        System.out.println("Warehouse added successfully!");
    }

    public void viewWarehouses() {

        if (warehouseList.isEmpty()) {
            System.out.println("No warehouses available.");
            return;
        }

        for (Warehouse warehouse : warehouseList) {
            System.out.println("--------------------------------");
            warehouse.displayWarehouse();
        }
    }
}