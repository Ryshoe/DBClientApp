package com.dbclientapp.mainscreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;

public class MainScreen {

    @FXML
    private Button appointmentAddButton;

    @FXML
    private Button appointmentDeleteButton;

    @FXML
    private Button appointmentEditButton;

    @FXML
    private TableColumn<?, ?> apptContactCol;

    @FXML
    private TableColumn<?, ?> apptCustIdCol;

    @FXML
    private TableColumn<?, ?> apptDescrCol;

    @FXML
    private TableColumn<?, ?> apptEndCol;

    @FXML
    private TableColumn<?, ?> apptIdCol;

    @FXML
    private TableColumn<?, ?> apptLocationCol;

    @FXML
    private TableColumn<?, ?> apptStartCol;

    @FXML
    private TableView<?> apptTable;

    @FXML
    private TableColumn<?, ?> apptTitleCol;

    @FXML
    private TableColumn<?, ?> apptTypeCol;

    @FXML
    private TableColumn<?, ?> apptUserIdCol;

    @FXML
    private TableColumn<?, ?> custAddressCol;

    @FXML
    private TableColumn<?, ?> custCountryCol;

    @FXML
    private TableColumn<?, ?> custDivisionCol;

    @FXML
    private TableColumn<?, ?> custIdCol;

    @FXML
    private TableColumn<?, ?> custNameCol;

    @FXML
    private TableColumn<?, ?> custPhoneCol;

    @FXML
    private TableColumn<?, ?> custPostalCol;

    @FXML
    private TableView<?> custTable;

    @FXML
    private Button customerAddButton;

    @FXML
    private Button customerDeleteButton;

    @FXML
    private Button customerEditButton;

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
    private ToggleGroup toggleGroup;

    @FXML
    private RadioButton weekRadio;

}
