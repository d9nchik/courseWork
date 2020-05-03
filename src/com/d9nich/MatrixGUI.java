package com.d9nich;

import com.d9nich.specialPane.EnterChooserPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class MatrixGUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane pane = new BorderPane();
        pane.setPadding(new Insets(10, 10, 10, 10));

        VBox calculatingArea = new VBox();
        pane.setBottom(calculatingArea);
        calculatingArea.setSpacing(5);
        calculatingArea.setAlignment(Pos.CENTER);
        Button calculate = new Button("Calculate");
        Text outputPath = new Text("Click to choose directory for output");
        Button chooseFolder = new Button("Choose Folder");

        ToggleGroup matrixMultiplication = new ToggleGroup();
        RadioButton simple = new RadioButton();
        simple.setToggleGroup(matrixMultiplication);
        RadioButton strassen = new RadioButton();
        strassen.setToggleGroup(matrixMultiplication);
        strassen.setSelected(true);
        RadioButton vino = new RadioButton();
        vino.setToggleGroup(matrixMultiplication);
        final HBox downHBox = new HBox(new Label(" - simple", simple),
                new Label(" - strassen", strassen), new Label(" - vino", vino));
        downHBox.setSpacing(10);

        final HBox outputFolderHBox = new HBox(new Label("Output: "), outputPath, chooseFolder);
        outputFolderHBox.setSpacing(10);
        calculatingArea.getChildren().addAll(outputFolderHBox, downHBox, calculate);

        EnterChooserPane centerPane = new EnterChooserPane();
        pane.setCenter(centerPane);

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Matrix Multiplier");
        primaryStage.show();

        chooseFolder.setOnMouseClicked(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            outputPath.setText(selectedDirectory.getAbsolutePath());
        });

        calculate.setOnAction(e -> {
            long[][] matrixA = centerPane.getMatrixA();
            long[][] matrixB = centerPane.getMatrixB();
            long[][] matrixC = makeCalculation(simple, strassen, matrixA, matrixB);

            if (centerPane.isSourceMatrixNeeded()) {
                FileWork.writeMatrix(matrixA, outputPath.getText() + "/matrixA.txt");
                FileWork.writeMatrix(matrixB, outputPath.getText() + "/matrixB.txt");
            }
            FileWork.writeMatrix(matrixC, outputPath.getText() + "/matrixC.txt");
            pane.setRight(new VBox(new Text("Total amount of adding " + MultiplicationsOfMatrix.getAddingOperation()),
                    new Text("Total amount of multiplying " + MultiplicationsOfMatrix.getAddingOperation())));
            MultiplicationsOfMatrix.clearOperation();
        });
    }

    private long[][] makeCalculation(RadioButton simple, RadioButton strassen, long[][] matrixA, long[][] matrixB) {
        long[][] matrixC;
        if (simple.isSelected())
            matrixC = MultiplicationsOfMatrix.simpleMultiplication(matrixA, matrixB);
        else if (strassen.isSelected())
            matrixC = MultiplicationsOfMatrix.simpleMultiplication(matrixA, matrixB);
        else
            matrixC = MultiplicationsOfMatrix.winogradMultiplication(matrixA, matrixB);
        return matrixC;
    }
}
