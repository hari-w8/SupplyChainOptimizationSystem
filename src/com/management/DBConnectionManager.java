package com.management;

import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * DATABASE MODULE: DBConnectionManager
 *
 * Responsible for:
 *  - Reading DB config from database.properties file
 *  - Creating a JDBC connection to MySQL (via XAMPP)
 *  - Providing a shared connection to all Management classes
 *
 * JDBC URL format:
 *   jdbc:mysql://localhost:3306/supplychain
 *
 * database.properties file (in project root /lib folder):
 *   db.url=jdbc:mysql://localhost:3306/supplychain
 *   db.username=root
 *   db.password=
 *   db.driver=com.mysql.jdbc.Driver
 */
public class DBConnectionManager {

    private static Connection connection = null;

    /**
     * Returns a single shared Connection (Singleton pattern).
     * Creates one if it doesn't exist yet.
     *
     * @return a live JDBC Connection object
     * @throws SQLException           if DB is unreachable or credentials are wrong
     * @throws IOException            if properties file can't be read
     * @throws ClassNotFoundException if MySQL JDBC driver JAR is missing
     */
    public static Connection getConnection()
            throws SQLException, IOException, ClassNotFoundException {

        // Only create a new connection if one doesn't exist (or was closed)
        if (connection == null || connection.isClosed()) {
            // Step 1: Load the properties file
            Properties props = new Properties();
            InputStream input = new FileInputStream("lib/database.properties");
            props.load(input);

            String url      = props.getProperty("db.url");
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            String driver   = props.getProperty("db.driver");

            // Step 2: Load the MySQL JDBC driver class into memory
            Class.forName(driver);

            // Step 3: Create the actual connection
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/supplychain_db", "root", "");

            System.out.println("[DB] Connection established successfully.");
        }
        return connection;
    }

    /**
     * Closes the connection when the application shuts down.
     * Always call this in a finally block or shutdown hook.
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("[DB] Connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("[DB] Error closing connection: " + e.getMessage());
        }
    }
}




















//package com.management;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Properties;
//
//public class DBConnectionManager {
//
//    public static Connection getConnection() {
//        Connection connection = null;
//
//        try {
//            Properties properties = new Properties();
//
//            FileInputStream file = new FileInputStream("src/database.properties");
//            properties.load(file);
//
//            String url = properties.getProperty("db.url");
//            String username = properties.getProperty("db.username");
//            String password = properties.getProperty("db.password");
//            String driver = properties.getProperty("db.driver");
//
//            Class.forName(driver);
//
//            connection = DriverManager.getConnection(url, username, password);
//
//            System.out.println("Database Connected Successfully!");
//
//        } catch (IOException e) {
//            System.out.println("database.properties file not found!");
//            e.printStackTrace();
//
//        } catch (ClassNotFoundException e) {
//            System.out.println("MySQL JDBC Driver not found!");
//            e.printStackTrace();
//
//        } catch (SQLException e) {
//            System.out.println("Database Connection Failed!");
//            e.printStackTrace();
//        }
//
//        return connection;
//    }
//}