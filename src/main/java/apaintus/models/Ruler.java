package apaintus.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.awt.event.MouseEvent;

public class Ruler {

    private Pane x;
    private Pane y;
    private Pane mouseX;
    private Pane mouseY;
    private Line xLine = new Line();
    private Line yLine = new Line();
    private int gradation = 10;

    public Ruler(Pane x, Pane y) {
        this.x = x;
        this.y = y;

        mouseX = new Pane();
        mouseY = new Pane();

        mouseX.prefHeightProperty().bind(x.heightProperty());
        mouseX.prefWidthProperty().bind(x.widthProperty());
        mouseY.prefWidthProperty().bind(y.widthProperty());
        mouseY.prefHeightProperty().bind(y.heightProperty());

        x.setPrefHeight(25.0);
        x.setStyle("-fx-border-color: lightgray ");

        y.setPrefWidth(25.0);
        y.setStyle("-fx-border-color: lightgray ");

        xLine.setStroke(Color.BLACK);
        yLine.setStroke(Color.BLACK);

        x.getChildren().add(mouseX);
        y.getChildren().add(mouseY);

        computeGradation(800, 600);
    }

    private void computeGradation(double width, double hieght) {
        for (int i = 25; i < width; i += gradation) {
            Line line = new Line();
            line.setStartX(i);
            line.setEndX(i);
            double length = i % 25 == 0 ? 12.5 : 20;
            line.setStartY(length);
            line.setEndY(25);
            line.setStroke(Color.BLACK);
            x.getChildren().add(line);
        }

        for (int i = 25; i < hieght; i += gradation) {
            Line line = new Line();
            line.setStartY(i);
            line.setEndY(i);
            double length = i % 25 == 0 ? 12.5 : 20;
            line.setStartX(length);
            line.setEndX(25);
            line.setStroke(Color.BLACK);
            y.getChildren().add(line);
        }
    }

    public void update(double x, double y) {
        clear();

        xLine.setStartY(0);
        xLine.setEndY(25);
        xLine.setStartX(x);
        xLine.setEndX(x);
        mouseX.getChildren().add(xLine);

        yLine.setStartX(0);
        yLine.setEndX(25);
        yLine.setStartY(y);
        yLine.setEndY(y);
        mouseY.getChildren().add(yLine);
    }

    private void clear() {
        mouseX.getChildren().clear();
        mouseY.getChildren().clear();
    }
}
