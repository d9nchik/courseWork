package com.d9nich.specialPane;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class GeneratorPane extends VBox {
    private final TextField a = new TextField();
    private final TextField b = new TextField();
    private final TextField c = new TextField();

    public GeneratorPane() {
        getChildren().add(new Text("Enter i, j and k. Where i - how many rows of matrix A\n" +
                "j - rows of matrix B and columns of A, k - columns of B"));
        getChildren().add(new HBox(new Label("i = "), a, new Label("j = "), b, new Label("k = "), c));
    }

    public int getI() {
        return Integer.parseInt(a.getText());
    }

    public int getJ() {
        return Integer.parseInt(b.getText());
    }

    public int getK() {
        return Integer.parseInt(c.getText());
    }
}
