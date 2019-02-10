package apaintus.controllers;

import apaintus.model.snapgrid.Snapgrid;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.DrawableShape;
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
    
    private Snapgrid snapgrid;

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

        snapgrid = new Snapgrid(toolBarController.getGridSpacing(),canvas.getWidth(),canvas.getHeight(),toolBarController.getStrokeSize(),false);
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
                activeShape = canvasService.createShape(activeTool, event,getCanvasDimension(),snapgrid);
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
                        break; // we found the first shape, no need to 
                    }
                }
            }
        });

        canvas.setOnMouseReleased(event -> {
            activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT && event.getX() != lastMouseClickPosition.getX() && event.getY() != lastMouseClickPosition.getY()) {
                saveDrawLayer();
                canvasChanged = true;
            }
        });

        canvas.setOnMouseDragged(event -> {
            activeTool = toolBarController.getActiveTool();
            if (activeTool != ActiveTool.SELECT && activeShape != null) {
                canvasService.updateShape(activeShape, event, lastMouseClickPosition, getCanvasDimension(),snapgrid);
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
    
    public double[] getCanvasDimension() {
    	double[] dimension = new double[2];
    	dimension[0]=canvas.getWidth();
    	dimension[1]=canvas.getHeight();
    	
    	return dimension;
    }
    
    public void drawSnapGrid() {
    	canvasService.drawSnapGrid(snapGridCanvas.getGraphicsContext2D(), snapgrid);
    }
    
    public void clearSnapgrid() {
    	snapGridCanvas.getGraphicsContext2D().clearRect(0, 0, snapGridCanvas.getWidth(), snapGridCanvas.getHeight());
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
    	
    	public GridSpinnerChangeListener() {
    	}
    	
    	@Override
    	public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
			snapgrid.setSpacing(newValue);
			if (snapgrid.isActive()) {
				clearSnapgrid();
				drawSnapGrid();
			}
		}
    }

	public void activateSnapGrid() {
		if(snapgrid.isActive()) {
			clearSnapgrid();
			snapgrid.setActive(false);
		}
		else {
			drawSnapGrid();
			snapgrid.setActive(true);
		}
	}
}