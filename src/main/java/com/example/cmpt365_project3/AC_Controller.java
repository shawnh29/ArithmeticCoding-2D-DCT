package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AC_Controller {
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;
    @FXML
    private Label title;
    @FXML
    private Label instructions;
    @FXML
    private Label warningMessage;
    @FXML
    private Label higherBound;
    @FXML
    private Label lowerBound;

    @FXML
    public void inputEntered() {
        String input = userInput.getText();
        System.out.println(input);

        double low = 0.0;
        double high = 1.0;
        double range = 0.0;

        for (char c : input.toCharArray()) {
            if (c == 'a') {
                range = high - low;
                high = low + range * 0.5;
                low = low + range * 0;
            } else {
                range = high - low;
                high = low + range * 1;
                low = low + range * 0.5;
            }
        }

        userInput.setVisible(false);
        enterButton.setVisible(false);
        instructions.setVisible(false);
        warningMessage.setVisible(false);
        higherBound.setVisible(true);
        lowerBound.setVisible(true);
        System.out.println("Low: " + low);
    }
}
