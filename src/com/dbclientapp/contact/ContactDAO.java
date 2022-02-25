package com.dbclientapp.contact;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends DataAccessObject<Contact> {

    private static final String READ_ONE_BY_ID = "SELECT Contact_ID, Contact_Name, Email " +
            "FROM contacts WHERE Contact_ID = ?";

    private static final String READ_ONE_BY_NAME = "SELECT Contact_ID, Contact_Name, Email " +
            "FROM contacts WHERE Contact_Name = ?";

    private static final String READ_ALL = "SELECT * FROM contacts";

    public ContactDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Contact findById(int id) {
        Contact contact = new Contact();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                contact.setId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }

    public Contact findByName(String name) {
        Contact contact = new Contact();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE_BY_NAME)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                contact.setId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contact;
    }

    @Override
    public List<Contact> findAll() {
        List<Contact> contactList = new ArrayList<>();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Contact contact = new Contact();
                contact.setId(rs.getInt("Contact_ID"));
                contact.setContactName(rs.getString("Contact_Name"));
                contact.setEmail(rs.getString("Email"));
                contactList.add(contact);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return contactList;
    }

    @Override
    public Contact update(Contact dto) {
        return null;
    }

    @Override
    public Contact create(Contact dto) {
        return null;
    }

    @Override
    public boolean delete(int id) {

        return false;
    }
}
