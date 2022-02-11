package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerQuery {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM customers";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int custId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM customers WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static int insert(int custId, String custName, String address,
                             String postal, String phone, int divisionId) throws SQLException {
        String sql = "INSERT INTO customers (Customer_ID, Customer_Name, " +
                "Address, Postal_Code, Phone, Division_ID)" +
                "VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custId);
        ps.setString(2, custName);
        ps.setString(3, address);
        ps.setString(4, postal);
        ps.setString(5, phone);
        ps.setInt(6, divisionId);

        return ps.executeUpdate();
    }
}
