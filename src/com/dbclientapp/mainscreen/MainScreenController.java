package com.dbclientapp.mainscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class MainScreenController {

    Stage stage;
    Parent scene;
    FXMLLoader loader;

    @FXML
    private Button apptAddButton;

    @FXML
    private TableColumn<?, ?> apptContactCol;

    @FXML
    private TableColumn<?, ?> apptCustIdCol;

    @FXML
    private Button apptDeleteButton;

    @FXML
    private TableColumn<?, ?> apptDescrCol;

    @FXML
    private Button apptEditButton;

    @FXML
    private TableColumn<?, ?> apptEndCol;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableColumn<?, ?> apptLocationCol;

    @FXML
    private TableColumn<?, ?> apptStartCol;

    @FXML
    private Tab apptTab;

    @FXML
    private TableView<?> apptTable;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private TableColumn<?, ?> apptUserIdCol;

    @FXML
    private Button custAddButton;

    @FXML
    private TableColumn<?, ?> custAddressCol;

    @FXML
    private TableColumn<?, ?> custCountryCol;

    @FXML
    private Button custDeleteButton;

    @FXML
    private TableColumn<?, ?> custDivisionCol;

    @FXML
    private Button custEditButton;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private TableColumn<?, ?> custNameCol;

    @FXML
    private TableColumn<?, ?> custPhoneCol;

    @FXML
    private TableColumn<?, ?> custPostalCol;

    @FXML
    private Tab custTab;

    @FXML
    private TableView<?> custTable;

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
    private ComboBox<?> report2Box;

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
    private ComboBox<?> reportBox;

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
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../Appointment/AppointmentAdd.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void apptDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void apptEdit(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../Appointment/AppointmentEdit.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void custAddButtonAction(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../Customer/CustomerAdd.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void custDeleteButtonAction(ActionEvent event) {

    }

    @FXML
    void custEditButtonAction(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../Customer/CustomerEdit.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void monthRadioAction(ActionEvent event) {

    }

    @FXML
    void noneRadioAction(ActionEvent event) {

    }

    @FXML
    void reportRunButtonAction(ActionEvent event) {

    }

    @FXML
    void weekRadioAction(ActionEvent event) {

    }

    public void selectTabPane(int tabIndex) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(tabIndex);
    }
}