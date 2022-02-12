package com.dbclientapp.util;

import com.dbclientapp.model.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CountryDAO extends DataAccessObject<Country> {

    protected CountryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Country findById(int id) {
        return null;
    }

    @Override
    public List<Country> findAll() {
        return null;
    }

    @Override
    public Country update(Country dto) {
        return null;
    }

    @Override
    public Country create(Country dto) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}

/*
public class CountryDAO {

    private static int qCountryId;
    private static String qCountryName;

    public static Country getAll() throws SQLException {
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            qCountryId = rs.getInt("Country_ID");
            qCountryName = rs.getString("Country");
            return new Country(qCountryId, qCountryName);
        }

        return null;
    }

    public static Country getCountry(int countryId) throws SQLException {
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setInt(1, countryId);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            qCountryId = rs.getInt("Country_ID");
            qCountryName = rs.getString("Country");
            return new Country(qCountryId, qCountryName);
        }

        return null;
    }
}
*/