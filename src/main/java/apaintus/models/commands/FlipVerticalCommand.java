package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Point;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import javafx.scene.transform.Rotate;
import javafx.scene.shape.*;

public class FlipVerticalCommand implements Command {

    private Node node;
    private Params param;
    private Point newCoordinate;
    private Point oldCoordinate;
    private String description;
    private CanvasController canvasController;

    public FlipVerticalCommand(Params param, Node node, CanvasController canvasController) {
        this.node = node;
        this.param = param;
        this.canvasController = canvasController;
        description = "Flip " + param.toString() + " " + node.getNodeType().toString();
    }

    @Override
    public void execute() {
        Rotate flip = new Rotate(180, node.getCenter().getX(), node.getCenter().getY());
        NodeType type = node.getNodeType();
        switch (type){
            case RECTANGLE:
                Rectangle rect = new Rectangle(node.getCoordinates().getX(), node.getCoordinates().getY(), node.getWidth(), node.getHeight());
                rect.getTransforms().add(flip);
                node.setCoordinates(new Point(rect.getX(), rect.getY()));
                node.setOrientation(rect.getRotate());
                break;
            case CIRCLE:
                break;
            case LINE:
                break;
            case SMILEY:
                break;
        }
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        switch (param) {
            case VERTICAL:
                newCoordinate = oldCoordinate;
                oldCoordinate = node.getCoordinates();
                node.setCoordinates(newCoordinate);
                break;
            case HORIZONTAL:
                node.setHeight(-node.getHeight());
                break;
        }
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public String getDescription() {
        return description;
    }

    public enum Params {
        VERTICAL,
        HORIZONTAL
    }
}


