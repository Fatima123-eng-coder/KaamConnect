package com.kaamconnect.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/KaamConnect";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "fatimasaleem";
    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection() {

        try {

            if (connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(
                        URL,
                        USERNAME,
                        PASSWORD
                );
            }

        } catch (SQLException e) {

            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }

        return connection;
    }

    public static void closeConnection() {

        try {

            if (connection != null && !connection.isClosed()) {

                connection.close();
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
}
