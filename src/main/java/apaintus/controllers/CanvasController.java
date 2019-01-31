package apaintus.controllers;

import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.Shape;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.CanvasService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.List;

public class CanvasController implements ChildController<Controller> {
    @FXML private AnchorPane root;
    @FXML private Canvas drawLayer;
    @FXML private Canvas canvas;

    private Controller controller;
    private ToolBarController toolBarController;

    private CanvasService canvasService;

    private boolean newImage = true;

    Point lastMouseClickPosition;
    DrawableShape activeShape;
    List<Shape> drawnShapes;

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
        this.toolBarController = this.controller.getToolBarController();

        this.canvasService = new CanvasService(this.toolBarController);
    }


    @Override
    public void initialize() {
        canvas.setOnMousePressed(event -> {
            lastMouseClickPosition = new Point(event.getX(), event.getY());

            ActiveTool activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT) {
                activeShape = canvasService.createShape(activeTool, event);
                // SET SHAPE TO SELECTED
            } else {
                // SET SHAPE TO SELECTED IF FOUND
            }
        });

        canvas.setOnMouseReleased(event -> {
            if (event.getX() != lastMouseClickPosition.getX() && event.getY() != lastMouseClickPosition.getY()) {
                // SAVE THE STATE
            } else {
                activeShape = null;
            }
        });

        canvas.setOnMouseDragged(event -> {
            ActiveTool activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT && activeShape != null) {
                canvasService.updateShape(activeShape, event, lastMouseClickPosition);
                canvasService.draw(drawLayer.getGraphicsContext2D(), activeShape);

            }
        });
    }

    public class ColorChangeListener implements ChangeListener<Color> {
        private Attribute attribute;

        public ColorChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Color> observableValue, Color oldValue, Color newValue) {
            System.out.println("DO SOME SHIT WITH " + attribute);
        }
    }

    public class SpinnerChangeListener implements ChangeListener<Double> {
        private Attribute attribute;

        public SpinnerChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            System.out.println("DO SOME SHIT WITH " + attribute);
        }
    }
}
