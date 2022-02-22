package com.dbclientapp;

import com.dbclientapp.customer.Customer;
import com.dbclientapp.customer.CustomerDAO;
import com.dbclientapp.util.DatabaseConnectionManager;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Application extends javafx.application.Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduling App");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

/*
AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
List<Appointment> testList5 = appointmentDAO.findAll();
System.out.println(testList5);

CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());
ObservableList<Customer> testList6 = customerDAO.findAll();
System.out.println(testList6);

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

DatabaseConnectionManager.closeConnection();
*/