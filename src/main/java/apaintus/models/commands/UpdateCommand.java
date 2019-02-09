package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Attribute;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.ShapeAttributes;

public class UpdateCommand implements Command {
    private CanvasController canvasController;
    private DrawableShape shape;
    private ShapeAttributes oldShapeAttributes;
    private ShapeAttributes shapeAttributes;
    private Attribute attribute;

    public UpdateCommand(CanvasController canvasController, DrawableShape shape, ShapeAttributes shapeAttributes, Attribute attribute) {
        this.canvasController = canvasController;
        this.shapeAttributes = shapeAttributes;
        this.shape = shape;
        this.attribute = attribute;
        oldShapeAttributes = this.shape.getShapeAttributes();
    }

    @Override
    public void execute() {
        shape.update(shapeAttributes);
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        shape.update(oldShapeAttributes);
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        execute();
    }

    @Override
    public Command getCommand(int index) {
        return null;
    }

    public Attribute getAttribute() {
        return attribute;
    }
}
