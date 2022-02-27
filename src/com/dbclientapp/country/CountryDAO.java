package com.dbclientapp.country;

import com.dbclientapp.util.DataAccessObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles creation of SQL query statements for country objects.
 */
public class CountryDAO extends DataAccessObject<Country> {

    private static final String READ_ONE_BY_ID = "SELECT Country_ID, Country " +
            "FROM countries WHERE Country_ID = ?";

    private static final String READ_ONE_BY_NAME = "SELECT Country_ID, Country " +
            "FROM countries WHERE Country = ?";

    private static final String READ_ALL = "SELECT * FROM countries";

    public CountryDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Country findById(int id) {
        Country country = new Country();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                country.setId(rs.getInt("Country_ID"));
                country.setCountryName(rs.getString("Country"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return country;
    }

    /**
     * Generates READ statement using string as an argument.
     * @param string country name to search
     * @return country record as an object
     */
    public Country findByName(String string) {
        Country country = new Country();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE_BY_NAME)) {
            ps.setString(1, string);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                country.setId(rs.getInt("Country_ID"));
                country.setCountryName(rs.getString("Country"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return country;
    }

    @Override
    public List<Country> findAll() {
        List<Country> countryList = new ArrayList<>();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Country country = new Country();
                country.setId(rs.getInt("Country_ID"));
                country.setCountryName(rs.getString("Country"));
                countryList.add(country);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return countryList;
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
    public boolean delete(int id) {

        return false;
    }
}
