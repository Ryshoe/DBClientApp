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
import java.time.*;
import java.util.ResourceBundle;

/**
 * Controller that handles appointment add screen.
 */
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


    /**
     * Handles action for when cancel button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        returnToMainScreen(event, false);
    }

    /**
     * Handles action for when OK button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        if(parseData())
            returnToMainScreen(event, true);
    }

    /**
     * Handles stage for returning to main menu screen.
     * @param event ActionEvent to determine if a button was pressed
     * @param check boolean to select appropriate tab on main menu screen
     * @throws IOException
     */
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

    /**
     * Populates TextFields with selected customer ID and current user ID.
     */
    private void populateTextField() {
        customerIdField.setText(String.valueOf(customer.getId()));
        userIdField.setText(String.valueOf(user.getId()));
    }

    /**
     * Populates ComboBoxes on form using static lists.
     */
    private void populateComboBox() {
        contactBox.setItems(contactList);
        typeBox.setItems(typeList);
        startTime.setItems(timeList);
        endTime.setItems(timeList);
    }

    /**
     * Parses the data that is entered on the form and creates a new appointment record in the database.
     * @return boolean for input validation
     */
    private boolean parseData() {
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

        // Add start/end data to appointment object
        appointmentInput.setStart(startDateTimeInput);
        appointmentInput.setEnd(endDateTimeInput);

        // Create new record in database
        appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        appointmentDAO.create(appointmentInput);
        DatabaseConnectionManager.closeConnection();

        return true;
    }

    /**
     * First method that is called when screen is loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBox();
        populateTextField();
    }
}
