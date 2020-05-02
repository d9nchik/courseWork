package com.d9nich.keyboardPane;

import javafx.scene.layout.HBox;

public class twoKeyboardMatrices extends HBox {
    private final MatrixFromKeyBoardWithSpecifiedSize matrixA = new MatrixFromKeyBoardWithSpecifiedSize();
    private final MatrixFromKeyBoardWithSpecifiedSize matrixB = new MatrixFromKeyBoardWithSpecifiedSize();

    public twoKeyboardMatrices() {
        getChildren().addAll(matrixA, matrixB);
        setSpacing(10);
    }

    private long[][] getMatrixA() {
        return matrixA.getMatrix();
    }

    private long[][] getMatrixB() {
        return matrixB.getMatrix();
    }
}
