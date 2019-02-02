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

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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
    List<DrawableShape> drawnShapes = new ArrayList<>();

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
        this.toolBarController = this.controller.getToolBarController();

        this.canvasService = new CanvasService(this.toolBarController,this);
    }


    @Override
    public void initialize() {
        canvas.setOnMousePressed(event -> {
            lastMouseClickPosition = new Point(event.getX(), event.getY());

            ActiveTool activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT) {
                if (activeShape != null) {
                    activeShape.setSelected(false);
                    redrawCanvas();
                }
                activeShape = canvasService.createShape(activeTool, event);
            } else {
            	ListIterator<DrawableShape> iterator = drawnShapes.listIterator(drawnShapes.size());
                while(iterator.hasPrevious()) {
                	DrawableShape shape = (DrawableShape)iterator.previous();
                    if (shape.contains(lastMouseClickPosition)) {
                        activeShape.setSelected(false);

                        activeShape = shape;
                        activeShape.setSelected(true);
                        redrawCanvas();
                        break; // we found the first shape, no need to 
                    }
                }
            }
        });

        canvas.setOnMouseReleased(event -> {
            if (event.getX() != lastMouseClickPosition.getX() && event.getY() != lastMouseClickPosition.getY()) {
                saveDrawLayer();
                redrawCanvas();
            }
        });

        canvas.setOnMouseDragged(event -> {
            ActiveTool activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT && activeShape != null) {
                canvasService.updateShape(activeShape, event, lastMouseClickPosition,getCanvasDimension());

                clearDrawLayer();

                canvasService.draw(drawLayer.getGraphicsContext2D(), activeShape);
            }
        });
    }

    public void redrawCanvas() {
        clearCanvas();
        for (DrawableShape shape : drawnShapes) {
            canvasService.draw(canvas.getGraphicsContext2D(), shape);
        }
    }

    public void clearCanvas() {
        canvasService.clear(canvas.getGraphicsContext2D());
    }

    public void clearDrawLayer() {
        canvasService.clear(drawLayer.getGraphicsContext2D());
    }

    private void saveDrawLayer() {
        if (activeShape != null) {
            drawnShapes.add(activeShape);

            canvasService.saveState(canvas.getGraphicsContext2D(), canvasService.convertCanvasToImage(drawLayer));

            clearDrawLayer();
        }
    }
    
    public double[] getCanvasDimension() {
    	double[] dimension = new double[2];
    	dimension[0]=canvas.getWidth();
    	dimension[1]=canvas.getHeight();
    	
    	return dimension;
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
