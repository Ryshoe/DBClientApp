package com.dbclientapp.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class testFruitsQuery {

    public static int insert(String fruitName, int colorId) throws SQLException {
        String sql = "INSERT INTO fruits (Fruit_Name, Color_ID) VALUES(?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, fruitName);
        ps.setInt(2, colorId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int fruitId, String fruitName) throws SQLException {
        String sql = "UPDATE fruits SET Fruit_Name = ? WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(2, fruitId);
        ps.setString(1, fruitName);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int fruitId) throws SQLException {
        String sql = "DELETE FROM fruits WHERE Fruit_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, fruitId);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM fruits";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            int colorId = rs.getInt("Color_ID");
            System.out.print(fruitId + " | " + fruitName + " | " + colorId + "\n");
        }
    }

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM fruits WHERE Color_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            int fruitId = rs.getInt("Fruit_ID");
            String fruitName = rs.getString("Fruit_Name");
            int colorIdFK = rs.getInt("Color_ID");
            System.out.print(fruitId + " | " + fruitName + " | " + colorIdFK + "\n");
        }
    }
}
