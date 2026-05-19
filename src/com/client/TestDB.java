package com.client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.management.DBConnectionManager;

public class TestDB {

    public static void main(String[] args) {

        Connection connection = DBConnectionManager.getConnection();

        if (connection != null) {
            System.out.println("DB Test Success!");

            try {
                // ================= SUPPLIER =================
                System.out.println("\n========== SUPPLIER DATA ==========");
                PreparedStatement ps1 = connection.prepareStatement("SELECT * FROM Supplier");
                ResultSet rs1 = ps1.executeQuery();

                while (rs1.next()) {
                    System.out.println(
                        rs1.getString("supplierId") + " | " +
                        rs1.getString("name") + " | " +
                        rs1.getString("contactPerson") + " | " +
                        rs1.getString("email") + " | " +
                        rs1.getString("phone")
                    );
                }

                // ================= PRODUCT =================
                System.out.println("\n========== PRODUCT DATA ==========");
                PreparedStatement ps2 = connection.prepareStatement("SELECT * FROM Product");
                ResultSet rs2 = ps2.executeQuery();

                while (rs2.next()) {
                    System.out.println(
                        rs2.getString("productId") + " | " +
                        rs2.getString("name") + " | " +
                        rs2.getString("description") + " | " +
                        rs2.getDouble("unitPrice") + " | " +
                        rs2.getString("category") + " | " +
                        rs2.getString("supplierId")
                    );
                }

                // ================= ORDERS =================
                System.out.println("\n========== ORDERS DATA ==========");
                PreparedStatement ps3 = connection.prepareStatement("SELECT * FROM Orders");
                ResultSet rs3 = ps3.executeQuery();

                while (rs3.next()) {
                    System.out.println(
                        rs3.getString("orderId") + " | " +
                        rs3.getString("customerId") + " | " +
                        rs3.getString("orderDate") + " | " +
                        rs3.getDouble("totalAmount") + " | " +
                        rs3.getString("status")
                    );
                }

                // ================= INVENTORY =================
                System.out.println("\n========== INVENTORY DATA ==========");
                PreparedStatement ps4 = connection.prepareStatement("SELECT * FROM Inventory");
                ResultSet rs4 = ps4.executeQuery();

                while (rs4.next()) {
                    System.out.println(
                        rs4.getString("inventoryId") + " | " +
                        rs4.getString("productId") + " | " +
                        rs4.getInt("quantityInStock") + " | " +
                        rs4.getString("lastUpdated")
                    );
                }

                // ================= WAREHOUSE =================
                System.out.println("\n========== WAREHOUSE DATA ==========");
                PreparedStatement ps5 = connection.prepareStatement("SELECT * FROM Warehouse");
                ResultSet rs5 = ps5.executeQuery();

                while (rs5.next()) {
                    System.out.println(
                        rs5.getString("warehouseId") + " | " +
                        rs5.getString("name") + " | " +
                        rs5.getString("location") + " | " +
                        rs5.getInt("capacity")
                    );
                }

                // ================= TRANSPORTATION =================
                System.out.println("\n========== TRANSPORTATION DATA ==========");
                PreparedStatement ps6 = connection.prepareStatement("SELECT * FROM Transportation");
                ResultSet rs6 = ps6.executeQuery();

                while (rs6.next()) {
                    System.out.println(
                        rs6.getString("shipmentId") + " | " +
                        rs6.getString("orderId") + " | " +
                        rs6.getString("carrierId") + " | " +
                        rs6.getString("status")
                    );
                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            System.out.println("DB Test Failed!");
        }
    }
}
