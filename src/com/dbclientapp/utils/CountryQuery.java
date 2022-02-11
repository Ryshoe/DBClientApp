package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryQuery {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int countryId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }
}
