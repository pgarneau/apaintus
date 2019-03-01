package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;

public class FlipCommand implements Command {

    private double newValue, oldValue;
    private Node node;
    private String description;
    private CanvasController canvasController;
    private Flip flip;

    public FlipCommand(CanvasController canvasController, Node node, Flip flip) {
        this.node = node;
        this.flip = flip;
        this.canvasController = canvasController;
        description = "Flip " + flip.toString() + " " + node.getNodeType().toString();
    }

    @Override
    public void execute() {
        oldValue = node.getOrientation();
        switch (flip) {
            case VERTICAL:
                newValue = 360 - oldValue;
                node.setOrientation(newValue);
                break;
            case HORIZONTAL:
                newValue = 180 - oldValue;
                node.setOrientation(newValue);
                break;
        }
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

    public enum Flip {
        VERTICAL,
        HORIZONTAL
    }
}


