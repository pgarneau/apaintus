package apaintus.models;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Ruler {

    private Pane x;
    private Pane y;
    private Pane mouseX;
    private Pane mouseY;
    private Line xLine = new Line();
    private Line yLine = new Line();
    private double gradation;
    private boolean isActive = false;

    public Ruler(Pane x, Pane y, boolean active) {
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

        isActive = active;
    }

    public void setActive(boolean active){
        this.isActive = active;
    }

    public void update(double x, double y) {
        if(!isActive)
            return;

        clearMousePosition();

        xLine.setStartY(0);
        xLine.setEndY(25);
        xLine.setStartX(x + 25);
        xLine.setEndX(x + 25);
        mouseX.getChildren().add(xLine);

        yLine.setStartX(0);
        yLine.setEndX(25);
        yLine.setStartY(y + 25);
        yLine.setEndY(y + 25);
        mouseY.getChildren().add(yLine);
    }

    private void clearMousePosition() {
        mouseX.getChildren().clear();
        mouseY.getChildren().clear();
    }

    public void clear() {
        clearMousePosition();
        x.getChildren().clear();
        y.getChildren().clear();
    }

    public void draw() {
        for (int i = 25; i < 800; i += gradation) {
            Line line = new Line();
            line.setStartX(i);
            line.setEndX(i);
            double length = i % 25 == 0 ? 12.5 : 20;
            line.setStartY(length);
            line.setEndY(25);
            line.setStroke(Color.BLACK);
            x.getChildren().add(line);
        }

        for (int i = 25; i < 600; i += gradation) {
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

    public void setGrading(Double newValue) {
        gradation = newValue;
    }
}
