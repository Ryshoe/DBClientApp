package com.dbclientapp.customer;

import com.dbclientapp.util.DataAccessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CustomerDAO extends DataAccessObject<Customer> {

    private static final String INSERT = "INSERT INTO customers (Customer_Name, " +
            "Address, Postal_Code, Phone, Division_ID) VALUES (?, ?, ?, ?, ?) ";

    private static final String GET_ONE = "SELECT Customer_ID, Customer_Name, " +
            "Address, Postal_Code, Phone, Division_ID FROM customers WHERE Customer_ID = ?";

    private static final String UPDATE = "UPDATE customers SET Customer_Name = ?, " +
            "Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";

    private static final String DELETE = "DELETE FROM customers WHERE Customer_ID = ?";

    public CustomerDAO(Connection connection) {
        super(connection);
    }

    @Override
    public Customer findById(int id) {
        Customer customer = new Customer();
        try(PreparedStatement ps = this.connection.prepareStatement(GET_ONE)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                customer.setId(rs.getInt("Customer_ID"));
                customer.setCustName(rs.getString("Customer_Name"));
                customer.setAddress(rs.getString("Address"));
                customer.setPostalCode(rs.getString("Postal_Code"));
                customer.setDivisionId(rs.getInt("Division_ID"));
            }
        } catch(SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return null;
    }

    @Override
    public Customer update(Customer dto) {
        Customer customer;
        try(PreparedStatement ps = this.connection.prepareStatement(UPDATE)) {
            ps.setString(1, dto.getCustName());
            ps.setString(2, dto.getAddress());
            ps.setString(3, dto.getPostalCode());
            ps.setString(4, dto.getPhoneNum());
            ps.setInt(5, dto.getDivisionId());
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
        try(PreparedStatement ps = this.connection.prepareStatement(INSERT)) {
            ps.setString(1, dto.getCustName());
            ps.setString(2, dto.getAddress());
            ps.setString(3, dto.getPostalCode());
            ps.setString(4, dto.getPhoneNum());
            ps.setInt(5, dto.getDivisionId());
            ps.execute();
            return null;
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
