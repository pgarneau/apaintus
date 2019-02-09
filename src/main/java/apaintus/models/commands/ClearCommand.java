package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.commands.Command;
import apaintus.models.shapes.DrawableShape;

import java.util.ArrayList;
import java.util.List;

public class ClearCommand implements Command {
    private CanvasController canvasController;
    private List<DrawableShape> drawableShapeList;
    private List<DrawableShape> oldDrawableShapeList;

    public ClearCommand(CanvasController canvasController) {
        this.canvasController = canvasController;
        drawableShapeList = canvasController.getDrawnShapes();
        oldDrawableShapeList = new ArrayList<>(drawableShapeList);
    }

    @Override
    public void execute() {
        canvasController.clearCanvas();
    }

    @Override
    public void undo() {
        drawableShapeList.addAll(oldDrawableShapeList);
        canvasController.setDrawnShapes(drawableShapeList);
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        canvasController.clearCanvas();
    }

    @Override
    public Command getCommand(int index) {
        return null;
    }
}
