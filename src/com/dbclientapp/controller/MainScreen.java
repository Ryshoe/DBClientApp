package com.dbclientapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;

public class MainScreen {

    @FXML
    private Button appointmentAddButton;

    @FXML
    private Button appointmentDeleteButton;

    @FXML
    private Button appointmentEditButton;

    @FXML
    private TableView<?> appointmentTable;

    @FXML
    private Button customerAddButton;

    @FXML
    private Button customerDeleteButton;

    @FXML
    private Button customerEditButton;

    @FXML
    private TableView<?> customerTable;

    @FXML
    private RadioButton monthRadio;

    @FXML
    private RadioButton weekRadio;

}
