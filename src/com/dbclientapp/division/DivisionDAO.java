package com.dbclientapp.division;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DivisionDAO extends DataAccessObject<Division> {

    private static final String READ_ONE = "SELECT Division_ID, Division, Country_ID " +
            "FROM first_level_divisions WHERE Division_ID = ?";

    private static final String READ_ALL = "SELECT * FROM first_level_divisions";

    public DivisionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Division findById(int id) {
        Division division = new Division(0, null, null);
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                division.setId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return division;
    }

    @Override
    public List<Division> findAll() {
        List<Division> divisionList = new ArrayList<>();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Division division = new Division(0, null, null);
                division.setId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
                divisionList.add(division);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return divisionList;
    }

    @Override
    public Division update(Division dto) {
        return null;
    }

    @Override
    public Division create(Division dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
