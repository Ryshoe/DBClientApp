package com.dbclientapp.appointment;

import com.dbclientapp.util.DataAccessObject;

import java.sql.*;
import java.util.List;

public class AppointmentDAO extends DataAccessObject<Appointment> {

    protected AppointmentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Appointment findById(int id) {
        return null;
    }

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public Appointment update(Appointment dto) {
        return null;
    }

    @Override
    public Appointment create(Appointment dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
/*
public class AppointmentDAO {


    public static void select() throws SQLException {
        String sql = "SELECT * FROM appointments";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int apptId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
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
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
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

    public static int update(int apptId, String title, String desc, String location,
                             String type, Timestamp start, Timestamp end, int custId,
                             int userId, int contactId) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, " +
                "Location = ?, Type = ?, Start = ?, End = ?, Customer_ID = ?, " +
                "User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, desc);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, start);
        ps.setTimestamp(6, end);
        ps.setInt(7, custId);
        ps.setInt(8, userId);
        ps.setInt(9, contactId);
        ps.setInt(10, apptId);

        return ps.executeUpdate();
    }

    public static int delete(int apptId) throws SQLException {
        String sql = "DELETE FROM appointments WHERE Appointment_ID = ?";
        PreparedStatement ps= DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setInt(1, apptId);

        return ps.executeUpdate();
    }
}
*/