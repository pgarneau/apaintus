package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;

import java.util.Optional;

public class DeselectCommand implements Command {
    private CanvasController canvasController;
    private Optional<Node> previousActiveNode;
    private String description = "Deselected";

    public DeselectCommand(CanvasController canvasController, Node previousActiveNode) {
        this.canvasController = canvasController;
        this.previousActiveNode = Optional.ofNullable(previousActiveNode);
    }

    @Override
    public void execute() {
        if (previousActiveNode.isPresent()) {
            previousActiveNode.get().setSelected(false);
        }

        canvasController.clearActiveNode();
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        canvasController.clearActiveNode();

        if (previousActiveNode.isPresent()) {
            previousActiveNode.get().setSelected(true);
            canvasController.setActiveNode(previousActiveNode.get());
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
}
