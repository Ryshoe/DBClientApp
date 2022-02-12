package com.dbclientapp.division;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.util.List;

public class DivisionDAO extends DataAccessObject<Division> {

    protected DivisionDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Division findById(int id) {
        return null;
    }

    @Override
    public List<Division> findAll() {
        return null;
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

/*
public class DivisionDAO {

    private static int qDivisionId;
    private static String qDivisionName;
    private static int qCountryId;
    private static Country qCountry;

    public static Division getAll() throws SQLException {
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        while(rs.next()) {
            qDivisionId = rs.getInt("Division_ID");
            qDivisionName = rs.getString("Division");
            qCountryId = rs.getInt("Country_ID");
            qCountry.setCountryId(qCountryId);
            return new Division(qDivisionId, qDivisionName, qCountry);
        }

        return null;
    }

    public static void getDivision(int divisionId) throws SQLException {
        String sql = "SELECT * FROM first_level_divisions WHERE Division_ID = ?";
        PreparedStatement ps = DatabaseConnectionManager.connection.prepareStatement(sql);
        ps.setInt(1, divisionId);
        ResultSet rs = ps.executeQuery();
    }
}
*/