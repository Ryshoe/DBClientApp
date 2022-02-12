package com.dbclientapp.appointment;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AppointmentEdit {

    @FXML
    private TextField appointmentIdField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> contactBox;

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField descriptionField;

    @FXML
    private DatePicker endDate;

    @FXML
    private ComboBox<?> endTime;

    @FXML
    private TextField locationField;

    @FXML
    private Button okButton;

    @FXML
    private DatePicker startDate;

    @FXML
    private ComboBox<?> startTime;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<?> typeBox;

    @FXML
    private TextField userIdField;

}
