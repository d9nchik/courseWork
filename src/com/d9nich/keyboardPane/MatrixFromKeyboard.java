package com.d9nich.keyboardPane;

import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

class MatrixFromKeyboard extends GridPane {
    private final TextField[][] textFields;

    public MatrixFromKeyboard(int i, int j) {
        textFields = new TextField[i][j];
        for (int k = 0; k < i; k++) {
            for (int l = 0; l < j; l++) {
                textFields[k][l] = new TextField();
                textFields[k][l].setPrefColumnCount(3);
                add(textFields[k][l], l, k);
            }
        }
    }

    public long[][] getMatrix() {
        long[][] matrix = new long[textFields.length][textFields[0].length];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = Integer.parseInt(textFields[i][j].getText());
        return matrix;
    }
}
