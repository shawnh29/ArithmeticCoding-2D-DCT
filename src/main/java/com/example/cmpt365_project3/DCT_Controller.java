package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class DCT_Controller {
    @FXML
    private TextArea textArea;

    public double[][] get_T_matrix(int n) {
        double[][] tMatrix = new double[n][n];
        double a = 0.0;
        for (int x=0; x<n; x++) {
            for (int y=0; y<n; y++) {
                double entry = 0.0;
                if (x == 0) {
                    a = Math.sqrt(1.0/n);
                } else {
                    a = Math.sqrt(2.0/n);
                }
                double numerator = (2 * y + 1) * x * Math.PI;
                double denominator = 2.0 * n;
                double angle = numerator / denominator;
                entry = a * Math.cos(angle);
                System.out.println("Angle: " + angle);
                System.out.println("Entry: " + entry);
                tMatrix[x][y] = entry;
            }
        }
        return tMatrix;
    }

    public int[][] getDCT_Matrix(double[][] t_matrix, int[][] originalMatrix, double[][] t_matrix_trans) {
        int n = originalMatrix.length;
        double[][] intermediate = new double[n][n];
        int[][] result = new int[n][n];

        double i, j;
        double dct, sum;
        for (int k=0; k<n; k++) {
            for (int l=0; l<n; l++) {
                double j1 = Math.sqrt(2) / Math.sqrt(n);
                if (k == 0) {
                    i = Math.sqrt(1.0/n);
                } else {
                    i = j1;
                }
                if (l == 0) {
                    j = Math.sqrt(1.0/n);
                } else {
                    j = j1;
                }
                sum = 0;
                for (int x=0; x<n; x++) {
                    for (int y=0; y<n; y++) {
                        double u = Math.cos((2 * x + 1) * k * Math.PI / (2 * n));
                        double v = Math.cos((2 * y + 1) * l * Math.PI / (2 * n));
                        dct = originalMatrix[x][y] * u * v;
                        sum += dct;
                    }
                }
                result[k][l] = (int) Math.round(sum * i * j);
            }
        }
//        for (int i=0; i<n; i++) {
//            for (int j=0; j<n; j++) {
//                for (int k=0; k<n; k++) {
//                    intermediate[i][j] += t_matrix[i][k] * originalMatrix[k][j];
//                }
//            }
//        }
//        for (int i=0; i<n; i++) {
//            for (int j=0; j<n; j++) {
//                for (int k=0; k<n; k++) {
//                    result[i][j] += (int) Math.round(intermediate[i][k] * t_matrix_trans[k][j]);
//                }
//            }
//        }
        return result;
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
            double[][] transformMatrix = get_T_matrix(n);
            System.out.println(Arrays.deepToString(transformMatrix));
            double[][] transposed_t_matrix = new double[n][n];
            for(int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    transposed_t_matrix[j][i] = transformMatrix[i][j];
                }
            }
            int[][] resultMatrix = getDCT_Matrix(transformMatrix, originalMatrix, transposed_t_matrix);
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (j == 0) {
                        textArea.appendText("\n");
                    }
                    textArea.appendText(" " + String.valueOf(resultMatrix[i][j]) + ", ");
                }
            }
        }
    }
}
