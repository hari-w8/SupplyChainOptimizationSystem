package com.service;

import java.util.ArrayList;

import com.exception.SupplyChainException;
import com.model.Order;
import com.model.Transportation;
import com.util.ApplicationUtil;

public class TransportationService {

    private ArrayList<Transportation> transportationList = new ArrayList<Transportation>();
    private static int shipmentCount = 7001;

    public String generateShipmentId() {
        return ApplicationUtil.generateId("T", shipmentCount++);
    }

    public void addShipment(Transportation transportation, OrderService orderService) throws SupplyChainException {

        if (!orderService.orderExists(transportation.getOrderId())) {
            throw new SupplyChainException("Invalid order ID. Place order first!");
        }

        if (!isValidStatus(transportation.getStatus())) {
            throw new SupplyChainException("Shipment status must be Pending, In Transit, or Delivered!");
        }

        transportationList.add(transportation);
        System.out.println("Shipment added successfully!");
    }

    public void viewShipments() {

        if (transportationList.isEmpty()) {
            System.out.println("No shipments available.");
            return;
        }

        for (Transportation transportation : transportationList) {
            System.out.println("--------------------------------");
            transportation.displayTransportation();
        }
    }

    public void updateShipmentStatus(String shipmentId, String status, OrderService orderService)
            throws SupplyChainException {

        if (!isValidStatus(status)) {
            throw new SupplyChainException("Shipment status must be Pending, In Transit, or Delivered!");
        }

        for (Transportation transportation : transportationList) {
            if (transportation.getShipmentId().equalsIgnoreCase(shipmentId)) {

                transportation.setStatus(status);

                if (status.equalsIgnoreCase("Delivered")) {
                    Order order = orderService.getOrderById(transportation.getOrderId());
                    order.setStatus("Delivered");
                }

                System.out.println("Shipment status updated successfully!");
                return;
            }
        }

        throw new SupplyChainException("Shipment not found!");
    }

    private boolean isValidStatus(String status) {
        return status.equalsIgnoreCase("Pending")
                || status.equalsIgnoreCase("In Transit")
                || status.equalsIgnoreCase("Delivered");
    }
}