package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;

import java.util.List;

public class DrawShapeCommand implements Command {
    private String description;
    private CanvasController canvasController;
    private List<DrawableShape> drawableShapeList;
    private DrawableShape shape;
    private SelectCommand selectCommand;

    public DrawShapeCommand(CanvasController canvasController, DrawableShape shape, SelectCommand selectCommand) {
        this.canvasController = canvasController;
        this.shape = shape;

        description = "Draw " + shape.getShapeType().toString();
        drawableShapeList = this.canvasController.getDrawnShapes();
        this.selectCommand = selectCommand;
    }

    @Override
    public void execute() {
        selectCommand.execute();
        drawableShapeList.add(shape);
        canvasController.saveDrawLayer();
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        selectCommand.undo();
        drawableShapeList.remove(shape);
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        selectCommand.redo();
        drawableShapeList.add(shape);
        canvasController.redrawCanvas();
    }

    @Override
    public String getDescription() {
        return description;
    }
}
