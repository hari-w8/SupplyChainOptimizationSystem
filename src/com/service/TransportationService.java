package com.service;

import com.model.Transportation;
import java.util.*;
public class TransportationService
{
 List<Transportation> list =new ArrayList<Transportation>();
 
 
 public void addTransportation(Transportation t)
 {
	 list.add(t);
	 System.out.println("Shipment added succesfully");
	 
		 
 }
 
 public void updateStatus(String newStatus,String shipmentId)
 {
	 if(list.isEmpty())
	 {
		 System.out.println("No shipments are found");
	 }
	 else
		
     {
	 for(Transportation t:list)
	 {
	 if(t.getShipmentId().equals(shipmentId))
			 t.setStatus(newStatus);
	 else
		 System.out.println("No order has placed by this shippingId");
	 }
	 }
 }
 
 public void viewAllShipments()
 {
	 if(list.isEmpty())
	 {
		 System.out.println("No shipments are found");
	 }
	 
	 for(Transportation t:list)
	 {
	  System.out.println(t);
	 }
 }
 
 public void viewShipmentsByOrderId(String OrderId)
 {
 
 if(list.isEmpty())
 {
	 System.out.println("No shipments are found");
 }
 else
 {
 for(Transportation t:list)
 {
 if(t.getOrderId().equals(OrderId))
	System.out.println(t);
 else
	 System.out.println("No order has placed by this OrderId");
 }
 }
 }
 
 public Transportation searchById(String shipId)
 {
	 if(list.isEmpty())
	 {
		 System.out.println("No shipments are found");
	 }
	 else
	 {
		 for(Transportation t: list)
		 {
			if( t. getShipmentId().equals(shipId))
			{
				return t;
			}
		 }
	 }
	 return null;
	 
 }
 
public void deleteShipmentsById(String ShipmentId)
{
	if(list.isEmpty())
	 {
		 System.out.println("No shipments are found");
	 }
	else
	{
		Transportation t=searchById(ShipmentId);
		if(t!=null)
		{
		list.remove(t);
		System.out.println("Shipment is deleted successfully");
		}
		else
		{
			System.out.println("Shipment is not found");
		}
			
		
	}
		
}

public String generateId()
{
	int c=100;
	return "TRANS"+(c++);
}
}