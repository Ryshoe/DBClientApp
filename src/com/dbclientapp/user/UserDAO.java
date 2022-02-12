package com.dbclientapp.user;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.util.List;

public class UserDAO extends DataAccessObject<User> {

    protected UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User update(User dto) {
        return null;
    }

    @Override
    public User create(User dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

/*
public class UserDAO {

    public static void select() throws SQLException {
        String sql = "SELECT * FROM users";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }

    public static void select(int userId) throws SQLException {   //overload for primary key
        String sql = "SELECT * FROM users WHERE User_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();
        //TODO Figure what to do with selection
    }
}
*/