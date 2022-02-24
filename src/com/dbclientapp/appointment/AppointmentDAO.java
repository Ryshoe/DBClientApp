package com.dbclientapp.appointment;

import com.dbclientapp.contact.Contact;
import com.dbclientapp.customer.Customer;
import com.dbclientapp.user.User;
import com.dbclientapp.util.DataAccessObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class AppointmentDAO extends DataAccessObject<Appointment> {

    private static final String CREATE = "INSERT INTO appointments (Title, Description, " +
            "Location, Type, Start, End, Customer_ID, User_ID, Contact_ID) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";

    private static final String READ_ONE = "SELECT Appointment_ID, Title, Description, " +
            "Location, Type, Start, End, Customer_ID, User_ID, Contact_ID " +
            "FROM appointments WHERE Appointment_ID = ?";

    private static final String READ_ALL = "SELECT * FROM appointments " +
            "INNER JOIN customers " +
            "ON appointments.Customer_ID = customers.Customer_ID " +
            "INNER JOIN users " +
            "ON appointments.User_ID = users.User_ID " +
            "INNER JOIN contacts " +
            "ON appointments.Contact_ID = contacts.Contact_ID";

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
                Customer customer = new Customer();
                User user = new User();
                Contact contact = new Contact();
                appointment.setId(rs.getInt("Appointment_ID"));
                appointment.setTitle(rs.getString("Title"));
                appointment.setDescription(rs.getString("Description"));
                appointment.setLocation(rs.getString("Location"));
                appointment.setType(rs.getString("Type"));
                appointment.setStart(rs.getTimestamp("Start"));
                appointment.setEnd(rs.getTimestamp("End"));
                customer.setId(rs.getInt("Customer_ID"));
                customer.setCustName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setPhoneNum(rs.getString("Phone"));
                user.setId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                contact.setId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
                appointment.setCustomer(customer);
                appointment.setUser(user);
                appointment.setContact(contact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointment;
    }

    @Override
    public ObservableList<Appointment> findAll() {
        ObservableList<Appointment> appointmentList = FXCollections.observableArrayList();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Appointment appointment = new Appointment();
                Customer customer = new Customer();
                User user = new User();
                Contact contact = new Contact();
                appointment.setId(rs.getInt("Appointment_ID"));
                appointment.setTitle(rs.getString("Title"));
                appointment.setDescription(rs.getString("Description"));
                appointment.setLocation(rs.getString("Location"));
                appointment.setType(rs.getString("Type"));
                appointment.setStart(rs.getTimestamp("Start"));
                appointment.setEnd(rs.getTimestamp("End"));
                customer.setId(rs.getInt("Customer_ID"));
                customer.setCustName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setPhoneNum(rs.getString("Phone"));
                user.setId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                contact.setId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
                appointment.setCustomer(customer);
                appointment.setUser(user);
                appointment.setContact(contact);
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
            ps.setInt(7, dto.getCustomer().getId());
            ps.setInt(8, dto.getUser().getId());
            ps.setInt(9, dto.getContact().getId());
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
            ps.setInt(7, dto.getCustomer().getId());
            ps.setInt(8, dto.getUser().getId());
            ps.setInt(9, dto.getContact().getId());
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
