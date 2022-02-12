package com.dbclientapp.util;

import com.dbclientapp.model.Contact;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ContactDAO extends DataAccessObject<Contact> {

    protected ContactDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Contact findById(int id) {
        return null;
    }

    @Override
    public List<Contact> findAll() {
        return null;
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
    public void delete(int id) {

    }
}

/*
public class ContactDAO {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int contactId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM contacts WHERE Contact_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setInt(1, contactId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }
}
*/