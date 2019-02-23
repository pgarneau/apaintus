package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;

import java.util.List;

public class DrawNodeCommand implements Command {
    private String description;
    private CanvasController canvasController;
    private List<Node> nodeList;
    private Node node;
    private SelectCommand selectCommand;

    public DrawNodeCommand(CanvasController canvasController, Node node, SelectCommand selectCommand) {
        this.canvasController = canvasController;
        this.node = node;
        this.selectCommand = selectCommand;
        nodeList = this.canvasController.getNodeList();
        description = "Draw " + node.getNodeType().toString();
    }

    @Override
    public void execute() {
        selectCommand.execute();
        nodeList.add(node);
        canvasController.saveDrawLayer();
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        selectCommand.undo();
        nodeList.remove(node);
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        selectCommand.redo();
        nodeList.add(node);
        canvasController.redrawCanvas();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
