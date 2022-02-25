package com.dbclientapp.customer;

import com.dbclientapp.country.Country;
import com.dbclientapp.country.CountryDAO;
import com.dbclientapp.division.Division;
import com.dbclientapp.division.DivisionDAO;
import com.dbclientapp.mainscreen.MainScreenController;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerEditController implements Initializable {

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
        returnToMainScreen(event);
    }

    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
         parseData();
         returnToMainScreen(event);
    }

    @FXML
    void countryBoxAction(ActionEvent event) {
        populateComboBox();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateSelected();
        populateComboBox();
    }

    private void returnToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    private void populateSelected() {
        // Retrieve selected customer from MainScreen and populate form
        Customer customer = MainScreenController.getSelectedCustomer();
        customerIdField.setText(String.valueOf(customer.getId()));
        nameField.setText(String.valueOf(customer.getCustName()));
        addressField.setText(String.valueOf(customer.getAddress()));
        postalField.setText(String.valueOf(customer.getPostalCode()));
        phoneField.setText(String.valueOf(customer.getPhoneNum()));
        countryBox.setValue(String.valueOf(customer.getDivision().getCountry().getCountryName()));
        divisionBox.setValue(String.valueOf(customer.getDivision().getDivisionName()));
    }

    private void populateComboBox() {
        // Populate country ComboBox from static list
        countryBox.setItems(countryList);
        String selectedCountry = countryBox.getSelectionModel().getSelectedItem();

        // Populate division ComboBox depending on country selection
        switch (selectedCountry) {
            case "U.S" -> divisionBox.setItems(divisionListUS);
            case "UK" -> divisionBox.setItems(divisionListUK);
            case "Canada" -> divisionBox.setItems(divisionListCA);
        }
    }

    private void parseData() {
        // TODO Error checking / input validation

        // Parse input from TextFields
        Customer customerInput = new Customer();
        customerInput.setId(Integer.parseInt(customerIdField.getText()));
        customerInput.setCustName(nameField.getText());
        customerInput.setAddress(addressField.getText());
        customerInput.setPostalCode(postalField.getText());
        customerInput.setPhoneNum(phoneField.getText());

        // Parse ComboBox selections
        CountryDAO countryDAO = new CountryDAO(DatabaseConnectionManager.openConnection());
        Country countryInput = countryDAO.findByName(countryBox.getValue());
        DatabaseConnectionManager.closeConnection();
        DivisionDAO divisionDAO = new DivisionDAO(DatabaseConnectionManager.openConnection());
        Division divisionInput = divisionDAO.findByName(divisionBox.getValue());
        DatabaseConnectionManager.closeConnection();

        // Add country and division details to customer
        divisionInput.setCountry(countryInput);
        customerInput.setDivision(divisionInput);

        // Update record in database
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());
        customerDAO.update(customerInput);
        DatabaseConnectionManager.closeConnection();
    }
}
