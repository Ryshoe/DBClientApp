package com.dbclientapp.main;

import com.dbclientapp.utility.JDBC;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        JDBC.openConnection();
        JDBC.closeConnection();
    }
}
