package apaintus.controllers;

import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.SelectionBox;
import apaintus.models.shapes.Shape;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.CanvasService;
import apaintus.services.update.UpdateService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CanvasController implements ChildController<Controller> {
    @FXML private AnchorPane root;
    @FXML private Canvas drawLayer;
    @FXML private Canvas canvas;
    @FXML private Canvas snapGridCanvas;
    
    private SnapGrid snapGrid;

    private Controller controller;
    private ToolBarController toolBarController;
    private AttributeController attributeController;

    private CanvasService canvasService;
    private UpdateService updateService;

    private Point lastMouseClickPosition;
    private DrawableShape activeShape;
    private List<DrawableShape> drawnShapes = new ArrayList<>();
    private Image baseImage;
    private boolean canvasChanged;
    private ActiveTool activeTool;

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
        this.toolBarController = this.controller.getToolBarController();
        this.attributeController = this.controller.getAttributeController();
        this.canvasService = new CanvasService(this.toolBarController);
        this.updateService = new UpdateService(this.attributeController, this.toolBarController);

        snapGrid = new SnapGrid(toolBarController.getGridSpacing(),canvas.getWidth(),canvas.getHeight(),toolBarController.getStrokeSize(),false);
    }

    @Override
    public void initialize() {
        canvas.setOnMousePressed(event -> {
            lastMouseClickPosition = new Point(event.getX(), event.getY());

            activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT) {
                if (activeShape != null) {
                    activeShape.setSelected(false);
                    this.attributeController.resetSpinners();
                    redrawCanvas();
                }
                activeShape = canvasService.createShape(activeTool, event,getCanvasDimension(), snapGrid);
            } else {
                ListIterator<DrawableShape> iterator = drawnShapes.listIterator(drawnShapes.size());
                while(iterator.hasPrevious()) {
                	DrawableShape shape = (DrawableShape)iterator.previous();
                    if (shape.contains(lastMouseClickPosition)) {
                        activeShape.setSelected(false);

                        activeShape = shape;
                        attributeController.update(activeShape.getShapeAttributes());
                        toolBarController.update(activeShape.getShapeAttributes());
                        activeShape.setSelected(true);
                        redrawCanvas();
                        return; // we found the first shape, no need to
                    }
                }

                if (activeShape != null) {
                    activeShape.setSelected(false);
                    this.attributeController.resetSpinners();
                    redrawCanvas();
                }

                activeShape = canvasService.createShape(activeTool, event);
            }
        });

        canvas.setOnMouseReleased(event -> {
            activeTool = toolBarController.getActiveTool();
            if (activeTool == ActiveTool.SELECT) {
                saveSelectionBox();
            } else if (event.getX() != lastMouseClickPosition.getX() && event.getY() != lastMouseClickPosition.getY()) {
                saveDrawLayer();
                canvasChanged = true;
            }
        });

        canvas.setOnMouseDragged(event -> {
            activeTool = toolBarController.getActiveTool();
            if(activeShape != null) {
                canvasService.updateShape(activeShape, event, lastMouseClickPosition, getCanvasDimension(), snapGrid);
                attributeController.update(activeShape.getShapeAttributes());

                canvasService.clear(drawLayer.getGraphicsContext2D());

                canvasService.drawShape(drawLayer.getGraphicsContext2D(), activeShape);
            }
        });
    }

    public void redrawCanvas() {
        canvasService.clear(canvas.getGraphicsContext2D());

        if (baseImage != null) {
            drawImage(baseImage);
        }

        for (DrawableShape shape : drawnShapes) {
            canvasService.drawShape(canvas.getGraphicsContext2D(), shape);
        }
    }

    public void drawImage(Image image) {
        baseImage = image;
        canvasService.saveState(canvas.getGraphicsContext2D(), image);
    }

    public void clearCanvas() {
        drawnShapes.clear();
        canvasService.clear(drawLayer.getGraphicsContext2D());
        canvasService.clear(canvas.getGraphicsContext2D());
    }

    public List<DrawableShape> getDrawnShapes() {
        return drawnShapes;
    }

    public void setDrawnShapes(List<DrawableShape> drawnShapes) {
        this.drawnShapes = drawnShapes;
    }

    public boolean isCanvasChanged() {
        return canvasChanged;
    }

    public Image getCanvasImage() {
        // Remove the bounding box around the selected shape when saving canvas to image
        activeShape.setSelected(false);
        redrawCanvas();
        Image image = canvasService.convertCanvasToImage(canvas);
        activeShape.setSelected(true);
        redrawCanvas();

        return image;
    }


    public void setCanvasChanged(boolean canvasChanged) {
        this.canvasChanged = canvasChanged;
    }

    private void saveDrawLayer() {
        if (activeShape != null) {
            drawnShapes.add(activeShape);

            canvasService.saveState(canvas.getGraphicsContext2D(), canvasService.convertCanvasToImage(drawLayer));

            canvasService.clear(drawLayer.getGraphicsContext2D());
        }
    }

    private void saveSelectionBox() {
        if(activeShape != null && !drawnShapes.contains(activeShape)) {
            boolean inside;
            SelectionBox selectionBox = (SelectionBox) activeShape;

            for (DrawableShape shape : drawnShapes) {
                inside = true;

                for (Point vertices : shape.getBoundingBox().getVertices()) {
                    inside = activeShape.contains(vertices);
                    if (!inside) {
                        break;
                    }
                }

                if (inside) {
                    selectionBox.add(shape);
                }
            }

            if (!selectionBox.isEmpty()) {
                selectionBox.resize();

                for(DrawableShape shape : drawnShapes) {
                    if (shape instanceof SelectionBox) {
                        activeShape = selectionBox.isDuplicate(shape) ? shape : selectionBox;
                        activeShape.setSelected(true);
                    }
                }

                if(activeShape == selectionBox){
                    selectionBox.optimize();
                    drawnShapes.add(activeShape);
                }
            }
        }

        canvasService.clear(drawLayer.getGraphicsContext2D());
        redrawCanvas();
    }

    public double[] getCanvasDimension() {
    	double[] dimension = new double[2];
    	dimension[0]=canvas.getWidth();
    	dimension[1]=canvas.getHeight();
    	
    	return dimension;
    }
    
    public void drawSnapGrid() {
    	canvasService.drawSnapGrid(snapGridCanvas.getGraphicsContext2D(), snapGrid);
    }
    
    public void clearSnapgrid() {
    	snapGridCanvas.getGraphicsContext2D().clearRect(0, 0, snapGridCanvas.getWidth(), snapGridCanvas.getHeight());
    }

    public void activateSnapGrid() {
        if(snapGrid.isActive()) {
            clearSnapgrid();
            snapGrid.setActive(false);
        }
        else {
            drawSnapGrid();
            snapGrid.setActive(true);
        }
    }

    public class ColorChangeListener implements ChangeListener<Color> {
        private Attribute attribute;

        public ColorChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Color> observableValue, Color oldValue, Color newValue) {
            if(activeShape != null && activeShape.isSelected() && drawnShapes.contains(activeShape)) {
                activeShape.update(updateService.getAttributes());
                redrawCanvas();
            }
        }
    }

    public class ShapeSpinnerChangeListener implements ChangeListener<Double> {
        private Attribute attribute;

        public ShapeSpinnerChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            if(activeShape != null && activeShape.isSelected() && drawnShapes.contains(activeShape)) {
                activeShape.update(updateService.getAttributes());
                redrawCanvas();
            }
        }
    }
    
    public class GridSpinnerChangeListener implements ChangeListener<Double>{

    	@Override
    	public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
			snapGrid.setSpacing(newValue);
			if (snapGrid.isActive()) {
				clearSnapgrid();
				drawSnapGrid();
			}
		}
    }
}