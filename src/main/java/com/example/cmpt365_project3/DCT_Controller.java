package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class DCT_Controller {

    public int[][] dctTransform(int[][] matrix) {
        int n = matrix.length;
        for (int x=0; x<n; x++) {
            for (int y=0; y<n; y++) {

            }
        }
        return matrix;
    }
    @FXML
    public void openFileClicked() throws FileNotFoundException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter(".txt files", "*.txt"));
        File file = fc.showOpenDialog(null);

        if (file != null) {
            Scanner scanner = new Scanner(file);
            int n = Integer.parseInt(scanner.nextLine());
            int[][] originalMatrix = new int[n][n];
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    originalMatrix[i][j] = scanner.nextInt();
                }
            }
            // Do the DCT transforming
        }
    }
}
