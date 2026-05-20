package com.management;

import com.exception.SupplyChainException;
import com.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderManagement {

    // ORDER STATUS
    public static final String STATUS_NEW = "New";

    public static final String STATUS_CONFIRMED = "Confirmed";

    public static final String STATUS_DISPATCHED = "Dispatched";

    public static final String STATUS_FULFILLED = "Fulfilled";

    public static final String STATUS_CANCELLED = "Cancelled";



  
    // INSERT ORDER

    public void insertOrder(Order order)
            throws SupplyChainException {

        // Validation
        if (order.getCustomerId() == null ||
                order.getCustomerId().trim().isEmpty()) {

            throw new SupplyChainException(
                    "Customer ID required");
        }

        // Check amount
        if (order.getTotalAmount() <= 0) {

            throw new SupplyChainException(
                    "Invalid total amount");
        }


        // SQL Query
        String sql =
                "INSERT INTO `Order` " +
                "(orderId, customerId, orderDate, totalAmount, status) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            // Get database connection
            Connection conn =
                    DBConnectionManager.getConnection();

            // Prepare query
            PreparedStatement ps =
                    conn.prepareStatement(sql);

            // Set values
            ps.setString(1, order.getOrderId());

            ps.setString(2, order.getCustomerId());

            ps.setString(3, order.getOrderDate());

            ps.setDouble(4, order.getTotalAmount());

            ps.setString(5, order.getStatus());

            // Execute query
            ps.executeUpdate();

            System.out.println("Order inserted");

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Insert failed : " + e.getMessage());
        }
    }



  
    // CANCEL ORDER
 
    public void cancelOrder(String orderId)
            throws SupplyChainException {

        // Get order using order id
        Order order = getOrderById(orderId);

        // Check order exists
        if (order == null) {

            throw new SupplyChainException(
                    "Order not found");
        }

        // Get current status
        String status = order.getStatus();

        // Cannot cancel after dispatch
        if (status.equals(STATUS_DISPATCHED) ||
                status.equals(STATUS_FULFILLED) ||
                status.equals(STATUS_CANCELLED)) {

            throw new SupplyChainException(
                    "Order cannot be cancelled");
        }


        // SQL Query
        String sql =
                "UPDATE `Order` " +
                "SET status = ? " +
                "WHERE orderId = ?";

        try {

            Connection conn =
                    DBConnectionManager.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, STATUS_CANCELLED);

            ps.setString(2, orderId);

            ps.executeUpdate();

            System.out.println("Order cancelled");

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Cancel failed");
        }
    }



    // UPDATE STATUS

    public void updateStatus(String orderId,
                             String newStatus)
            throws SupplyChainException {

        String sql =
                "UPDATE `Order` " +
                "SET status = ? " +
                "WHERE orderId = ?";

        try {

            Connection conn =
                    DBConnectionManager.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, newStatus);

            ps.setString(2, orderId);

            ps.executeUpdate();

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Status update failed");
        }
    }



    
    // GET ORDER BY ID
 
    public Order getOrderById(String orderId)
            throws SupplyChainException {

        try {

            Connection conn =
                    DBConnectionManager.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "SELECT * FROM `Order` WHERE orderId = ?");

            ps.setString(1, orderId);

            ResultSet rs = ps.executeQuery();

            // If data found
            if (rs.next()) {

                return mapRow(rs);
            }

            return null;

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Fetch failed");
        }
    }



   
    // GET ORDERS BY CUSTOMER
 
    public List<Order> getOrdersByCustomer(String customerId)
            throws SupplyChainException {

        List<Order> list = new ArrayList<>();

        try {

            Connection conn =
                    DBConnectionManager.getConnection();

            PreparedStatement ps =
                    conn.prepareStatement(
                            "SELECT * FROM `Order` WHERE customerId = ?");

            ps.setString(1, customerId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                list.add(mapRow(rs));
            }

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Fetch failed");
        }

        return list;
    }



   
    // GET ALL ORDERS
   
    public List<Order> getAllOrders()
            throws SupplyChainException {

        List<Order> list = new ArrayList<>();

        try {

            Connection conn =
                    DBConnectionManager.getConnection();

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery("SELECT * FROM `Order`");

            while (rs.next()) {

                list.add(mapRow(rs));
            }

        } catch (Exception e) {

            throw new SupplyChainException(
                    "Fetch all failed");
        }

        return list;
    }



  
    // MAP RESULTSET TO OBJECT
   
    private Order mapRow(ResultSet rs)
            throws SQLException {

        return new Order(

                rs.getString("orderId"),

                rs.getString("customerId"),

                rs.getString("orderDate"),

                rs.getDouble("totalAmount"),

                rs.getString("status")
        );
    }
}