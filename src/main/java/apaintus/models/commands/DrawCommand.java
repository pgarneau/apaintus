package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.commands.Command;
import apaintus.models.shapes.DrawableShape;

import java.util.List;

public class DrawCommand implements Command {
    private CanvasController canvasController;
    private List<DrawableShape> drawnShapes;
    private DrawableShape shape;

    public DrawCommand(CanvasController canvasController, DrawableShape shape) {
        this.canvasController = canvasController;
        this.shape = shape;
        drawnShapes = this.canvasController.getDrawnShapes();
    }

    @Override
    public void execute() {
        drawnShapes.add(shape);

        canvasController.saveDrawLayer();
    }

    @Override
    public void undo() {
        drawnShapes.remove(shape);

        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        drawnShapes.add(shape);

        canvasController.redrawCanvas();
    }

    @Override
    public Command getCommand(int index) {
        return null;
    }
}
