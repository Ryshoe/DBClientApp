package com.dbclientapp.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Handles driver for connecting to provided SQL database.
 */
public abstract class DatabaseConnectionManager {
    private static final String PROTOCOL = "jdbc";
    private static final String VENDOR = ":mysql:";
    private static final String LOCATION = "//localhost/";
    private static final String DATABASENAME = "client_schedule";
    private static final String JDBCURL = PROTOCOL + VENDOR + LOCATION + DATABASENAME + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String USERNAME = "sqlUser"; // Username
    private static final String PASSWORD = "Passw0rd!"; // Password
    public static Connection connection;  // Connection interface

    /**
     * Opens connection to database.
     * @return connection as object
     */
    public static Connection openConnection() {
        try {
            Class.forName(DRIVER); // Locate driver
            connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD);
        }
        catch(Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
        return connection;
    }

    /**
     * Closes connection to database.
     */
    public static void closeConnection() {
        try {
            connection.close();
        } catch(Exception e) {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
