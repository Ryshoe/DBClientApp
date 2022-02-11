package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactQuery {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int contactId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }
}
