package com.management;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.exception.SupplyChainException;
import com.model.Supplier;

public class SupplierManagement
{

    Connection con = DBConnectionManager.getConnection();

    // INSERT SUPPLIER

    public void insertSupplier(
            Supplier supplier)
            throws SupplyChainException
    {

        try
        {

            String query =
                    "INSERT INTO supplier VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1,
                    supplier.getSupplierId());

            ps.setString(2,
                    supplier.getName());

            ps.setString(3,
                    supplier.getContactPerson());

            ps.setString(4,
                    supplier.getEmail());

            ps.setString(5,
                    supplier.getPhone());

            ps.executeUpdate();

            System.out.println(
                    "Supplier Inserted Successfully");

        }
        catch(Exception e)
        {
            throw new SupplyChainException(
                    "Unable to insert supplier");
        }
    }

    // GET SUPPLIER BY ID

    public Supplier getSupplierById(
            String supplierId)
            throws SupplyChainException
    {

        Supplier supplier = null;

        try
        {

            String query =
                    "SELECT * FROM supplier WHERE supplierId=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1,
                    supplierId);

            ResultSet rs =
                    ps.executeQuery();

            if(rs.next())
            {

                supplier =
                        new Supplier(
                                rs.getString("supplierId"),
                                rs.getString("name"),
                                rs.getString("contactPerson"),
                                rs.getString("email"),
                                rs.getString("phone")
                        );
            }

        }
        catch(Exception e)
        {
            throw new SupplyChainException(
                    "Unable to fetch supplier");
        }

        return supplier;
    }

    // VIEW ALL SUPPLIERS

    public List<Supplier> getAllSuppliers()
            throws SupplyChainException
    {

        List<Supplier> list =
                new ArrayList<>();

        try
        {

            String query =
                    "SELECT * FROM supplier";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next())
            {

                Supplier supplier =
                        new Supplier(
                                rs.getString("supplierId"),
                                rs.getString("name"),
                                rs.getString("contactPerson"),
                                rs.getString("email"),
                                rs.getString("phone")
                        );

                list.add(supplier);
            }

        }
        catch(Exception e)
        {
            throw new SupplyChainException(
                    "Unable to fetch suppliers");
        }

        return list;
    }

    // SEARCH BY NAME

    public List<Supplier> searchByName(
            String keyword)
            throws SupplyChainException
    {

        List<Supplier> list =
                new ArrayList<>();

        try
        {

            String query =
                    "SELECT * FROM supplier WHERE name LIKE ?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1,
                    "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next())
            {

                Supplier supplier =
                        new Supplier(
                                rs.getString("supplierId"),
                                rs.getString("name"),
                                rs.getString("contactPerson"),
                                rs.getString("email"),
                                rs.getString("phone")
                        );

                list.add(supplier);
            }

        }
        catch(Exception e)
        {
            throw new SupplyChainException(
                    "Search failed");
        }

        return list;
    }

    // DELETE SUPPLIER

    public void deleteSupplier(
            String supplierId)
            throws SupplyChainException
    {

        try
        {

            String query =
                    "DELETE FROM supplier WHERE supplierId=?";

            PreparedStatement ps =
                    con.prepareStatement(query);

            ps.setString(1,
                    supplierId);

            int rows =
                    ps.executeUpdate();

            if(rows > 0)
            {
                System.out.println(
                        "Supplier Deleted Successfully");
            }
            else
            {
                throw new SupplyChainException(
                        "Supplier ID not found");
            }

        }
        catch(Exception e)
        {
            throw new SupplyChainException(
                    "Unable to delete supplier");
        }
    }
}