package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;

import java.util.Optional;

public class SelectCommand implements Command {
    private CanvasController canvasController;
    private String description = "select";
    private Optional<DrawableShape> previousActiveShape;
    private DrawableShape newActiveShape;

    public SelectCommand(CanvasController canvasController, DrawableShape previousActiveShape, DrawableShape newActiveShape) {
        this.canvasController = canvasController;
        this.newActiveShape = newActiveShape;
        this.previousActiveShape = Optional.ofNullable(previousActiveShape);
    }

    @Override
    public void execute() {
        canvasController.clearActiveShape();

        if (previousActiveShape.isPresent()) {
            previousActiveShape.get().setSelected(false);
        }

        newActiveShape.setSelected(true);
        canvasController.setActiveShape(newActiveShape);
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        canvasController.clearActiveShape();

        if (previousActiveShape.isPresent()) {
            previousActiveShape.get().setSelected(true);
            canvasController.setActiveShape(previousActiveShape.get());
        }

        newActiveShape.setSelected(false);
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
