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
import java.io.*;
import java.net.URL;
import java.time.ZoneId;
import java.util.ResourceBundle;

/**
 * Controller that handles login screen.
 */
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

    /**
     * Handles action for when cancel button is pressed.
     * @param event
     */
    @FXML
    void cancelButtonAction(ActionEvent event) {
        Platform.exit();
    }

    /**
     * Handles action for when OK button is pressed.
     * @param event
     * @throws IOException
     */
    @FXML
    void okButtonAction(ActionEvent event) throws IOException {
        parseData();
        verifyLogin(event);
        recordLogin();
    }

    /**
     * Translates text and labels to English or French depending on system locale.
     */
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

    /**
     * Validates username and password combination.
     * @param event
     * @throws IOException
     */
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

    /**
     * Parses text from username and password field.
     */
    private void parseData() {
        userInput = usernameField.getText();
        passInput = passwordField.getText();
    }

    /**
     * Handles stage for going to main menu screen.
     * @param event ActionEvent to determine if a button was pressed
     * @throws IOException
     */
    private void goToMainScreen(ActionEvent event) throws IOException {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        Parent scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
        MainScreenController.timeAlert();
    }

    /**
     * Records login activity and writes to a local .txt file.
     * @throws IOException
     */
    private void recordLogin() throws IOException {
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

    /**
     * First method that is called when screen is loaded.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateText();
    }
}
