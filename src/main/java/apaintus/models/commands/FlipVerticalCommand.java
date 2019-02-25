package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Point;
import apaintus.models.nodes.Node;

public class FlipVerticalCommand implements Command {

    private Node node;
    private String description;
    private CanvasController canvasController;

    public FlipVerticalCommand(Params param, Node node, CanvasController canvasController) {
        this.node = node;
        this.canvasController = canvasController;
        description = "Flip " + param.toString() + " " + node.getNodeType().toString();
    }

    @Override
    public void execute() {
        double rotationAxis = node.getCenter().getY();
        double x1 = node.getCoordinates().getX();
        double y1 = node.getCoordinates().getY();

        double d = 0;
        double x2 = 2 * d - x1;
        double y2 = 2 * d * 0 - y1 + 2 * rotationAxis;

        node.setCoordinates(new Point(x2, y2));
        System.out.println(node.getCoordinates().getX() +" " + node.getCoordinates().getY());
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
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


