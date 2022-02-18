package com.dbclientapp.appointment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class AppointmentAdd {

    Stage stage;
    Parent scene;
    FXMLLoader loader;

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

    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void okButtonAction(ActionEvent event) {

    }

}
