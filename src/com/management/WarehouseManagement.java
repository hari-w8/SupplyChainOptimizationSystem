/*package com.management;

import com.exception.SupplyChainException;
import com.model.Warehouse;
import com.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseManagement {

    // Insert Warehouse
    public void insertWarehouse(Warehouse warehouse)
            throws SupplyChainException {

        String query =
                "INSERT INTO warehouse VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setString(
                    1, warehouse.getWarehouseId());

            preparedStatement.setString(
                    2, warehouse.getName());

            preparedStatement.setString(
                    3, warehouse.getLocation());

            preparedStatement.setInt(
                    4, warehouse.getCapacity());

            preparedStatement.setInt(
                    5, warehouse.getCurrentStock());

            preparedStatement.setInt(
                    6, warehouse.getAvailableSpace());

            preparedStatement.setDouble(
                    7, warehouse.getStorageUsage());

            preparedStatement.setString(
                    8, warehouse.getStatus());

            preparedStatement.setString(
                    9, warehouse.getCreatedDate());

            preparedStatement.setString(
                    10, warehouse.getLastUpdated());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error inserting warehouse.");
        }
    }

    // Get Warehouse By ID
    public Warehouse getWarehouseById(String warehouseId)
            throws SupplyChainException {

        String query =
                "SELECT * FROM warehouse WHERE warehouseId = ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setString(1, warehouseId);

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            if (resultSet.next()) {

                return extractWarehouse(resultSet);
            }

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error fetching warehouse.");
        }

        return null;
    }

    // Get All Warehouses
    public List<Warehouse> getAllWarehouses()
            throws SupplyChainException {

        List<Warehouse> warehouseList =
                new ArrayList<>();

        String query =
                "SELECT * FROM warehouse";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query);

             ResultSet resultSet =
                     preparedStatement.executeQuery()) {

            while (resultSet.next()) {

                warehouseList.add(
                        extractWarehouse(resultSet));
            }

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error fetching warehouses.");
        }

        return warehouseList;
    }

    // Search By Location
    public List<Warehouse> getWarehousesByLocation(
            String location)
            throws SupplyChainException {

        List<Warehouse> warehouseList =
                new ArrayList<>();

        String query =
                "SELECT * FROM warehouse WHERE LOWER(location) LIKE ?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setString(
                    1,
                    "%" + location.toLowerCase() + "%");

            ResultSet resultSet =
                    preparedStatement.executeQuery();

            while (resultSet.next()) {

                warehouseList.add(
                        extractWarehouse(resultSet));
            }

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error searching warehouses.");
        }

        return warehouseList;
    }

    // Update Warehouse Stock
    public void updateWarehouseStock(Warehouse warehouse)
            throws SupplyChainException {

        String query =
                "UPDATE warehouse SET currentStock=?, availableSpace=?, storageUsage=?, status=?, lastUpdated=? WHERE warehouseId=?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(
                    1, warehouse.getCurrentStock());

            preparedStatement.setInt(
                    2, warehouse.getAvailableSpace());

            preparedStatement.setDouble(
                    3, warehouse.getStorageUsage());

            preparedStatement.setString(
                    4, warehouse.getStatus());

            preparedStatement.setString(
                    5, warehouse.getLastUpdated());

            preparedStatement.setString(
                    6, warehouse.getWarehouseId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error updating stock.");
        }
    }

    // Update Capacity
    public void updateCapacity(Warehouse warehouse)
            throws SupplyChainException {

        String query =
                "UPDATE warehouse SET capacity=?, availableSpace=?, storageUsage=?, status=?, lastUpdated=? WHERE warehouseId=?";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement preparedStatement =
                     connection.prepareStatement(query)) {

            preparedStatement.setInt(
                    1, warehouse.getCapacity());

            preparedStatement.setInt(
                    2, warehouse.getAvailableSpace());

            preparedStatement.setDouble(
                    3, warehouse.getStorageUsage());

            preparedStatement.setString(
                    4, warehouse.getStatus());

            preparedStatement.setString(
                    5, warehouse.getLastUpdated());

            preparedStatement.setString(
                    6, warehouse.getWarehouseId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {

            throw new SupplyChainException(
                    "Error updating capacity.");
        }
    }

    // Extract Warehouse Object
    private Warehouse extractWarehouse(ResultSet resultSet)
            throws SQLException {

        return new Warehouse(
                resultSet.getString("warehouseId"),
                resultSet.getString("name"),
                resultSet.getString("location"),
                resultSet.getInt("capacity"),
                resultSet.getInt("currentStock"),
                resultSet.getInt("availableSpace"),
                resultSet.getDouble("storageUsage"),
                resultSet.getString("status"),
                resultSet.getString("createdDate"),
                resultSet.getString("lastUpdated")
        );
    }
}*/
