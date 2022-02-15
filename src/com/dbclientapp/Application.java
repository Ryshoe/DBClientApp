package com.dbclientapp;

import com.dbclientapp.appointment.Appointment;
import com.dbclientapp.appointment.AppointmentDAO;
import com.dbclientapp.contact.Contact;
import com.dbclientapp.contact.ContactDAO;
import com.dbclientapp.country.Country;
import com.dbclientapp.country.CountryDAO;
import com.dbclientapp.customer.Customer;
import com.dbclientapp.customer.CustomerDAO;
import com.dbclientapp.division.Division;
import com.dbclientapp.division.DivisionDAO;
import com.dbclientapp.user.User;
import com.dbclientapp.user.UserDAO;
import com.dbclientapp.util.DatabaseConnectionManager;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        ContactDAO contactDAO = new ContactDAO(DatabaseConnectionManager.openConnection());
        List<Contact> testList1 = contactDAO.findAll();
        System.out.println(testList1);

        CountryDAO countryDAO = new CountryDAO(DatabaseConnectionManager.openConnection());
        List<Country> testList2 = countryDAO.findAll();
        System.out.println(testList2);

        DivisionDAO divisionDAO = new DivisionDAO(DatabaseConnectionManager.openConnection());
        List<Division> testList3 = divisionDAO.findAll();
        System.out.println(testList3);

        UserDAO userDAO = new UserDAO(DatabaseConnectionManager.openConnection());
        List<User> testList4 = userDAO.findAll();
        System.out.println(testList4);

        /*
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());

        // Print original table
        List<Appointment> testList3 = appointmentDAO.findAll();
        System.out.println(testList3);

        // Test CREATE statement
        Appointment appointment = new Appointment();
        appointment.setTitle("title");
        appointment.setDescription("description");
        appointment.setLocation("location");
        appointment.setType("Team-Building");
        appointment.setStart(new Timestamp(System.currentTimeMillis()));
        appointment.setEnd(new Timestamp(System.currentTimeMillis()));
        appointment.setCustId(3);
        appointment.setUserId(2);
        appointment.setContactId(3);
        Appointment dbAppointment = appointmentDAO.create(appointment);
        System.out.println(dbAppointment);

        // Test READ statement
        dbAppointment = appointmentDAO.findById(dbAppointment.getId());
        System.out.println(dbAppointment);

        // Test UPDATE statement
        dbAppointment.setType("Feedback");
        appointmentDAO.update(dbAppointment);
        System.out.println(dbAppointment);

        // Test DELETE statement
        appointmentDAO.delete(dbAppointment.getId());
        System.out.println(dbAppointment);

        // Print updated table
        testList = appointmentDAO.findAll();
        System.out.println(testList);
         */

        DatabaseConnectionManager.closeConnection();
    }
}
