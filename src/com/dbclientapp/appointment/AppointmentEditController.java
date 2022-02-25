package com.dbclientapp.appointment;

import com.dbclientapp.mainscreen.MainScreenController;
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
        returnToMainScreen(event);
    }

    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        //TODO Parse inputs from form and add to database
        returnToMainScreen(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateComboBox();
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

    private void populateComboBox() {
        // Populate ComboBoxes from static lists
        contactBox.setItems(contactList);
        typeBox.setItems(typeList);
        startTime.setItems(timeList);
        endTime.setItems(timeList);
    }

    //TODO Pre-populate form with selected appointment from MainScreen
    //TODO Error check bounds for start/end date and time
}
