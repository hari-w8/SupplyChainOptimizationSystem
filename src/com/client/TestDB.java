package com.client;

import java.sql.Connection;

import com.management.DBConnectionManager;

public class TestDB {

    public static void main(String[] args) {

        Connection connection = DBConnectionManager.getConnection();

        if (connection != null) {
            System.out.println("DB Test Success!");
        } else {
            System.out.println("DB Test Failed!");
        }
    }
}