package com.dbclientapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AppointmentAdd {

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
    private TextField idField;

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
    private TextField typeField;

    @FXML
    private TextField userIdField;

}
