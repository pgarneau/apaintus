package apaintus.controllers;

import apaintus.models.Alignment;
import apaintus.models.commands.*;
import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.SelectionBox;
import apaintus.models.shapes.ShapeType;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.CanvasService;
import apaintus.services.update.UpdateService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CanvasController implements ChildController<Controller> {
    @FXML
    private AnchorPane root;
    @FXML
    private Canvas drawLayer;
    @FXML
    private Canvas canvas;
    @FXML
    private Canvas snapGridCanvas;

    private SnapGrid snapGrid;

    private ToolBarController toolBarController;
    private AttributeController attributeController;

    private CanvasService canvasService;
    private UpdateService updateService;

    private Point lastMouseClickPosition;
    private DrawableShape tempActiveShape;
    private DrawableShape activeShape;
    private SelectionBox selectionBox;
    private List<DrawableShape> drawnShapes = new ArrayList<>();
    private Image baseImage;
    private boolean canvasChanged;
    private ActiveTool activeTool;
    private Invoker invoker;
    private DeselectCommand deselectCommand;

    @Override
    public void injectParentController(Controller controller) {
        this.toolBarController = controller.getToolBarController();
        this.attributeController = controller.getAttributeController();
        this.canvasService = new CanvasService(this.toolBarController);
        this.updateService = new UpdateService(this.attributeController, this.toolBarController);

        snapGrid = new SnapGrid(toolBarController.getGridSpacing(), canvas.getWidth(), canvas.getHeight(), false);
        invoker = controller.getInvoker();
    }

    @Override
    public void initialize() {
        canvas.setOnMousePressed(event -> {
            lastMouseClickPosition = new Point(event.getX(), event.getY());
            activeTool = toolBarController.getActiveTool();

            tempActiveShape = canvasService.createShape(activeTool, event, getCanvasDimension());
            deselectCommand = new DeselectCommand(this, activeShape);
            deselectCommand.execute();

            if (activeTool == ActiveTool.SELECT) {
                selectionBox = (SelectionBox) tempActiveShape;
            }
        });

        canvas.setOnMouseReleased(event -> {
            deselectCommand.undo();

            if (event.getX() == lastMouseClickPosition.getX() && event.getY() == lastMouseClickPosition.getY()) {
                if (activeTool == ActiveTool.SELECT) {
                    ListIterator<DrawableShape> iterator = drawnShapes.listIterator(drawnShapes.size());

                    while (iterator.hasPrevious()) {
                        DrawableShape shape = iterator.previous();

                        if (shape.contains(lastMouseClickPosition)) {
                            invoker.execute(new SelectCommand(this, activeShape, shape));

                            return;
                        }
                    }

                    if (activeShape != null) {
                        invoker.execute(new DeselectCommand(this, activeShape));
                    }
                }
            } else {
                if (activeTool == ActiveTool.SELECT) {
                    saveSelectionBox();
                } else {
                    invoker.execute(new DrawShapeCommand(this, tempActiveShape, new SelectCommand(this, activeShape, tempActiveShape)));
                }
            }

            canvasChanged = true;
        });

        canvas.setOnMouseDragged(event -> {
            canvasService.updateShape(tempActiveShape, event, lastMouseClickPosition, getCanvasDimension(), snapGrid);
            attributeController.update(tempActiveShape);
            canvasService.clear(drawLayer.getGraphicsContext2D());
            canvasService.drawShape(drawLayer.getGraphicsContext2D(), tempActiveShape);
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

    public Canvas getCanvas() {
        return this.canvas;
    }

    public void setCanvasChanged(boolean canvasChanged) {
        this.canvasChanged = canvasChanged;
    }

    public void saveDrawLayer() {
        if (activeShape != null) {
            canvasService.saveState(canvas.getGraphicsContext2D(), canvasService.convertCanvasToImage(drawLayer));

            canvasService.clear(drawLayer.getGraphicsContext2D());
        }
    }

    private void saveSelectionBox() {
        canvasService.clear(drawLayer.getGraphicsContext2D());

        for (DrawableShape shape : drawnShapes) {
            if (selectionBox.contains(shape)) {
                selectionBox.add(shape);
            }
        }

        if (selectionBox.isEmpty()) {
            return;
        }

        if (selectionBox.getSize() == 1) {
            invoker.execute(new SelectCommand(this, activeShape, selectionBox.getShape(0)));
            return;
        }

        selectionBox.resize();

        for (DrawableShape shape : drawnShapes) {
            if (shape.getShapeType() == ShapeType.SELECTION_BOX) {
                ((SelectionBox) shape).resize();
                if (selectionBox.isDuplicate(shape)) {
                    invoker.execute(new SelectCommand(this, activeShape, selectionBox.isDuplicate(shape) ? shape : selectionBox));
                    return;
                }
            }
        }

        selectionBox.optimize();
        invoker.execute(new DrawShapeCommand(this, selectionBox, new SelectCommand(this, activeShape, selectionBox)));
    }

    public double[] getCanvasDimension() {
        double[] dimension = new double[2];
        dimension[0] = canvas.getWidth();
        dimension[1] = canvas.getHeight();

        return dimension;
    }

    public void drawSnapGrid() {
        canvasService.drawSnapGrid(snapGridCanvas.getGraphicsContext2D(), snapGrid);
    }

    public void clearSnapgrid() {
        snapGridCanvas.getGraphicsContext2D().clearRect(0, 0, snapGridCanvas.getWidth(), snapGridCanvas.getHeight());
    }

    public void activateSnapGrid() {
        if (snapGrid.isActive()) {
            clearSnapgrid();
            snapGrid.setActive(false);
        } else {
            drawSnapGrid();
            snapGrid.setActive(true);
        }
    }

    public Image getBaseImage() {
        return baseImage;
    }

    public void setBaseImage(Image baseImage) {
        this.baseImage = baseImage;
    }

    public AttributeController getAttributeController() {
        return attributeController;
    }

    public ToolBarController getToolBarController() {
        return toolBarController;
    }

    public DrawableShape getActiveShape() {
        return activeShape;
    }

    public void setActiveShape(DrawableShape drawableShape) {
        activeShape = drawableShape;
        toolBarController.update(activeShape);
        attributeController.update(activeShape);
        attributeController.setAttributeChangeListeners();
        toolBarController.setToolBarListeners();
    }

    public void clearActiveShape() {
        activeShape = null;
        attributeController.resetAttributes();
        attributeController.unsetAttributeChangeListerners();
        toolBarController.unsetToolBarListeners();
    }

    public class ColorChangeListener implements ChangeListener<Color> {
        private Attribute attribute;

        public ColorChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Color> observableValue, Color oldValue, Color newValue) {
            if (activeShape != null && activeShape.isSelected() && drawnShapes.contains(activeShape)) {
                invoker.execute(new UpdateShapeCommand(CanvasController.this, activeShape, updateService.getAttributes(), attribute));
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
            if (activeShape != null && activeShape.isSelected() && drawnShapes.contains(activeShape)) {
                if (activeShape.getShapeType() == ShapeType.SELECTION_BOX) {
                    ((SelectionBox) activeShape).update(attribute, newValue - oldValue);
                    redrawCanvas();
                } else {
                    invoker.execute(new UpdateShapeCommand(CanvasController.this, activeShape, updateService.getAttributes(), attribute));
                }
            }
        }
    }

    public class GridSpinnerChangeListener implements ChangeListener<Double> {
        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            snapGrid.setSpacing(newValue);
            if (snapGrid.isActive()) {
                clearSnapgrid();
                drawSnapGrid();
            }
        }
    }

    public class UngroupActionEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (activeShape.getShapeType() == ShapeType.SELECTION_BOX) {
                ((SelectionBox) activeShape).clear();
                drawnShapes.remove(activeShape);
                activeShape.setSelected(false);
                attributeController.resetAttributes();
                redrawCanvas();
            }
        }
    }

    public class TextAreaChangeListener implements ChangeListener<String> {
        private Attribute attribute;

        public TextAreaChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (activeShape != null && activeShape.isSelected() && drawnShapes.contains(activeShape)) {
                invoker.execute(new UpdateShapeCommand(CanvasController.this, activeShape, updateService.getAttributes(), attribute));
            }
        }
    }

    public class AlignmentActionEvent implements EventHandler<ActionEvent> {
        private Alignment alignment;

        public AlignmentActionEvent(Alignment alignment) {
            this.alignment = alignment;
        }

        @Override
        public void handle(ActionEvent event) {
            if (activeShape.getShapeType() == ShapeType.SELECTION_BOX) {
                ((SelectionBox) activeShape).alignShapes(alignment);
                redrawCanvas();
            }
        }
    }
}
