package com.dbclientapp.login;

import com.dbclientapp.user.User;
import com.dbclientapp.user.UserDAO;
import com.dbclientapp.util.DatabaseConnectionManager;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    Stage stage;
    Parent scene;
    FXMLLoader loader;

    @FXML
    private Button cancelButton;

    @FXML
    private Button okButton;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    private Label zoneIdLabel;

    @FXML
    void cancelButtonAction(ActionEvent event) {
        Platform.exit();
    }

    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        String userInput = usernameField.getText();
        String passInput = passwordField.getText();
        UserDAO userDAO = new UserDAO(DatabaseConnectionManager.openConnection());
        User userSearch = userDAO.findByUser(userInput);
        DatabaseConnectionManager.closeConnection();

        //Validate username and password combination
        if(passInput.equals(userSearch.getPassword())) {
            stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
            scene = loader.load();
            stage.setScene(new Scene(scene));
            stage.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Invalid username / password.");
            alert.show();
        }
    }

    @Override   // Initialize screen with ZoneId label
    public void initialize(URL url, ResourceBundle resourcebundle) {
        ZoneId zone = ZoneId.systemDefault();
        zoneIdLabel.setText(String.valueOf(zone));
    }
}
