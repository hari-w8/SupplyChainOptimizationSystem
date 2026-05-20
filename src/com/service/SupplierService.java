package com.service;

import java.util.ArrayList;
import java.util.List;

import com.exception.SupplyChainException;
import com.model.Supplier;

public class SupplierService
{


    List<Supplier> supplierList = new ArrayList<>();


    public void addSupplier(
            String name,
            String contactPerson,
            String email,
            String phone)
            throws SupplyChainException
    {


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

        if(!email.matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$"))
        {
            throw new SupplyChainException(
                    "Invalid email");
        }

        if(!phone.matches("[0-9]{10}"))
        {
            throw new SupplyChainException(
                    "Phone number must contain 10 digits");
        }
        String firstPart ;
        if(name.length()>=4)
        {
               firstPart= name.substring(0,4).toUpperCase();
        }
        else
        {
        	firstPart=name.toUpperCase();
        }

        String lastPart =phone.substring(6);

        String supplierId = firstPart + lastPart;


        Supplier supplier =
                new Supplier(
                        supplierId,
                        name,
                        contactPerson,
                        email,
                        phone
                );


        supplierList.add(supplier);

        System.out.println(
                "Supplier Added Successfully");

        System.out.println(
                "Generated Supplier ID : "
                        + supplierId);
    }



    public Supplier getSupplierById(
            String supplierId)
            throws SupplyChainException
    {

        for(Supplier supplier : supplierList)
        {

            if(supplier.getSupplierId()
                    .equals(supplierId))
            {
                return supplier;
            }
        }

        throw new SupplyChainException(
                "Supplier not found");
    }



    public List<Supplier> getAllSuppliers()
    {
        return supplierList;
    }



    public void deleteSupplier(
            String supplierId)
            throws SupplyChainException
    {

        Supplier supplier =
                getSupplierById(
                        supplierId);
        

        supplierList.remove(supplier);

        System.out.println(
                "Supplier Deleted Successfully");
    }



    public List<Supplier> searchByName(
            String name)
    {

        List<Supplier> result =
                new ArrayList<>();

        for(Supplier supplier : supplierList)
        {

            if(supplier.getName()
                    .toLowerCase()
                    .contains(name.toLowerCase()))
            {
                result.add(supplier);
            }
        }

        return result;
    }
}