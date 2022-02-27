package com.dbclientapp.login;

import com.dbclientapp.Application;
import com.dbclientapp.mainscreen.MainScreenController;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private String userInput;
    private String passInput;
    public static User loggedInUser;

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
        parseData();
        verifyLogin(event);
        recordLogin();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourcebundle) {
        populateText();
    }

    public void populateText() {
        // Set ZoneID label
        ZoneId zone = ZoneId.systemDefault();
        zoneIdLabel.setText(String.valueOf(zone));

        // Set prompt text
        usernameField.setPromptText(Application.rb.getString("Username"));
        passwordField.setPromptText(Application.rb.getString("Password"));

        // Set button text
        cancelButton.setText(Application.rb.getString("Cancel"));
    }

    private void verifyLogin(ActionEvent event) throws IOException {
        // Access database and search
        UserDAO userDAO = new UserDAO(DatabaseConnectionManager.openConnection());
        setLoggedInUser(userDAO.findByUser(userInput));
        DatabaseConnectionManager.closeConnection();

        // Validate username and password combination
        if(passInput.equals(loggedInUser.getPassword())) {
            goToMainScreen(event);
        } else {
            Application.showError(Application.rb.getString("Error"));
        }
    }

    private void parseData() {
        userInput = usernameField.getText();
        passInput = passwordField.getText();
    }

    private void goToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
        MainScreenController.timeAlert();
    }

    private void recordLogin() throws IOException {
        // Record login activity and write to login_activity.txt

        FileWriter fileWriter = new FileWriter("src/com/dbclientapp/login_activity.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);
        printWriter.println(userInput + "/" + passInput);
        printWriter.close();
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        LoginController.loggedInUser = loggedInUser;
    }
}
