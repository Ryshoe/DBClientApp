package com.dbclientapp.customer;

import com.dbclientapp.country.Country;
import com.dbclientapp.country.CountryDAO;
import com.dbclientapp.division.Division;
import com.dbclientapp.division.DivisionDAO;
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

/**
 * Controller that handles customer add screen.
 */
public class CustomerAddController implements Initializable {

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

    /**
     * Handles action for when cancel button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {
        returnToMainScreen(event);
    }

    /**
     * Handles action for when OK button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        parseData();
        returnToMainScreen(event);
    }

    /**
     * Handles action for when a country is selected from the ComboBox.
     * @param event
     */
    @FXML
    void countryBoxAction(ActionEvent event) {
        // Populate division ComboBox depending on country selection
        String selectedCountry = countryBox.getSelectionModel().getSelectedItem();
        switch (selectedCountry) {
            case "U.S" -> divisionBox.setItems(divisionListUS);
            case "UK" -> divisionBox.setItems(divisionListUK);
            case "Canada" -> divisionBox.setItems(divisionListCA);
        }
    }

    /**
     * Handles stage for returning to main menu screen
     * @param event ActionEvent to determine if a button was pressed
     * @throws IOException
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Populates ComboBoxes on form using static lists.
     */
    private void populateCountryComboBox() {
        // Populate country ComboBox from static list
        countryBox.setItems(countryList);
    }

    /**
     * Parses the data that is entered on the form and updates the selected customer record in the database.
     */
    private void parseData() {
        // Parse input from TextFields
        Customer customerInput = new Customer();
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

        // Create new record in database
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());
        customerDAO.create(customerInput);
        DatabaseConnectionManager.closeConnection();
    }

    /**
     * First method that is called when screen is loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateCountryComboBox();
    }
}
