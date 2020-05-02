package com.d9nich;

import com.d9nich.keyboardPane.twoKeyboardMatrices;
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

        ToggleGroup howToEnterMatrix = new ToggleGroup();
        RadioButton generateMatrixRB = new RadioButton();
        generateMatrixRB.setToggleGroup(howToEnterMatrix);
        RadioButton enterMatrixRB = new RadioButton();
        enterMatrixRB.setToggleGroup(howToEnterMatrix);
        RadioButton readMatrixRB = new RadioButton();
        readMatrixRB.setToggleGroup(howToEnterMatrix);
        enterMatrixRB.setSelected(true);
        final HBox hBox = new HBox(new Label(" - generate", generateMatrixRB),
                new Label(" - enter from keyboard", enterMatrixRB), new Label(" - read file", readMatrixRB));
        hBox.setSpacing(10);
        pane.setTop(hBox);

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

        final twoKeyboardMatrices twoKeyboardMatrices = new twoKeyboardMatrices();
        pane.setCenter(twoKeyboardMatrices);

        final MatrixFilePane filePane = new MatrixFilePane();
        final GeneratorPane generatorPane = new GeneratorPane();

        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Matrix Multiplier");
        primaryStage.show();

        chooseFolder.setOnMouseClicked(e -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File selectedDirectory = directoryChooser.showDialog(primaryStage);
            outputPath.setText(selectedDirectory.getAbsolutePath());
        });

        generateMatrixRB.setOnMouseClicked(e -> pane.setCenter(generatorPane));
        enterMatrixRB.setOnAction(e -> pane.setCenter(twoKeyboardMatrices));
        readMatrixRB.setOnAction(e -> pane.setCenter(filePane));

        calculate.setOnAction(e -> {
            if (generateMatrixRB.isSelected()) {
                int i = generatorPane.getI();
                int j = generatorPane.getJ();
                int k = generatorPane.getK();

                long[][] matrixA = MultiplicationsOfMatrix.generateMatrix(i, j);
                long[][] matrixB = MultiplicationsOfMatrix.generateMatrix(j, k);
                long[][] matrixC;

                if (simple.isSelected())
                    matrixC = MultiplicationsOfMatrix.simpleMultiplication(matrixA, matrixB);
                else if (strassen.isSelected())
                    matrixC = MultiplicationsOfMatrix.simpleMultiplication(matrixA, matrixB);
                else
                    matrixC = MultiplicationsOfMatrix.winogradMultiplication(matrixA, matrixB);

                FileWork.writeMatrix(matrixA, outputPath.getText() + "/matrixA.txt");
                FileWork.writeMatrix(matrixB, outputPath.getText() + "/matrixB.txt");
                FileWork.writeMatrix(matrixC, outputPath.getText() + "/matrixC.txt");
            }
        });
    }
}
