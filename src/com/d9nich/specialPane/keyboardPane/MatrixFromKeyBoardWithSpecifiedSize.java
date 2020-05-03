package com.d9nich.specialPane.keyboardPane;

import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

class MatrixFromKeyBoardWithSpecifiedSize extends BorderPane {

    public MatrixFromKeyBoardWithSpecifiedSize() {
        HBox comboBoxes = new HBox();
        setTop(comboBoxes);
        comboBoxes.setAlignment(Pos.CENTER);
        comboBoxes.setSpacing(5);
        ComboBox<Integer> row = new ComboBox<>();
        for (int i = 1; i <= 10; i++)
            row.getItems().add(i);
        row.setValue(5);
        ComboBox<Integer> column = new ComboBox<>(row.getItems());
        column.setValue(5);
        comboBoxes.getChildren().addAll(new Label("Row: "), row, new Label("Column: "), column);

        resetCenter(row, column);

        row.setOnAction(e -> resetCenter(row, column));
        column.setOnAction(e -> resetCenter(row, column));

    }

    private void resetCenter(ComboBox<Integer> row, ComboBox<Integer> column) {
        setCenter(new MatrixFromKeyboard(row.getValue(), column.getValue()));
    }

    public long[][] getMatrix() {
        return ((MatrixFromKeyboard) getCenter()).getMatrix();
    }
}
