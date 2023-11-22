package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML
    private Button AC_button;

    @FXML
    public void goToAC() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ArithmeticCodingScreen.fxml"));
        Stage stage = (Stage) AC_button.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Arithmetic Coding");
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void goToDCT() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DCT_Screen.fxml"));
        Stage stage = (Stage) AC_button.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("2D DCT");
        stage.setScene(scene);
        stage.show();
    }
}