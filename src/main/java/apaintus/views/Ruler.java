package apaintus.views;

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


    public Ruler(Pane x, Pane y) {
        this.x = x;
        this.y = y;
        isActive = false;

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

        x.widthProperty().addListener((observable, oldValue, newValue) -> {
            redraw();
        });
        y.heightProperty().addListener((observable, oldValue, newValue) -> {
            redraw();
        });
    }

    public void toggle() {
        if (!isActive) {
            isActive = true;
            mouseX.setStyle("-fx-background-color: white; -fx-border-color: black");
            mouseY.setStyle("-fx-background-color: white; -fx-border-color: black");

            x.setStyle("-fx-border-color: lightgray");
            y.setStyle("-fx-border-color: lightgray");
        } else {
            isActive = false;
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
        xLine.setStartX(x >= 35 ? x - 25 : 10);
        xLine.setEndX(x >= 35 ? x - 25 : 10);

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
        int count = 0;
        for (int i = 0; i < root.getWidth(); i += gradation) {
            Line line = new Line();
            line.setStartX(i + 35);
            line.setEndX(i + 35);
            double length = (count % 10) == 0 ? 12.5 : 20;
            line.setStartY(length);
            line.setEndY(24);
            line.setStroke(Color.BLACK);
            x.getChildren().add(line);

            if (count == 0) {
                count = 10;
                Text number = new Text(Integer.toString(i/(int)gradation * 10));
                number.setStyle("-fx-text-color: black; -fx-font-size:10");
                TextFlow textFlow = new TextFlow();
                textFlow.setLayoutX(i + 40);
                textFlow.setLayoutY(0);
                textFlow.getChildren().add(number);
                x.getChildren().add(textFlow);
            }
            count--;
        }
        count = 0;
        for (int i = 0; i < root.getHeight(); i += gradation) {
            Line line = new Line();
            line.setStartY(i + 35);
            line.setEndY(i + 35);
            double length = (count % 10)== 0 ? 12.5 : 20;
            line.setStartX(length);
            line.setEndX(24);
            line.setStroke(Color.BLACK);
            y.getChildren().add(line);

            if (count == 0) {
                count = 10;
                Text number = new Text(Integer.toString(i/(int)gradation * 10));
                number.setStyle("-fx-text-color: black; -fx-font-size:10");
                TextFlow textFlow = new TextFlow();
                textFlow.setLayoutY(i + 40);
                textFlow.setLayoutX(0);
                textFlow.getChildren().add(number);
                y.getChildren().add(textFlow);
            }
            count--;
        }
    }

    public void setGradation(double newValue) {
        gradation = newValue;
    }

    public void redraw() {
        if (isActive)
            draw();
    }
}
