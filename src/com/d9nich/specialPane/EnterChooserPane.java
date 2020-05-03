package com.d9nich.specialPane;

import com.d9nich.FileWork;
import com.d9nich.MultiplicationsOfMatrix;
import com.d9nich.specialPane.keyboardPane.twoKeyboardMatrices;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class EnterChooserPane extends BorderPane {
    private final twoKeyboardMatrices twoKeyboardMatrices = new twoKeyboardMatrices();
    private final MatrixFilePane filePane = new MatrixFilePane();
    private final GeneratorPane generatorPane = new GeneratorPane();

    private final RadioButton generateMatrixRB = new RadioButton();
    private final RadioButton enterMatrixRB = new RadioButton();
    private final RadioButton readMatrixRB = new RadioButton();

    public EnterChooserPane() {
        ToggleGroup howToEnterMatrix = new ToggleGroup();
        generateMatrixRB.setToggleGroup(howToEnterMatrix);
        enterMatrixRB.setToggleGroup(howToEnterMatrix);
        readMatrixRB.setToggleGroup(howToEnterMatrix);
        enterMatrixRB.setSelected(true);
        final HBox hBox = new HBox(new Label(" - generate", generateMatrixRB),
                new Label(" - enter from keyboard", enterMatrixRB), new Label(" - read file", readMatrixRB));
        hBox.setSpacing(10);
        setTop(hBox);

        setCenter(twoKeyboardMatrices);

        generateMatrixRB.setOnMouseClicked(e -> setCenter(generatorPane));
        enterMatrixRB.setOnAction(e -> setCenter(twoKeyboardMatrices));
        readMatrixRB.setOnAction(e -> setCenter(filePane));
    }

    public long[][] getMatrixA() {
        long[][] matrix;
        if (generateMatrixRB.isSelected()) {
            int i = generatorPane.getI();
            int j = generatorPane.getJ();

            matrix = MultiplicationsOfMatrix.generateMatrix(i, j);
        } else if (enterMatrixRB.isSelected()) {
            matrix = twoKeyboardMatrices.getMatrixA();
        } else {
            matrix = FileWork.readMatrix(filePane.getMatrixAPath());
        }
        return matrix;
    }

    public long[][] getMatrixB() {
        long[][] matrix;
        if (generateMatrixRB.isSelected()) {
            int j = generatorPane.getJ();
            int k = generatorPane.getK();

            matrix = MultiplicationsOfMatrix.generateMatrix(j, k);
        } else if (enterMatrixRB.isSelected()) {
            matrix = twoKeyboardMatrices.getMatrixB();
        } else {
            matrix = FileWork.readMatrix(filePane.getMatrixBPath());
        }
        return matrix;
    }

    public boolean isSourceMatrixNeeded() {
        return !readMatrixRB.isSelected();
    }
}
