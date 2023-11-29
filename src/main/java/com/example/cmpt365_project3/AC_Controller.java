package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class AC_Controller {
    @FXML
    private TextField userInput;
    @FXML
    private Button enterButton;
    @FXML
    private Label inputLabel;
    @FXML
    private Label string;
    @FXML
    private Label instructions;
    @FXML
    private Label warningMessage;
    @FXML
    private Label higherBound;
    @FXML
    private Label lowerBound;
    @FXML
    private Label lowerBoundVal;
    @FXML
    private Label higherBoundVal;
    @FXML
    private TextArea results;
    @FXML
    private Button openFileButton;
    @FXML
    private Label openLabel;

    class Data {
        private String input;
        private double upperBound;
        private double lowerBound;

        public Data(String input, double upperBound, double lowerBound) {
            this.input = input;
            this.upperBound = upperBound;
            this.lowerBound = lowerBound;
        }
        public String getInput() {
            return input;
        }
        public double getUpperBound() {
            return upperBound;
        }
        public double getLowerBound() {
            return lowerBound;
        }
    }
    public double[] getBounds(String string) {
        double[] bounds = new double[2];
        double low = 0.0;
        double high = 1.0;
        double range = 0.0;

        for (char c : string.toCharArray()) {
            if (c == 'a' || c == 'A') {
                range = high - low;
                high = low + range * 0.5;
                low = low + range * 0;
            } else if (c == 'b' || c == 'B') {
                range = high - low;
                high = low + range * 1;
                low = low + range * 0.5;
            }
            else {
                System.out.println("INVALID CHARACTER DETECTED IN INPUT STRING");
                throw new IllegalArgumentException("INVALID CHARACTER DETECTED IN INPUT STRING");
            }
        }
        bounds[0] = low;
        bounds[1] = high;
        return bounds;
    }
    @FXML
    public void inputEntered() {
        String input = userInput.getText();
        System.out.println(input);

        double[] bounds = getBounds(input);

        userInput.setVisible(false);
        enterButton.setVisible(false);
        instructions.setVisible(false);
        warningMessage.setVisible(false);
        higherBoundVal.setText(String.valueOf(bounds[1]));
        lowerBoundVal.setText(String.valueOf(bounds[0]));
        inputLabel.setVisible(true);
        string.setText(input);
        string.setVisible(true);
        higherBoundVal.setVisible(true);
        lowerBoundVal.setVisible(true);
        higherBound.setVisible(true);
        lowerBound.setVisible(true);
        openFileButton.setVisible(false);
        openLabel.setVisible(false);
    }
    @FXML
    public void openFileClicked() throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt files", "*.txt"));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            ArrayList<Data> entries = new ArrayList<>();
            Scanner scanner = new Scanner(file);
            int numOfStrings = Integer.parseInt(scanner.nextLine());
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                double[] bounds = getBounds(input);
                Data newData = new Data(input, bounds[1], bounds[0]);
                entries.add(newData);
            }
            for (Data entry : entries) {
                results.appendText("Input String: " + entry.input + "\n");
                results.appendText("Lower bound: " + entry.lowerBound + "\n");
                results.appendText("Upper bound: " + entry.upperBound + "\n");
                results.appendText("\n");
            }
            userInput.setVisible(false);
            enterButton.setVisible(false);
            instructions.setVisible(false);
            warningMessage.setVisible(false);
            openLabel.setVisible(false);
            openFileButton.setVisible(false);
            results.setVisible(true);
            results.setEditable(false);
        }
    }
}
