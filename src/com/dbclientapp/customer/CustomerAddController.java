package com.dbclientapp.customer;

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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerAddController implements Initializable {

    Stage stage;
    Parent scene;
    FXMLLoader loader;
    private final ObservableList<String> countryList = FXCollections.observableArrayList(
            "U.S",
            "UK",
            "Canada");
    private final ObservableList<String> divisionListUS = FXCollections.observableArrayList(
            "Alabama",
            "Arizona",
            "Arkansas",
            "California",
            "Colorado",
            "Connecticut",
            "Delaware",
            "District of Columbia",
            "Florida",
            "Georgia",
            "Idaho",
            "Illinois",
            "Indiana",
            "Iowa",
            "Kansas",
            "Kentucky",
            "Louisiana",
            "Maine",
            "Maryland",
            "Massachusetts",
            "Michigan",
            "Minnesota",
            "Mississippi",
            "Missouri",
            "Montana",
            "Nebraska",
            "Nevada",
            "New Hampshire",
            "New Jersey",
            "New Mexico",
            "New York",
            "North Carolina",
            "North Dakota",
            "Ohio",
            "Oklahoma",
            "Oregon",
            "Pennsylvania",
            "Rhode Island",
            "South Carolina",
            "South Dakota",
            "Tennessee",
            "Texas",
            "Utah",
            "Vermont",
            "Virginia",
            "Washington",
            "West Virginia",
            "Wisconsin",
            "Wyoming",
            "Hawaii",
            "Alaska");
    private final ObservableList<String> divisionListUK = FXCollections.observableArrayList(
            "England",
            "Wales",
            "Scotland",
            "Northern Ireland");
    private final ObservableList<String> divisionListCA = FXCollections.observableArrayList(
            "Northwest Territories",
            "Alberta",
            "British Columbia",
            "Manitoba",
            "New Brunswick",
            "Nova Scotia",
            "Prince Edward Island",
            "Ontario",
            "Québec",
            "Saskatchewan",
            "Nunavut",
            "Yukon",
            "Newfoundland and Labrador");

    @FXML
    private TextField addressField;
    @FXML
    private Button cancelButton;
    @FXML
    private ComboBox<String> countryBox;
    @FXML
    private TextField customerIdField;
    @FXML
    private ComboBox<String> divisionBox;
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

    @FXML
    void countryBoxAction(ActionEvent event) {
        String selectedCountry = countryBox.getSelectionModel().getSelectedItem();

        // Populate division ComboBox depending on country selection
        switch (selectedCountry) {
            case "U.S" -> divisionBox.setItems(divisionListUS);
            case "UK" -> divisionBox.setItems(divisionListUK);
            case "Canada" -> divisionBox.setItems(divisionListCA);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Populate country ComboBox
        countryBox.setItems(countryList);
    }
}
