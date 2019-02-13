package apaintus.models.commands;

import apaintus.controllers.AttributeController;
import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;

import java.util.Optional;

public class DeselectCommand implements Command {
    private CanvasController canvasController;
    private AttributeController attributeController;
    private Optional<DrawableShape> previousActiveShape;
    private String description = "hello";

    public DeselectCommand(CanvasController canvasController, AttributeController attributeController, DrawableShape previousActiveShape) {
        this.canvasController = canvasController;
        this.attributeController = attributeController;
        this.previousActiveShape = Optional.ofNullable(previousActiveShape);
    }

    @Override
    public void execute() {
        if (previousActiveShape.isPresent()) {
            previousActiveShape.get().setSelected(false);
        }

        canvasController.clearActiveShape();
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        canvasController.clearActiveShape();

        if (previousActiveShape.isPresent()) {
            previousActiveShape.get().setSelected(true);
            canvasController.setActiveShape(previousActiveShape.get());
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
