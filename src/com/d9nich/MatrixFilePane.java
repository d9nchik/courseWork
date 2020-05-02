package com.d9nich;

import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MatrixFilePane extends VBox {
    private final Text matrixAPath = new Text("Choose matrix A path");
    private final Text matrixBPath = new Text("Choose matrix B path");

    public MatrixFilePane() {
        setSpacing(10);

        HBox firstHBox = new HBox();
        firstHBox.setSpacing(5);
        Button setPathA = new Button("Set path");
        setPathA.setOnAction(e -> {
            FileChooser directoryChooser = new FileChooser();
            File selectedDirectory = directoryChooser.showOpenDialog(new Stage());
            matrixAPath.setText(selectedDirectory.getAbsolutePath());
        });
        firstHBox.getChildren().addAll(matrixAPath, setPathA);

        HBox secondHBox = new HBox();
        secondHBox.setSpacing(5);
        Button setPathB = new Button("Set path");
        setPathB.setOnAction(e -> {
            FileChooser directoryChooser = new FileChooser();
            File selectedDirectory = directoryChooser.showOpenDialog(new Stage());
            matrixBPath.setText(selectedDirectory.getAbsolutePath());
        });
        secondHBox.getChildren().addAll(matrixBPath, setPathB);

        getChildren().addAll(firstHBox, secondHBox);
    }

    public String getMatrixAPath() {
        return matrixAPath.getText();
    }

    public String getMatrixBPath() {
        return matrixBPath.getText();
    }
}
