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

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
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
        parseData();
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

    private void parseData() {
        //TODO Error checking / input validation
        // Check bounds for start/end date and time

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

        //TODO Parse date from DatePicker and time from ComboBox
        // also convert to UTC

        // Create new record in database
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        appointmentDAO.create(appointmentInput);
        DatabaseConnectionManager.closeConnection();
    }
}
