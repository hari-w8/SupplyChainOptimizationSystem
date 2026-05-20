package com.model;

public class Transportation {

    private String shipmentId;
    private String orderId;
    private String carrierId;
    private String status;

    public Transportation(String shipmentId, String orderId, String carrierId, String status) {
        this.shipmentId = shipmentId;
        this.orderId = orderId;
        this.carrierId = carrierId;
        this.status = status;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getCarrierId() {
        return carrierId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void displayTransportation() {
        System.out.println("Shipment ID : " + shipmentId);
        System.out.println("Order ID    : " + orderId);
        System.out.println("Carrier ID  : " + carrierId);
        System.out.println("Status      : " + status);
    }
}