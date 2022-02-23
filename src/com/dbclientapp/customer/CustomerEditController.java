package com.dbclientapp.customer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {

    Stage stage;
    Parent scene;
    FXMLLoader loader;

    @FXML
    private TextField addressField;

    @FXML
    private Button cancelButton;

    @FXML
    private ComboBox<?> countryBox;

    @FXML
    private TextField customerIdField;

    @FXML
    private ComboBox<?> divisionBox;

    @FXML
    private TextField nameField;

    @FXML
    private Button okButton;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField postalField;

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
        //TODO Parse inputs from form and add to database
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    //TODO Pre-populate form with selected customer from MainScreen
    //TODO Create method that populates country ComboBox
    //TODO Create method that populates division ComboBox
}
