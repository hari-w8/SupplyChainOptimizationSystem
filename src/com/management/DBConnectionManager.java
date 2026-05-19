package com.management;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnectionManager {

    public static Connection getConnection() {
        Connection connection = null;

        try {
            Properties properties = new Properties();

            FileInputStream file = new FileInputStream("database.properties");
            properties.load(file);

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");
            String driver = properties.getProperty("db.driver");

            Class.forName(driver);

            connection = DriverManager.getConnection(url, username, password);

            System.out.println("Database Connected Successfully!");

        } catch (IOException e) {
            System.out.println("database.properties file not found!");
            e.printStackTrace();

        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found!");
            e.printStackTrace();

        } catch (SQLException e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }

}


