package com.service;

import java.util.List;

import com.exception.SupplyChainException;
import com.management.SupplierManagement;
import com.model.Supplier;

public class SupplierService
{

    SupplierManagement management =
            new SupplierManagement();

    // ADD SUPPLIER

    public void addSupplier(
            String name,
            String contactPerson,
            String email,
            String phone)
            throws SupplyChainException
    {

        // VALIDATION

        if(name.isEmpty())
        {
            throw new SupplyChainException(
                    "Company name cannot be empty");
        }

        if(contactPerson.isEmpty())
        {
            throw new SupplyChainException(
                    "Contact person cannot be empty");
        }

        if(!email.contains("@"))
        {
            throw new SupplyChainException(
                    "Invalid email");
        }

        if(phone.length() != 10)
        {
            throw new SupplyChainException(
                    "Phone number must be 10 digits");
        }

        // GENERATE SUPPLIER ID

        String firstPart =
                name.substring(0,4).toUpperCase();

        String lastPart =
                phone.substring(6);

        String supplierId =
                firstPart + lastPart;

        // CREATE OBJECT

        Supplier supplier =
                new Supplier(
                        supplierId,
                        name,
                        contactPerson,
                        email,
                        phone
                );

        // INSERT INTO DATABASE

        management.insertSupplier(
                supplier);

        System.out.println(
                "Supplier Added Successfully");

        System.out.println(
                "Generated Supplier ID : "
                        + supplierId);
    }

    // GET SUPPLIER BY ID

    public Supplier getSupplierById(
            String supplierId)
            throws SupplyChainException
    {

        Supplier supplier =
                management.getSupplierById(
                        supplierId);

        if(supplier == null)
        {
            throw new SupplyChainException(
                    "Supplier not found");
        }

        return supplier;
    }

    // VIEW ALL SUPPLIERS

    public List<Supplier> getAllSuppliers()
    		 throws SupplyChainException
    {
        return management.getAllSuppliers();
    }

    // DELETE SUPPLIER

    public void deleteSupplier(
            String supplierId)
            throws SupplyChainException
    {

        Supplier supplier =
                management.getSupplierById(
                        supplierId);

        if(supplier == null)
        {
            throw new SupplyChainException(
                    "Supplier ID not found");
        }

        management.deleteSupplier(
                supplierId);

        System.out.println(
                "Supplier Deleted Successfully");
    }

    // SEARCH BY NAME

    public List<Supplier> searchByName(
            String name)
            		 throws SupplyChainException
    {
        return management.searchByName(name);
    } 
}
