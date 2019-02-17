package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.Node;

import java.util.Optional;

public class SelectCommand implements Command {
    private CanvasController canvasController;
    private String description;
    private Optional<Node> previousActiveNode;
    private Node newActiveNode;

    public SelectCommand(CanvasController canvasController, Node previousActiveNode, Node newActiveNode) {
        this.canvasController = canvasController;
        this.newActiveNode = newActiveNode;
        this.previousActiveNode = Optional.ofNullable(previousActiveNode);
        description = "Selected " + newActiveNode.getNodeType().toString();
    }

    @Override
    public void execute() {
        canvasController.clearActiveNode();

        if (previousActiveNode.isPresent()) {
            previousActiveNode.get().setSelected(false);
        }

        newActiveNode.setSelected(true);
        canvasController.setActiveNode(newActiveNode);
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        canvasController.clearActiveNode();

        if (previousActiveNode.isPresent()) {
            previousActiveNode.get().setSelected(true);
            canvasController.setActiveNode(previousActiveNode.get());
        }

        newActiveNode.setSelected(false);
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
}
