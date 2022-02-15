package com.dbclientapp.appointment;

import com.dbclientapp.util.DataAccessObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends DataAccessObject<Appointment> {

    private static final String CREATE = "INSERT INTO appointments (Title, Description, " +
            "Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String READ_ONE = "SELECT Appointment_ID, Title, Description, " +
            "Location, Type, Start, End, Customer_ID, User_ID, Contact_ID " +
            "FROM appointments WHERE Appointment_ID = ?";

    private static final String READ_ALL = "SELECT * FROM appointments";

    private static final String UPDATE = "UPDATE appointments SET Title = ?, " +
            "Description = ?, Location = ?, Type = ?, Start = ?, End = ?, " +
            "Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";

    private static final String DELETE = "DELETE FROM appointments WHERE Appointment_ID = ?";

    public AppointmentDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Appointment findById(int id) {
        Appointment appointment = new Appointment();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                appointment.setId(rs.getInt("Appointment_ID"));
                appointment.setTitle(rs.getString("Title"));
                appointment.setDescription(rs.getString("Description"));
                appointment.setLocation(rs.getString("Location"));
                appointment.setType(rs.getString("Type"));
                appointment.setStart(rs.getTimestamp("Start"));
                appointment.setEnd(rs.getTimestamp("End"));
                appointment.setCustId(rs.getInt("Customer_ID"));
                appointment.setUserId(rs.getInt("User_ID"));
                appointment.setContactId(rs.getInt("Contact_ID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        List<Appointment> appointmentList = new ArrayList<>();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(rs.getInt("Appointment_ID"));
                appointment.setTitle(rs.getString("Title"));
                appointment.setDescription(rs.getString("Description"));
                appointment.setLocation(rs.getString("Location"));
                appointment.setType(rs.getString("Type"));
                appointment.setStart(rs.getTimestamp("Start"));
                appointment.setEnd(rs.getTimestamp("End"));
                appointment.setCustId(rs.getInt("Customer_ID"));
                appointment.setUserId(rs.getInt("User_ID"));
                appointment.setContactId(rs.getInt("Contact_ID"));
                appointmentList.add(appointment);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    @Override
    public Appointment update(Appointment dto) {
        Appointment appointment;
        try(PreparedStatement ps = this.connection.prepareStatement(UPDATE)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getDescription());
            ps.setString(3, dto.getLocation());
            ps.setString(4, dto.getType());
            ps.setTimestamp(5, dto.getStart());
            ps.setTimestamp(6, dto.getEnd());
            ps.setInt(7, dto.getCustId());
            ps.setInt(8, dto.getUserId());
            ps.setInt(9, dto.getContactId());
            ps.setInt(10, dto.getId());
            ps.execute();
            appointment = this.findById(dto.getId());
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointment;
    }

    @Override
    public Appointment create(Appointment dto) {
        try(PreparedStatement ps = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getTitle());
            ps.setString(2, dto.getDescription());
            ps.setString(3, dto.getLocation());
            ps.setString(4, dto.getType());
            ps.setTimestamp(5, dto.getStart());
            ps.setTimestamp(6, dto.getEnd());
            ps.setInt(7, dto.getCustId());
            ps.setInt(8, dto.getUserId());
            ps.setInt(9, dto.getContactId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int id = 0;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            return this.findById(id);
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try(PreparedStatement ps = this.connection.prepareStatement(DELETE)) {
            ps.setInt(1, id);
            ps.execute();
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
