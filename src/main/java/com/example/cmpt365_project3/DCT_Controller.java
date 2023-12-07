package com.example.cmpt365_project3;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DCT_Controller {
    @FXML
    private TextArea textArea;
    @FXML
    private TextArea textArea2;
    @FXML
    private Label leftLabel;
    @FXML
    private Label rightLabel;

    public int[][] getResult (int[][] originalMatrix, boolean rowFirst) {
        int n = originalMatrix.length;
        int[][] result = new int[n][n];

        double a, b;
        double res, sum;
        for (int k=0; k<n; k++) {
            for (int l=0; l<n; l++) {
                double j1 = Math.sqrt(2) / Math.sqrt(n);
                if (k == 0) {
                    a = Math.sqrt(1.0/n);
                } else {
                    a = j1;
                }
                if (l == 0) {
                    b = Math.sqrt(1.0/n);
                } else {
                    b = j1;
                }
                sum = 0;
                double u = 0.0;
                double v = 0.0;
                if (rowFirst) {
                    // row first order
                    for (int x=0; x<n; x++) {
                        for (int y=0; y<n; y++) {
                            u = Math.cos((2 * x + 1) * k * Math.PI / (2 * n));
                            v = Math.cos((2 * y + 1) * l * Math.PI / (2 * n));
                            res = originalMatrix[x][y] * u * v;
                            sum += res;
                        }
                    }
                } else {
                    // column first order
                    for (int y=0; y<n; y++) {
                        for (int x=0; x<n; x++) {
                            u = Math.cos((2 * x + 1) * k * Math.PI / (2 * n));
                            v = Math.cos((2 * y + 1) * l * Math.PI / (2 * n));
                            res = originalMatrix[x][y] * u * v;
                            sum += res;
                        }
                    }
                }
                result[k][l] = (int) Math.round(sum * a * b);
            }
        }
        return result;
    }
//    public double[][] get_T_matrix(int n) {
//        double[][] tMatrix = new double[n][n];
//        double a = 0.0;
//        for (int x=0; x<n; x++) {
//            for (int y=0; y<n; y++) {
//                double entry = 0.0;
//                if (x == 0) {
//                    a = Math.sqrt(1.0/n);
//                } else {
//                    a = Math.sqrt(2.0/n);
//                }
//                double numerator = (2 * y + 1) * x * Math.PI;
//                double denominator = 2.0 * n;
//                double angle = numerator / denominator;
//                entry = a * Math.cos(angle);
//                System.out.println("Angle: " + angle);
//                System.out.println("Entry: " + entry);
//                tMatrix[x][y] = entry;
//            }
//        }
//        return tMatrix;
//    }

//    public int[][] getDCT_Matrix(double[][] t_matrix, int[][] originalMatrix, double[][] t_matrix_trans) {
//        int n = originalMatrix.length;
//        double[][] intermediate = new double[n][n];
//        int[][] result = new int[n][n];
//
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
//        return result;
//    }
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
//            double[][] transformMatrix = get_T_matrix(n);
//            System.out.println(Arrays.deepToString(transformMatrix));
//            double[][] transposed_t_matrix = new double[n][n];
//            for(int i=0; i<n; i++) {
//                for (int j=0; j<n; j++) {
//                    transposed_t_matrix[j][i] = transformMatrix[i][j];
//                }
//            }
//            int[][] resultMatrix = getDCT_Matrix(transformMatrix, originalMatrix, transposed_t_matrix);
            // Do the DCT transforming
            int [][] resultMatrixRow = getResult(originalMatrix, true);
            int [][] resultMatrixCol = getResult(originalMatrix, false);
            leftLabel.setVisible(true);
            rightLabel.setVisible(true);
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    if (j == 0) {
                        textArea.appendText("\n");
                        textArea2.appendText("\n");
                    }
                    textArea.appendText(" " + String.valueOf(resultMatrixRow[i][j]) + ", ");
                    textArea2.appendText(" " + String.valueOf(resultMatrixCol[i][j]) + ", ");
                }
            }
        }
    }
}
