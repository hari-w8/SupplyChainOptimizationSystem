package com.management;

/**
 * DBConnectionManager handles the lifecycle of the database connection.
 * It implements the Singleton pattern to ensure only one connection pool/instance 
 * manages the database resource across the Supply Chain application.
 */
public class DBConnectionManager {

    // Database credentials and configuration
    private static final String URL = "jdbc:mysql://localhost:3306/supply_chain_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password123";

    // The single instance of this manager
    private static DBConnectionManager instance;
    private java.sql.Connection connection;

    // Private constructor prevents instantiation from other classes
    private DBConnectionManager() {
        try {
            // Explicitly load the JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("LOG: Successfully connected to the Supply Chain Database.");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: MySQL JDBC Driver not found. Add it to your classpath.");
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            System.err.println("ERROR: Failed to establish a database connection.");
            e.printStackTrace();
        }
    }

    /**
     * Public access point to get the single instance of DBConnectionManager.
     * Uses double-checked locking for thread safety.
     */
    public static DBConnectionManager getInstance() {
        if (instance == null) {
            synchronized (DBConnectionManager.class) {
                if (instance == null) {
                    instance = new DBConnectionManager();
                }
            }
        }
        return instance;
    }

    /**
     * Provides the active database connection. 
     * Re-establishes connection if it was closed or dropped.
     */
    public java.sql.Connection getConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("LOG: Connection was closed. Re-opening...");
                this.connection = java.sql.DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (java.sql.SQLException e) {
            System.err.println("ERROR: Could not reconnect to database.");
            e.printStackTrace();
        }
        return this.connection;
    }

    /**
     * Gracefully closes the database connection when the application shuts down.
     */
    public void closeConnection() {
        if (this.connection != null) {
            try {
                this.connection.close();
                System.out.println("LOG: Database connection closed cleanly.");
            } catch (java.sql.SQLException e) {
                System.err.println("ERROR: Failed to close database connection safely.");
                e.printStackTrace();
            }
        }
    }
}