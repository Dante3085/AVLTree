package com.AVLTree.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

public class AVLNodeView extends StackPane
{
    private Circle circle;
    private Label dataText;

    public AVLNodeView(double x, double y, String dataText)
    {
        circle = new Circle(dataText.length()*5);
        circle.setOpacity(0.5);
        circle.setTranslateX(x);
        circle.setTranslateY(y);

        this.dataText = new Label(dataText);
        this.dataText.setTranslateX(x);
        this.dataText.setTranslateY(y);

        getChildren().addAll(circle, this.dataText);
    }
}
