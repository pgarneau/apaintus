package apaintus.controllers;

import apaintus.models.Alignment;
import apaintus.models.commands.*;
import apaintus.models.shapes.Node;
import apaintus.models.shapes.NodeType;
import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.SelectionBox;
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

import java.lang.reflect.Method;
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
    private FigureLogController figureLogController;

    private CanvasService canvasService;
    private UpdateService updateService;

    private Point lastMouseClickPosition;
    private Node activeNode;
    private Node tempActiveNode;
    private List<Node> nodeList = new ArrayList<>();
    private Image baseImage;
    private boolean canvasChanged;
    private ActiveTool activeTool;
    private Invoker invoker;
    private DeselectCommand deselectCommand;

    @Override
    public void injectParentController(Controller controller) {
        this.toolBarController = controller.getToolBarController();
        this.attributeController = controller.getAttributeController();
        this.figureLogController = controller.getFigureLogController();
        this.canvasService = new CanvasService(this.toolBarController);
        this.updateService = new UpdateService(this.attributeController, this.toolBarController);

        snapGrid = new SnapGrid(toolBarController.getSnapGridSize(), canvas.getWidth(), canvas.getHeight(), false);
        invoker = controller.getInvoker();
    }

    @Override
    public void initialize() {
        canvas.setOnMousePressed(event -> {
            lastMouseClickPosition = new Point(event.getX(), event.getY());
            activeTool = toolBarController.getActiveTool();

            tempActiveNode = canvasService.createNode(activeTool, event, getCanvasDimension());

            deselectCommand = new DeselectCommand(this, activeNode);
            deselectCommand.execute();
        });

        canvas.setOnMouseReleased(event -> {
            deselectCommand.undo();

            if (event.getX() == lastMouseClickPosition.getX() && event.getY() == lastMouseClickPosition.getY()) {
                if (activeTool == ActiveTool.SELECT) {
                    ListIterator<Node> iterator = nodeList.listIterator(nodeList.size());

                    while (iterator.hasPrevious()) {
                        Node node = iterator.previous();

                        if (node.getBoundingBox().contains(lastMouseClickPosition)) {
                            invoker.execute(new SelectCommand(this, activeNode, node));

                            return;
                        }
                    }

                    if (activeNode != null) {
                        invoker.execute(new DeselectCommand(this, activeNode));
                    }
                }
            } else {
                if (activeTool == ActiveTool.SELECT) {
                    saveSelectionBox((SelectionBox) tempActiveNode);
                } else {
                    invoker.execute(new DrawNodeCommand(this, tempActiveNode, new SelectCommand(this, activeNode, tempActiveNode)));
                }
            }

            canvasChanged = true;
        });

        canvas.setOnMouseDragged(event -> {
            canvasService.updateNode(tempActiveNode, event, lastMouseClickPosition, getCanvasDimension(), snapGrid);
            attributeController.update(tempActiveNode);
            canvasService.clear(drawLayer.getGraphicsContext2D());
            canvasService.drawNode(drawLayer.getGraphicsContext2D(), tempActiveNode);
        });
    }

    public void redrawCanvas() {
        canvasService.clear(canvas.getGraphicsContext2D());

        if (baseImage != null) {
            drawImage(baseImage);
        }

        for (Node node : nodeList) {
            canvasService.drawNode(canvas.getGraphicsContext2D(), node);
        }

        figureLogController.updateFigureList(nodeList);
        figureLogController.selectFigureListItem(activeNode);
    }

    public void drawImage(Image image) {
        baseImage = image;
        canvasService.saveState(canvas.getGraphicsContext2D(), image);
    }

    public void clearCanvas() {
        nodeList.clear();
        figureLogController.updateFigureList(nodeList);
        canvasService.clear(drawLayer.getGraphicsContext2D());
        canvasService.clear(canvas.getGraphicsContext2D());
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public boolean isCanvasChanged() {
        return canvasChanged;
    }

    public Image getCanvasImage() {
        // Remove the bounding box around the selected shape when saving canvas to image
        activeNode.setSelected(false);
        redrawCanvas();
        Image image = canvasService.convertCanvasToImage(canvas);
        activeNode.setSelected(true);
        redrawCanvas();

        return image;
    }

    public Canvas getCanvas(){
        return this.canvas;
    }

    public void setCanvasChanged(boolean canvasChanged) {
        this.canvasChanged = canvasChanged;
    }

    public void saveDrawLayer() {
        if (activeNode != null) {
            canvasService.saveState(canvas.getGraphicsContext2D(), canvasService.convertCanvasToImage(drawLayer));
            canvasService.clear(drawLayer.getGraphicsContext2D());
        }
    }

    private void saveSelectionBox(SelectionBox selectionBox) {
        canvasService.clear(drawLayer.getGraphicsContext2D());

        for (Node node : nodeList) {
            if (selectionBox.contains(node)) {
                selectionBox.add(node);
            }
        }

        if (selectionBox.getNodeList().isEmpty()) {
            return;
        }

        selectionBox.resize();
        selectionBox.optimize();

        if (selectionBox.getNodeList().size() == 1) {
            invoker.execute(new SelectCommand(this, activeNode, selectionBox.getNodeList().get(0)));
            return;
        }

        for (Node node : nodeList) {
            if (node.getNodeType() == NodeType.SELECTION_BOX) {
                if (selectionBox.isDuplicate((SelectionBox) node)) {
                    invoker.execute(new SelectCommand(this, activeNode, node));
                }
            }
        }

        invoker.execute(new DrawNodeCommand(this, selectionBox, new SelectCommand(this, activeNode, selectionBox)));
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

    public void clearSnapGrid() {
        snapGridCanvas.getGraphicsContext2D().clearRect(0, 0, snapGridCanvas.getWidth(), snapGridCanvas.getHeight());
    }

    public void toggleSnapGrid() {
        if (snapGrid.isActive()) {
            clearSnapGrid();
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

    public Node getActiveNode() {
        return activeNode;
    }

    public void setActiveNode(Node node) {
        activeNode = node;
        toolBarController.update(activeNode);
        attributeController.update(activeNode);
        attributeController.setAttributeChangeListeners();
        toolBarController.setToolBarListeners();
    }

    public void clearActiveNode() {
        activeNode = null;
        attributeController.resetAttributes();
        attributeController.unsetAttributeChangeListeners();
        toolBarController.unsetToolBarListeners();
    }

    public class ColorChangeListener implements ChangeListener<Color> {
        private Attribute attribute;

        public ColorChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends Color> observableValue, Color oldValue, Color newValue) {
            if (activeNode != null) {
                invoker.execute(new UpdateCommand(CanvasController.this, activeNode, attribute, oldValue.toString(), newValue.toString()));
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
            if (activeNode != null) {
                invoker.execute(new UpdateCommand(CanvasController.this, activeNode, attribute, oldValue, newValue));
            }
//            if (activeNode != null && activeNode.isSelected() && nodeList.contains(activeNode)) {
//                if (activeNode.getNodeType() == NodeType.SELECTION_BOX) {
//                    ((SelectionBox) activeNode).update(attribute, newValue - oldValue);
//                    redrawCanvas();
//                } else {
//                    invoker.execute(new UpdateCommand(CanvasController.this, activeNode, updateService.getAttributes(), attribute));
//                }
//            }
        }
    }

    public class SnapGridSizeListener implements ChangeListener<Double> {
        @Override
        public void changed(ObservableValue<? extends Double> observableValue, Double oldValue, Double newValue) {
            snapGrid.setSize(newValue);
            if (snapGrid.isActive()) {
                clearSnapGrid();
                drawSnapGrid();
            }
        }
    }

    public class UngroupActionEvent implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
//            if (activeNode.getShapeType() == ShapeType.SELECTION_BOX) {
//                ((SelectionBox) activeNode).clear();
//                nodeList.remove(activeNode);
//                activeNode.setSelected(false);
//                attributeController.resetAttributes();
//                redrawCanvas();
//            }
        }
    }

    public class TextAreaChangeListener implements ChangeListener<String> {
        private Attribute attribute;

        public TextAreaChangeListener(Attribute attribute) {
            this.attribute = attribute;
        }

        @Override
        public void changed(ObservableValue<? extends String> observableValue, String oldValue, String newValue) {
            if (activeNode != null) {
                invoker.execute(new UpdateCommand(CanvasController.this, activeNode, attribute, newValue, oldValue));
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
//            if (activeNode.getShapeType() == ShapeType.SELECTION_BOX) {
//                ((SelectionBox) activeNode).alignShapes(alignment);
//                redrawCanvas();
//            }
        }
    }
}
