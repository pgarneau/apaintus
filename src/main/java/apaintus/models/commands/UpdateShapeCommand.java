package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Attribute;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.ShapeAttributes;

public class UpdateShapeCommand implements Command {
    private CanvasController canvasController;
    private DrawableShape shape;
    private ShapeAttributes oldShapeAttributes;
    private ShapeAttributes shapeAttributes;
    private Attribute attribute;
    private String description;

    public UpdateShapeCommand(CanvasController canvasController, DrawableShape shape, ShapeAttributes shapeAttributes, Attribute attribute) {
        this.canvasController = canvasController;
        this.shapeAttributes = shapeAttributes;
        this.shape = shape;
        this.attribute = attribute;
        oldShapeAttributes = this.shape.getShapeAttributes();
        description = "Updated " + shape.getShapeType().toString() + " " + this.attribute.toString();
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

        updateControllers();
    }

    @Override
    public void redo() {
        execute();
        updateControllers();
    }

    @Override
    public String getDescription() {
        return description;
    }

    private void updateControllers() {
        canvasController.getAttributeController().unsetAttributeChangeListerners();
        canvasController.getToolBarController().unsetToolBarListeners();
        canvasController.getToolBarController().update(shape);
        canvasController.getAttributeController().update(shape);
        canvasController.getAttributeController().setAttributeChangeListeners();
        canvasController.getToolBarController().setToolBarListeners();
    }
}
