package com.dbclientapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Creates a GUI-based scheduling desktop application.
 */
public class Application extends javafx.application.Application {

    public final static ResourceBundle rb = ResourceBundle.getBundle("com/dbclientapp/util/Locale",
            Locale.getDefault());

    /**
     * Displays error alert based on passed string.
     * @param string the message that is displayed in the alert box
     */
    public static void showError(String string) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(string);
        alert.show();
    }

    /**
     * Creates empty stage and sets login screen as the scene.
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login/Login.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Scheduling App");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * First method that is called by the program.
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
