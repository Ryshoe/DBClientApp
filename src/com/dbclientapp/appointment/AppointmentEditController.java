package com.dbclientapp.appointment;

import com.dbclientapp.Application;
import com.dbclientapp.contact.Contact;
import com.dbclientapp.contact.ContactDAO;
import com.dbclientapp.customer.Customer;
import com.dbclientapp.login.LoginController;
import com.dbclientapp.mainscreen.MainScreenController;
import com.dbclientapp.user.User;
import com.dbclientapp.util.DatabaseConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AppointmentEditController implements Initializable {

    private static final ObservableList<String> contactList = FXCollections.observableArrayList(
            "Anika Costa",
            "Daniel Garcia",
            "Li Lee");
    private static final ObservableList<String> typeList = FXCollections.observableArrayList(
            "De-Briefing",
            "Planning Session",
            "Team-Building",
            "Feedback");
    private static final ObservableList<String> timeList = FXCollections.observableArrayList(
            "00:00:00",
            "01:00:00",
            "02:00:00",
            "03:00:00",
            "04:00:00",
            "05:00:00",
            "06:00:00",
            "07:00:00",
            "08:00:00",
            "09:00:00",
            "10:00:00",
            "11:00:00",
            "12:00:00",
            "13:00:00",
            "14:00:00",
            "15:00:00",
            "16:00:00",
            "17:00:00",
            "18:00:00",
            "19:00:00",
            "20:00:00",
            "21:00:00",
            "22:00:00",
            "23:00:00");
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");

    @FXML
    private TextField appointmentIdField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<String> contactBox;
    @FXML
    private TextField customerIdField;
    @FXML
    private TextField descriptionField;
    @FXML
    private DatePicker endDate;
    @FXML
    private ComboBox<String> endTime;
    @FXML
    private TextField locationField;
    @FXML
    private Button okButton;
    @FXML
    private DatePicker startDate;
    @FXML
    private ComboBox<String> startTime;
    @FXML
    private TextField titleField;
    @FXML
    private ComboBox<String> typeBox;
    @FXML
    private TextField userIdField;

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        returnToMainScreen(event);
    }

    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        if(parseData())
            returnToMainScreen(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBox();
        populateSelected();
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        MainScreenController mainScreenController = loader.getController();
        mainScreenController.selectTabPane(1);
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void populateSelected() {
        // Retrieve selected appointment from MainScreen and populate form
        Appointment appointment = MainScreenController.getSelectedAppointment();
        appointmentIdField.setText(String.valueOf(appointment.getId()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        contactBox.setValue(appointment.getCustomer().getCustName());
        typeBox.setValue(appointment.getType());
        startDate.setValue(appointment.getStart().toLocalDate());
        endDate.setValue(appointment.getEnd().toLocalDate());
        startTime.setValue(appointment.getStart().format(dtf));
        endTime.setValue(appointment.getEnd().format(dtf));
        customerIdField.setText(String.valueOf(appointment.getCustomer().getId()));
        userIdField.setText(String.valueOf(appointment.getUser().getId()));
    }

    private void populateComboBox() {
        // Populate ComboBoxes from static lists
        contactBox.setItems(contactList);
        typeBox.setItems(typeList);
        startTime.setItems(timeList);
        endTime.setItems(timeList);
    }

    private boolean parseData() {
        //TODO Error checking / input validation

        // Parse input from TextFields
        Appointment appointmentInput = new Appointment();
        Appointment appointment = MainScreenController.getSelectedAppointment();
        appointmentInput.setId(Integer.parseInt(appointmentIdField.getText()));
        appointmentInput.setTitle(titleField.getText());
        appointmentInput.setDescription(descriptionField.getText());
        appointmentInput.setLocation(locationField.getText());
        appointmentInput.setCustomer(appointment.getCustomer());
        appointmentInput.setUser(appointment.getUser());

        // Parse contact ComboBox selection
        ContactDAO contactDAO = new ContactDAO(DatabaseConnectionManager.openConnection());
        Contact contactInput = contactDAO.findByName(contactBox.getValue());
        DatabaseConnectionManager.closeConnection();
        appointmentInput.setContact(contactInput);
        appointmentInput.setType(typeBox.getValue());

        // Parse times from ComboBoxes
        int startTimeInput = Integer.parseInt(startTime.getValue().replaceAll(":", "")) / 10000;
        int endTimeInput = Integer.parseInt(endTime.getValue().replaceAll(":", "")) / 10000;
        if(endTimeInput <= startTimeInput) {
            Application.showError("End time must be after start time.");
            return false;
        }

        // Parse dates from DatePickers
        LocalDate startDateInput = startDate.getValue();
        LocalDate endDateInput = endDate.getValue();
        if(endDateInput.isBefore(startDateInput)) {
            Application.showError("End date cannot be before start date.");
            return false;
        }

        // Combine date and time into LocalDateTime
        LocalDateTime startDateTimeInput = startDateInput.atTime(LocalTime.of(startTimeInput, 0));
        LocalDateTime endDateTimeInput = endDateInput.atTime(LocalTime.of(endTimeInput, 0));

        // Check if appointment falls outside business hours
        ZonedDateTime startDateTimeInputEST = startDateTimeInput.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime endDateTimeInputEST = endDateTimeInput.atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("America/New_York"));
        if(startDateTimeInputEST.getHour() < 8 || startDateTimeInputEST.getHour() > 22) {
            Application.showError("Start time falls outside of business hours (8:00 AM to 10:00 PM EST.)");
            return false;
        }
        if(endDateTimeInputEST.getHour() < 8 || endDateTimeInputEST.getHour() > 22) {
            Application.showError("End time falls outside of business hours (8:00 AM to 10:00 PM EST.)");
            return false;
        }

        // Check for appointment overlaps
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        ObservableList<Appointment> apptList = appointmentDAO.findAll();
        DatabaseConnectionManager.closeConnection();
        for(Appointment i : apptList) {
            if(startDateTimeInput.isAfter(i.getStart()) && startDateTimeInput.isBefore(i.getEnd()) ||
            startDateTimeInput.isEqual(i.getStart()) || startDateTimeInput.isEqual(i.getEnd())) {
                Application.showError("Start conflicts with Appointment ID: " + i.getId());
                return false;
            }
            if(endDateTimeInput.isAfter(i.getStart()) && endDateTimeInput.isBefore(i.getEnd()) ||
            endDateTimeInput.isEqual(i.getStart()) || endDateTimeInput.isEqual(i.getEnd())) {
                Application.showError("End conflicts with Appointment ID: " + i.getId());
                return false;
            }
        }

        appointmentInput.setStart(startDateTimeInput);
        appointmentInput.setEnd(endDateTimeInput);

        // Update record in database
        appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        appointmentDAO.update(appointmentInput);
        DatabaseConnectionManager.closeConnection();

        return true;
    }
}
