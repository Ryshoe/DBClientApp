package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserQuery {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int userId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }
}
