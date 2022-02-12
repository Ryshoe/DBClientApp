package com.dbclientapp.main;

import com.dbclientapp.model.Customer;
import com.dbclientapp.util.CustomerDAO;
import com.dbclientapp.util.DatabaseConnectionManager;

public class Main {

    public static void main(String[] args) {
        /*
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());

        Customer customer = customerDAO.findById(4);
        System.out.println(customer);
        customer.setCustName("Lig Bebowski");
        customerDAO.update(customer);
        System.out.println(customer);
        customerDAO.delete(customer.getId());
        System.out.println(customer);

        DatabaseConnectionManager.closeConnection();
        */
    }
}
