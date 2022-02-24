package com.dbclientapp.customer;

import com.dbclientapp.country.Country;
import com.dbclientapp.division.Division;
import com.dbclientapp.util.DataAccessObject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class CustomerDAO extends DataAccessObject<Customer> {

    private static final String CREATE = "INSERT INTO customers (Customer_Name, " +
            "Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?) ";

    private static final String READ_ONE = "SELECT Customer_ID, Customer_Name, " +
            "Address, Postal_Code, Phone, Division_ID FROM customers WHERE Customer_ID = ?";

    private static final String READ_ALL = "SELECT * FROM customers " +
            "INNER JOIN first_level_divisions " +
            "ON customers.Division_ID = first_level_divisions.Division_ID " +
            "INNER JOIN countries " +
            "ON first_level_divisions.Country_ID = countries.Country_ID";

    private static final String UPDATE = "UPDATE customers SET Customer_Name = ?, " +
            "Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

    private static final String DELETE = "DELETE FROM customers WHERE Customer_ID = ?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(int id) {
        Customer customer = new Customer();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ONE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Division division = new Division();
                Country country = new Country();
                customer.setId(rs.getInt("Customer_ID"));
                customer.setCustName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setPhoneNum(rs.getString("Phone"));
                division.setId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
                country.setId(rs.getInt("Country_ID"));
                country.setCountryName(rs.getString("Country"));
                division.setCountry(country);
                customer.setDivision(division);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public ObservableList<Customer> findAll() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try(PreparedStatement ps = this.connection.prepareStatement(READ_ALL)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                Customer customer = new Customer();
                Division division = new Division();
                Country country = new Country();
                customer.setId(rs.getInt("Customer_ID"));
                customer.setCustName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setPhoneNum(rs.getString("Phone"));
                division.setId(rs.getInt("Division_ID"));
                division.setDivisionName(rs.getString("Division"));
                country.setId(rs.getInt("Country_ID"));
                country.setCountryName(rs.getString("Country"));
                division.setCountry(country);
                customer.setDivision(division);
                customerList.add(customer);
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customerList;
    }

    @Override
    public Customer update(Customer dto) {
        Customer customer;
        try(PreparedStatement ps = this.connection.prepareStatement(UPDATE)) {
            ps.setString(1, dto.getCustName());
            ps.setString(2, dto.getAddress());
            ps.setString(3, dto.getPostalCode());
            ps.setString(4, dto.getPhoneNum());
            ps.setInt(5, dto.getDivision().getId());
            ps.setInt(6, dto.getId());
            ps.execute();
            customer = this.findById(dto.getId());
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public Customer create(Customer dto) {
        try(PreparedStatement ps = this.connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, dto.getCustName());
            ps.setString(2, dto.getAddress());
            ps.setString(3, dto.getPostalCode());
            ps.setString(4, dto.getPhoneNum());
            ps.setInt(5, dto.getDivision().getId());
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
