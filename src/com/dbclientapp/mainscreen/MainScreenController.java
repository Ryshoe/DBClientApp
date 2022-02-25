package com.dbclientapp.mainscreen;

import com.dbclientapp.Application;
import com.dbclientapp.appointment.Appointment;
import com.dbclientapp.appointment.AppointmentDAO;
import com.dbclientapp.customer.Customer;
import com.dbclientapp.customer.CustomerDAO;
import com.dbclientapp.util.DatabaseConnectionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {

    private final ObservableList<Customer> custList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> apptList = FXCollections.observableArrayList();
    private final ObservableList<String> reportList = FXCollections.observableArrayList(
            "Report #1",
            "Report #2",
            "Report #3");
    private final ObservableList<String> contactList = FXCollections.observableArrayList(
            "Anika Costa",
            "Daniel Garcia",
            "Li Lee");
    public static Customer selectedCustomer = new Customer();
    public static Appointment selectedAppointment = new Appointment();

    @FXML
    private Button apptAddButton;
    @FXML
    private TableColumn<Appointment, String> apptContactCol;
    @FXML
    private TableColumn<Appointment, Number> apptCustIdCol;
    @FXML
    private Button apptDeleteButton;
    @FXML
    private TableColumn<?, ?> apptDescrCol;
    @FXML
    private Button apptEditButton;
    @FXML
    private TableColumn<?, ?> apptEndCol;
    @FXML
    private TableColumn<Appointment, ?> apptIdCol;
    @FXML
    private TableColumn<?, ?> apptLocationCol;
    @FXML
    private TableColumn<?, ?> apptStartCol;
    @FXML
    private Tab apptTab;
    @FXML
    private TableView<Appointment> apptTable;
    @FXML
    private TableColumn<?, ?> apptTitleCol;
    @FXML
    private TableColumn<?, ?> apptTypeCol;
    @FXML
    private TableColumn<Appointment, Number> apptUserIdCol;
    @FXML
    private Button custAddButton;
    @FXML
    private TableColumn<?, ?> custAddressCol;
    @FXML
    private TableColumn<Customer, String> custCountryCol;
    @FXML
    private Button custDeleteButton;
    @FXML
    private TableColumn<Customer, String> custDivisionCol;
    @FXML
    private Button custEditButton;
    @FXML
    private TableColumn<Customer, ?> custIdCol;
    @FXML
    private TableColumn<?, ?> custNameCol;
    @FXML
    private TableColumn<?, ?> custPhoneCol;
    @FXML
    private TableColumn<?, ?> custPostalCol;
    @FXML
    private Tab custTab;
    @FXML
    private TableView<Customer> custTable;
    @FXML
    private RadioButton monthRadio;
    @FXML
    private RadioButton noneRadio;
    @FXML
    private TableColumn<?, ?> report1MonthColumn;
    @FXML
    private TableView<?> report1Table;
    @FXML
    private TableColumn<?, ?> report1TypeColumn1;
    @FXML
    private TableColumn<?, ?> report1TypeColumn2;
    @FXML
    private TableColumn<?, ?> report1TypeColumn3;
    @FXML
    private TableColumn<?, ?> report1TypeColumn4;
    @FXML
    private TableColumn<?, ?> report2ApptIdCol;
    @FXML
    private ComboBox<String> report2Box;
    @FXML
    private TableColumn<?, ?> report2CustIdCol;
    @FXML
    private TableColumn<?, ?> report2DescrCol;
    @FXML
    private TableColumn<?, ?> report2EndCol;
    @FXML
    private Button report2OkButton;
    @FXML
    private TableColumn<?, ?> report2StartCol;
    @FXML
    private TableView<?> report2Table;
    @FXML
    private TableColumn<?, ?> report2TitleCol;
    @FXML
    private TableColumn<?, ?> report2TypeCol;
    @FXML
    private TableColumn<?, ?> report3CustIdCol;
    @FXML
    private TableColumn<?, ?> report3CustNameCol;
    @FXML
    private TableColumn<?, ?> report3LastUpdtByCol;
    @FXML
    private TableView<?> report3Table;
    @FXML
    private ComboBox<String> reportBox;
    @FXML
    private Button reportRunButton;
    @FXML
    private Tab reportTab;
    @FXML
    private TabPane tabPane;
    @FXML
    private ToggleGroup toggleGroup;
    @FXML
    private RadioButton weekRadio;

    @FXML
    void apptAddButtonAction(ActionEvent event) throws IOException {
        goToScreen(event, "../Appointment/AppointmentAdd.fxml");
    }

    @FXML
    void apptEdit(ActionEvent event) throws IOException {
        //TODO Grab current selection and pass to Edit screen
        goToScreen(event, "../Appointment/AppointmentEdit.fxml");
    }

    @FXML
    void apptDeleteButtonAction(ActionEvent event) {
        //TODO Delete selected appointment
    }

    @FXML
    void custAddButtonAction(ActionEvent event) throws IOException {
        goToScreen(event, "../Customer/CustomerAdd.fxml");
    }

    @FXML
    void custEditButtonAction(ActionEvent event) {
        try {
            setSelectedCustomer(custTable.getSelectionModel().getSelectedItem());
            goToScreen(event, "../Customer/CustomerEdit.fxml");
        } catch (Exception e) {
            Application.showError("Please select a customer to edit.");
        }
    }

    @FXML
    void custDeleteButtonAction(ActionEvent event) {
        //TODO Delete selected customer
        try {
            setSelectedCustomer(custTable.getSelectionModel().getSelectedItem());
            deleteCustomer(selectedCustomer);
        } catch (Exception e) {
            Application.showError("Please select a customer to delete.");
        }
        populateTableView();
    }

    @FXML
    void noneRadioAction(ActionEvent event) {
        //TODO Remove filter and show all appointments
    }

    @FXML
    void weekRadioAction(ActionEvent event) {
        //TODO Filter appointments by week
    }

    @FXML
    void monthRadioAction(ActionEvent event) {
        //TODO Filter appointments by month
    }

    @FXML
    void reportBoxAction(ActionEvent event) {
        //TODO Add listener for report selection
    }

    @FXML
    void reportRunButtonAction(ActionEvent event) {
        //TODO Run selected report
        // If report #1 selected, populate report1Table
        // If report #2 selected, display report2Box and populate report2Table (default to first contact)

        //TODO Determine a 3rd report type
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTableView();
        populateComboBox();
    }

    private void goToScreen(ActionEvent event, String location) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void selectTabPane(int tabIndex) {
        // Change tabs depending on selection
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabIndex);
    }

    private void populateComboBox() {
        // Populate report ComboBoxes
        reportBox.setItems(reportList);
        report2Box.setItems(contactList);
    }

    private void populateTableView() {
        // Assign columns for customer data
        custIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("custName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
        custPostalCol.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));
        custDivisionCol.setCellValueFactory(cdf -> cdf.getValue().getDivision().
                divisionNameProperty());
        custCountryCol.setCellValueFactory(cdf -> cdf.getValue().getDivision().
                getCountry().countryNameProperty());

        // Populate customer TableView using SQL database
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());
        custList.setAll(customerDAO.findAll());
        DatabaseConnectionManager.closeConnection();
        custTable.setItems(custList);
        custTable.getSortOrder().add(custIdCol);
        custTable.refresh();


        // Assign column values for appointment data
        apptIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        apptDescrCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("location"));
        apptContactCol.setCellValueFactory(cdf -> cdf.getValue().getContact().contactNameProperty());
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIdCol.setCellValueFactory(cdf -> cdf.getValue().getCustomer().idProperty());
        apptUserIdCol.setCellValueFactory(cdf -> cdf.getValue().getUser().idProperty());

        // Populate appointment TableView using SQL database
        AppointmentDAO appointmentDAO = new AppointmentDAO(DatabaseConnectionManager.openConnection());
        apptList.setAll(appointmentDAO.findAll());
        DatabaseConnectionManager.closeConnection();
        apptTable.setItems(apptList);
        apptTable.getSortOrder().add(apptIdCol);
        apptTable.refresh();
    }

    public static Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer customer) {
        MainScreenController.selectedCustomer = customer;
    }

    private void deleteCustomer(Customer customer) {
        CustomerDAO customerDAO = new CustomerDAO(DatabaseConnectionManager.openConnection());
        customerDAO.delete(customer.getId());
        DatabaseConnectionManager.closeConnection();
    }

    public static Appointment getSelectedAppointment() {
        return selectedAppointment;
    }

    public void setSelectedAppointment(Appointment selectedAppointment) {
        MainScreenController.selectedAppointment = selectedAppointment;
    }
}
