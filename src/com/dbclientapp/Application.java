package com.dbclientapp;

import com.dbclientapp.appointment.Appointment;
import com.dbclientapp.appointment.AppointmentDAO;
import com.dbclientapp.customer.Customer;
import com.dbclientapp.customer.CustomerDAO;
import com.dbclientapp.util.DatabaseConnectionManager;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

public class Application {

    public static void main(String[] args) {

        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());

        // Print original table
        List<Appointment> testList = appointmentDAO.findAll();
        System.out.println(testList);

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

        DatabaseConnectionManager.closeConnection();
    }
}
