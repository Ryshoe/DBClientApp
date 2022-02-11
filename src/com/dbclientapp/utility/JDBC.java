package com.dbclientapp.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class JDBC {
    private static final String PROTOCOL = "jdbc";
    private static final String VENDOR = ":mysql:";
    private static final String LOCATION = "//localhost/";
    private static final String DATABASENAME = "client_schedule";
    private static final String JDBCURL = PROTOCOL + VENDOR + LOCATION + DATABASENAME + "?connectionTimeZone = SERVER"; // LOCAL
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver reference
    private static final String USERNAME = "sqlUser"; // Username
    private static final String PASSWORD = "Passw0rd!"; // Password
    public static Connection connection;  // Connection Interface

    public static void openConnection()
    {
        try {
            Class.forName(DRIVER); // Locate Driver
            connection = DriverManager.getConnection(JDBCURL, USERNAME, PASSWORD); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }
}
