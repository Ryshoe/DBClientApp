package com.dbclientapp.appointment;

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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class AppointmentEditController implements Initializable {

    private final Appointment appointment = MainScreenController.getSelectedAppointment();
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
            "08:00:00",
            "09:00:00",
            "10:00:00",
            "11:00:00",
            "12:00:00",
            "13:00:00",
            "14:00:00",
            "15:00:00",
            "16:00:00",
            "17:00:00");
    private final SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");

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
        parseData();
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
        appointmentIdField.setText(String.valueOf(appointment.getId()));
        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        contactBox.setValue(appointment.getCustomer().getCustName());
        typeBox.setValue(appointment.getType());
        startDate.setValue(appointment.getStart().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        endDate.setValue(appointment.getEnd().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        startTime.setValue(sdf.format(appointment.getStart()));
        endTime.setValue(sdf.format(appointment.getEnd()));
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

    private void parseData() {
        //TODO Error checking / input validation
        // Check bounds for start/end date and time

        // Parse input from TextFields
        Appointment appointmentInput = new Appointment();
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

        // Parse dates from DatePickers and times from ComboBoxes
        int startTimeInput = Integer.parseInt(startTime.getValue().replaceAll(":", "")) / 10000;
        int endTimeInput = Integer.parseInt(endTime.getValue().replaceAll(":", "")) / 10000;
        LocalDate startDateInput = startDate.getValue();
        LocalDate endDateInput = endDate.getValue();
        LocalDateTime startDateTimeInput = startDateInput.atTime(LocalTime.of(startTimeInput, 0));
        LocalDateTime endDateTimeInput = endDateInput.atTime(LocalTime.of(endTimeInput, 0));
        appointmentInput.setStart(Timestamp.valueOf(startDateTimeInput));
        appointmentInput.setEnd(Timestamp.valueOf(endDateTimeInput));

        // Update record in database
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        appointmentDAO.update(appointmentInput);
        DatabaseConnectionManager.closeConnection();
    }
}
