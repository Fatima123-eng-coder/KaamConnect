package com.kaamconnect.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/KaamConnect"
                    + "?useSSL=false"
                    + "&allowPublicKeyRetrieval=true"
                    + "&serverTimezone=UTC";

    private static final String USERNAME =
            "kaamconnect_user";

    private static final String PASSWORD =
            "KaamConnect@123";

    private static Connection connection;

    private DBConnection() {
    }

    public static Connection getConnection() {

        try {

            /*
             * Explicitly load the MySQL JDBC driver.
             */
            Class.forName(
                    "com.mysql.cj.jdbc.Driver"
            );

            if (connection == null ||
                    connection.isClosed()) {

                connection =
                        DriverManager.getConnection(
                                URL,
                                USERNAME,
                                PASSWORD
                        );

                System.out.println(
                        "Database connected successfully."
                );
            }

            return connection;

        } catch (ClassNotFoundException exception) {

            throw new IllegalStateException(
                    "MySQL Connector/J is not added to the project.",
                    exception
            );

        } catch (SQLException exception) {

            throw new IllegalStateException(
                    "Database connection failed. "
                            + "Check the database, username and password.",
                    exception
            );
        }
    }

    public static void closeConnection() {

        try {

            if (connection != null &&
                    !connection.isClosed()) {

                connection.close();
                connection = null;

                System.out.println(
                        "Database connection closed."
                );
            }

        } catch (SQLException exception) {

            exception.printStackTrace();
        }
    }
}