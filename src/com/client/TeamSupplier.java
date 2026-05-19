package com.client;

import com.service.SupplierService;

public class TeamSupplier
{

    public static void main(String[] args)
    {

        SupplierService service =
                new SupplierService();

        try
        {

            service.addSupplier(
                    "TechNova",
                    "Ravi",
                    "tech@gmail.com",
                    "9876543210"
            );

        }
        catch(Exception e)
        {
            System.out.println(
                    e.getMessage());
        }
    }
}