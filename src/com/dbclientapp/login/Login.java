package com.dbclientapp.login;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class Login {

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
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        loader = new FXMLLoader(getClass().getResource("../mainscreen/MainScreen.fxml"));
        scene = loader.load();
        stage.setScene(new Scene(scene));
        stage.show();
    }

}
