package com.service;

import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.Supplier;
import com.util.ApplicationUtil;

public class SupplierService {
	private ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
    private static int supplierCount = 1001;

    public String generateId() {
        return ApplicationUtil.generateId("S",supplierCount++);
    }

    public void addSupplier(Supplier supplier) throws SupplyChainException {

        if (!ApplicationUtil.isValidEmail(supplier.getEmail())) {
            throw new SupplyChainException("Invalid email format!");
        }

        if (!ApplicationUtil.isValidPhone(supplier.getPhone())) {
            throw new SupplyChainException("Phone number must contain exactly 10 digits!");
        }

        supplierList.add(supplier);
        System.out.println("Supplier added successfully!");
    }

    public void viewSuppliers() {

        if (supplierList.isEmpty()) {
            System.out.println("No suppliers available.");
            return;
        }

        for (Supplier supplier : supplierList) {
            System.out.println("--------------------------------");
            supplier.displaySupplier();
        }
    }

    public boolean supplierExists(String supplierId) {

        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {
                return true;
            }
        }

        return false;
    }

    public Supplier getSupplierById(String supplierId) throws SupplyChainException {

        for (Supplier supplier : supplierList) {
            if (supplier.getSupplierId().equalsIgnoreCase(supplierId)) {
                return supplier;
            }
        }

        throw new SupplyChainException("Supplier not found!");
    }
}