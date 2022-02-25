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

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentAddController implements Initializable {

    private final Customer customer = MainScreenController.getSelectedCustomer();
    private final User user = LoginController.getLoggedInUser();
    private final ObservableList<String> contactList = FXCollections.observableArrayList(
            "Anika Costa",
            "Daniel Garcia",
            "Li Lee");
    private final ObservableList<String> typeList = FXCollections.observableArrayList(
            "De-Briefing",
            "Planning Session",
            "Team-Building",
            "Feedback");
    private final ObservableList<String> timeList = FXCollections.observableArrayList(
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
        returnToMainScreen(event, false);
    }

    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        if(parseData())
            returnToMainScreen(event, true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBox();
        populateTextField();
    }

    private void returnToMainScreen(ActionEvent event, boolean check) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        if(check) {
            MainScreenController mainScreenController = loader.getController();
            mainScreenController.selectTabPane(1);
        }
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void populateTextField() {
        // Populate Customer and User ID fields from MainScreen selection
        customerIdField.setText(String.valueOf(customer.getId()));
        userIdField.setText(String.valueOf(user.getId()));
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
        appointmentInput.setTitle(titleField.getText());
        appointmentInput.setDescription(descriptionField.getText());
        appointmentInput.setLocation(locationField.getText());
        appointmentInput.setCustomer(customer);
        appointmentInput.setUser(user);

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

        //TODO Check if appointment falls outside of business hours (8:00 AM to 10:00 PM EST)
        //TODO Check for appointment overlaps
        LocalDateTime startDateTimeInput = startDateInput.atTime(LocalTime.of(startTimeInput, 0));
        LocalDateTime endDateTimeInput = endDateInput.atTime(LocalTime.of(endTimeInput, 0));

        appointmentInput.setStart(Timestamp.valueOf(startDateTimeInput));
        appointmentInput.setEnd(Timestamp.valueOf(endDateTimeInput));

        // Create new record in database
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        appointmentDAO.create(appointmentInput);
        DatabaseConnectionManager.closeConnection();

        return true;
    }
}
