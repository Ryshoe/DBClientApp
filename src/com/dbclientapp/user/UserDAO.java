package com.dbclientapp.user;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends DataAccessObject<User> {

    private static final String READ_ONE = "SELECT User_ID, User_Name, Password " +
            "FROM users WHERE User_ID = ?";

    private static final String READ_ALL = "SELECT * FROM users";

    public UserDAO(Connection connection) {
        super(connection);
    }

    @Override
    public User findById(int id) {
        User user = new User();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                user.setId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setId(rs.getInt("User_ID"));
                user.setUsername(rs.getString("User_Name"));
                user.setPassword(rs.getString("Password"));
                userList.add(user);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return userList;
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
