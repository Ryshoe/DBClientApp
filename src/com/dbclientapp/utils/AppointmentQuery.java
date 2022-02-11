package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class AppointmentQuery {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int apptId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static int insert(int apptId, String title, String desc,
                             String location, String type, Timestamp start,
                             Timestamp end, int custId, int userId, int contactId) throws SQLException {
        String sql = "INSERT INTO appointments (Appointment_ID, Title, " +
                "Description, Location, Type, Start, End, Customer_ID, " +
                "User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptId);
        ps.setString(2, title);
        ps.setString(3, desc);
        ps.setString(4, location);
        ps.setString(5, type);
        ps.setTimestamp(6, start);
        ps.setTimestamp(7, end);
        ps.setInt(8, custId);
        ps.setInt(9, userId);
        ps.setInt(10, contactId);

        return ps.executeUpdate();
    }
}
