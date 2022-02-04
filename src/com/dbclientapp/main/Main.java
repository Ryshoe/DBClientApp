package com.dbclientapp.main;

import com.dbclientapp.helper.JDBC;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        JDBC.closeConnection();
    }
}
