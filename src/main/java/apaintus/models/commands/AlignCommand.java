package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Alignment;
import apaintus.models.Attribute;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import apaintus.models.nodes.SelectionBox;
import apaintus.util.ReflectionUtil;

import java.util.Stack;

public class AlignCommand implements Command {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    private CanvasController canvasController;
    private SelectionBox selectionBox;
    private Alignment alignment;
    private String description;

    public AlignCommand(CanvasController canvasController, SelectionBox selectionBox, Alignment alignment) {
        this.canvasController = canvasController;
        this.selectionBox = selectionBox;
        this.alignment = alignment;

        undoStack = new Stack<>();
        redoStack = new Stack<>();

        description = alignment.toString() + " alignment";
    }

    @Override
    public void execute() {
        Attribute attribute;
        double[] values; // oldValue = 0, newValue = 1

        if (alignment == Alignment.RIGHT || alignment == Alignment.LEFT) {
            attribute = Attribute.COORDINATE_X;
        } else {
            attribute = Attribute.COORDINATE_Y;
        }

        for (Node node : selectionBox.getNodeList()) {
            double strokeSize = ReflectionUtil.get(node, Attribute.STROKE_SIZE.toString());
            strokeSize = (strokeSize / 2.0) + 1.0;

            values = (node.getNodeType() == NodeType.LINE) ?
                    computeLineValues(node, strokeSize) :
                    computeValues(node, strokeSize, attribute);

            Command updateNode = new UpdateCommand(canvasController, node, attribute, values[0], values[1]);
            undoStack.push(updateNode);
            updateNode.execute();
        }

        selectionBox.resize();
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        while (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }

        selectionBox.resize();
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        while (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        }

        selectionBox.resize();
        canvasController.redrawCanvas();
    }

    @Override
    public String getDescription() {
        return description;
    }

    private double[] computeValues(Node node, double strokeSize, Attribute attribute) {
        double newValue;
        double oldValue;

        if (attribute == Attribute.COORDINATE_X) {
            newValue = (alignment == Alignment.LEFT) ?
                    selectionBox.getCoordinates().getX() + strokeSize :
                    (selectionBox.getCoordinates().getX() + selectionBox.getWidth()) - node.getWidth() - strokeSize;
            oldValue = node.getCoordinates().getX();
        } else {
            newValue = (alignment == Alignment.TOP) ?
                    selectionBox.getCoordinates().getY() + strokeSize :
                    (selectionBox.getCoordinates().getY() + selectionBox.getHeight()) - node.getHeight() - strokeSize;
            oldValue = node.getCoordinates().getY();
        }

        return new double[]{oldValue, newValue};
    }

    private double[] computeLineValues(Node node, double strokeSize) {
        double newValue;
        double oldValue;

        double width = node.getCenter().getX() - node.getCoordinates().getX();
        double height = node.getCenter().getY() - node.getCoordinates().getY();

        double extremityWidth = Math.abs((node.getBoundingBox().getVertices()[0].getX()
                - node.getBoundingBox().getVertices()[3].getX())) / 2.0;
        double extremityHeight = Math.abs(node.getBoundingBox().getVertices()[0].getY()
                - node.getBoundingBox().getVertices()[3].getY()) / 2.0;

        if (alignment == Alignment.LEFT) {
            newValue = (width < 0) ?
                    selectionBox.getCoordinates().getX() + Math.abs(2.0 * width) + strokeSize + extremityWidth :
                    selectionBox.getCoordinates().getX() + strokeSize + extremityWidth;
            oldValue = node.getCoordinates().getX();
        } else if (alignment == Alignment.RIGHT) {
            newValue = (width <= 0) ?
                    selectionBox.getCoordinates().getX() + selectionBox.getWidth() - strokeSize - extremityWidth :
                    selectionBox.getCoordinates().getX() + selectionBox.getWidth() - Math.abs(2.0 * width)
                            - strokeSize - extremityWidth;
            oldValue = node.getCoordinates().getX();
        } else if (alignment == Alignment.TOP) {
            newValue = (height < 0) ?
                    selectionBox.getCoordinates().getY() + Math.abs(2.0 * height) + strokeSize + extremityHeight :
                    selectionBox.getCoordinates().getY() + strokeSize + extremityHeight;
            oldValue = node.getCoordinates().getY();
        } else {
            newValue = (height <= 0) ?
                    selectionBox.getCoordinates().getY() + selectionBox.getHeight() - strokeSize - extremityHeight :
                    selectionBox.getCoordinates().getY() + selectionBox.getHeight() - Math.abs(2.0 * height)
                            - strokeSize - extremityHeight;
            oldValue = node.getCoordinates().getY();
        }

        return new double[]{oldValue, newValue};
    }
}
