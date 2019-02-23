package apaintus.models;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

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



        mouseX.setLayoutX(25.0);
        mouseY.setLayoutY(25.0);

        x.setPrefHeight(25.0);

        y.setPrefWidth(25.0);

        xLine.setStroke(Color.BLACK);
        yLine.setStroke(Color.BLACK);

        x.getChildren().add(mouseX);
        y.getChildren().add(mouseY);

        isActive = active;

        x.widthProperty().addListener((observable, oldValue, newValue) -> {
            resizeUpdate();
        });
        y.heightProperty().addListener((observable, oldValue, newValue) -> {
            resizeUpdate();
        });
    }

    public void toogleRulers(boolean active) {
        this.isActive = active;
        if(active){
            mouseX.setStyle("-fx-background-color: white; -fx-border-color: black");
            mouseY.setStyle("-fx-background-color: white; -fx-border-color: black");

            x.setStyle("-fx-border-color: lightgray");
            y.setStyle("-fx-border-color: lightgray");
        }
        else{
            mouseX.setStyle("-fx-background-color: transparent; -fx-border-color: transparent");
            mouseY.setStyle("-fx-background-color: transparent; -fx-border-color: transparent");

            x.setStyle("-fx-border-color: transparent");
            y.setStyle("-fx-border-color: transparent");
        }
    }

    public void update(double x, double y) {
        if (!isActive)
            return;

        clearMousePosition();

        xLine.setStartY(0);
        xLine.setEndY(24);
        xLine.setStartX(x >= 35? x - 25 : 10);
        xLine.setEndX(x >= 35? x - 25 : 10);

        mouseX.getChildren().add(xLine);

        yLine.setStartX(0);
        yLine.setEndX(24);
        yLine.setStartY(y >= 35 ? y - 25 : 10);
        yLine.setEndY(y >= 35 ? y - 25 : 10);
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
        x.getChildren().add(mouseX);
        y.getChildren().add(mouseY);
    }

    public void draw() {
        clear();
        AnchorPane root = (AnchorPane) x.getParent();

        for (int i = 35; i < root.getWidth(); i += gradation) {
            Line line = new Line();
            line.setStartX(i);
            line.setEndX(i);
            double length = i % 35 == 0 ? 12.5 : 20;
            line.setStartY(length);
            line.setEndY(24);
            line.setStroke(Color.BLACK);
            x.getChildren().add(line);

            if(i % 35 == 0) {
                Text number = new Text(Integer.toString(i - 35));
                number.setStyle("-fx-text-color: black");
                TextFlow textFlow = new TextFlow();
                textFlow.setLayoutX(i + 5);
                textFlow.setLayoutY(0);
                textFlow.getChildren().add(number);
                x.getChildren().add(textFlow);
            }
        }

        for (int i = 35; i < root.getHeight(); i += gradation) {
            Line line = new Line();
            line.setStartY(i);
            line.setEndY(i);
            double length = i % 35 == 0 ? 12.5 : 20;
            line.setStartX(length);
            line.setEndX(24);
            line.setStroke(Color.BLACK);
            y.getChildren().add(line);

            if(i % 35 == 0) {
                Text number = new Text(Integer.toString(i - 35));
                number.setStyle("-fx-text-color: black");
                TextFlow textFlow = new TextFlow();
                textFlow.setLayoutY(i + 5);
                textFlow.setLayoutX(0);
                textFlow.getChildren().add(number);
                y.getChildren().add(textFlow);
            }
        }
    }

    public void setGrading(Double newValue) {
        gradation = newValue;
    }

    public void resizeUpdate() {
        if (isActive)
            draw();
    }
}
