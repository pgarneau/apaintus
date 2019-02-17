package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.Node;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements Command {
    private static final String DESCRIPTION = "Clear Canvas";

    private CanvasController canvasController;
    private List<Node> nodeList;
    private List<Node> oldNodeList;

    public ClearCommand(CanvasController canvasController) {
        this.canvasController = canvasController;
        nodeList = canvasController.getNodeList();
        oldNodeList = new ArrayList<>(nodeList);
    }

    @Override
    public void execute() {
        canvasController.clearCanvas();
    }

    @Override
    public void undo() {
        nodeList.addAll(oldNodeList);
        canvasController.setNodeList(nodeList);
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        canvasController.clearCanvas();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }
}
