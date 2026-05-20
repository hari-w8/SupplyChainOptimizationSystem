package com.management;

import com.exception.SupplyChainException;
import com.model.Transportation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MANAGEMENT (DAO) CLASS: TransportationManagement
 */
public class TransportationManagement {

    public void insertShipment(Transportation transport) throws SupplyChainException {
        String sql = "INSERT INTO Transportation (shipmentId, orderId, carrierId, status,sourceLocation,destinationLocation,shipmentDate,deliveryDate,deliveryCharges) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, transport.getShipmentId());
            ps.setString(2, transport.getOrderId());
            ps.setString(3, transport.getCarrierId());
            ps.setString(4, transport.getStatus());
            ps.executeUpdate();
            System.out.println("[Transport] Shipment added: " + transport.getShipmentId());
        } catch (SQLIntegrityConstraintViolationException e) {
            throw new SupplyChainException("Order ID '" + transport.getOrderId() + "' does not exist.");
        } catch (Exception e) {
            throw new SupplyChainException("Failed to add shipment: " + e.getMessage());
        }
    }

    /**
     * Updates the shipment status.
     * Valid statuses: "Pending", "In Transit", "Delivered", "Returned"
     */
    public void updateShipmentStatus(String shipmentId, String newStatus) throws SupplyChainException {
        List<String> validStatuses = List.of("Pending", "In Transit", "Delivered", "Returned");
        if (!validStatuses.contains(newStatus)) {
            throw new SupplyChainException("Invalid status '" + newStatus + 
                "'. Use: Pending, In Transit, Delivered, Returned");
        }

        Transportation existing = getShipmentById(shipmentId);
        if (existing == null) {
            throw new SupplyChainException("Shipment ID '" + shipmentId + "' not found.");
        }

        // Prevent going backwards (e.g., Delivered → In Transit)
        if (existing.getStatus().equals("Delivered")) {
            throw new SupplyChainException("Cannot update status of an already-delivered shipment.");
        }

        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "UPDATE Transportation SET status = ? WHERE shipmentId = ?");
            ps.setString(1, newStatus);
            ps.setString(2, shipmentId);
            ps.executeUpdate();
            System.out.println("[Transport] Status updated: " + shipmentId + " → " + newStatus);
        } catch (Exception e) {
            throw new SupplyChainException("Failed to update shipment: " + e.getMessage());
        }
    }

    public Transportation getShipmentById(String shipmentId) throws SupplyChainException {
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Transportation WHERE shipmentId = ?");
            ps.setString(1, shipmentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            return null;
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching shipment: " + e.getMessage());
        }
    }

    public Transportation getShipmentByOrderId(String orderId) throws SupplyChainException {
        try {
            Connection conn = DBConnectionManager.getConnection();
            PreparedStatement ps = conn.prepareStatement(
                "SELECT * FROM Transportation WHERE orderId = ?");
            ps.setString(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return mapRow(rs);
            return null;
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching shipment by order: " + e.getMessage());
        }
    }

    public List<Transportation> getAllShipments() throws SupplyChainException {
        List<Transportation> list = new ArrayList<>();
        try {
            Connection conn = DBConnectionManager.getConnection();
            ResultSet rs = conn.createStatement()
                .executeQuery("SELECT * FROM Transportation ORDER BY shipmentId");
            while (rs.next()) list.add(mapRow(rs));
        } catch (Exception e) {
            throw new SupplyChainException("Error fetching shipments: " + e.getMessage());
        }
        return list;
    }

    private Transportation mapRow(ResultSet rs) throws SQLException {
        return new Transportation(
            rs.getString("shipmentId"),
            rs.getString("orderId"),
            rs.getString("carrierId"),
            rs.getString("status"),
            rs.getString("sourceLocation"),
            rs.getString("destinationLocation"),
            rs.getString("shipmentDate"),
            rs.getString("deliveryDate"),
            rs.getDouble("deliveryCharges")
            
        );
    }
}
