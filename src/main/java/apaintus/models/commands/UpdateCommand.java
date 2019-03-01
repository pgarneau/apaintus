package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.nodes.Node;
import apaintus.util.ReflectionUtil;

public class UpdateCommand<T> implements Command {
    private CanvasController canvasController;
    private Node node;
    private T oldValue;
    private T newValue;
    private Attribute attribute;
    private String description;

    public UpdateCommand(CanvasController canvasController, Node node, Attribute attribute, T oldValue, T newValue) {
        this.canvasController = canvasController;
        this.node = node;
        this.attribute = attribute;
        this.oldValue = oldValue;
        this.newValue = newValue;
        description = "Updated " + node.getNodeType().toString() + " " + this.attribute.toString();
    }

    @Override
    public void execute() {
        setAttribute(attribute, newValue);
        canvasController.redrawCanvas();
    }

    @Override
    public void undo() {
        setAttribute(attribute, oldValue);
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

    private void setAttribute(Attribute attribute, T value) {
        switch (attribute) {
            case COORDINATE_X:
                node.setCoordinates(new Point(Double.valueOf(value.toString()), node.getCoordinates().getY()));
            break;

            case COORDINATE_Y:
                node.setCoordinates(new Point(node.getCoordinates().getX(), Double.valueOf(value.toString())));
                break;

            case WIDTH:
                node.setWidth(Double.valueOf(value.toString()));
                break;

            case HEIGHT:
                node.setHeight(Double.valueOf(value.toString()));
                break;

            case ORIENTATION:
                node.setOrientation(Double.valueOf(value.toString()));
                break;

            default:
                ReflectionUtil.set(node, attribute.toString(), value);
        }
    }

    private void updateControllers() {
        canvasController.getAttributeController().unsetAttributeChangeListeners();
        canvasController.getToolBarController().unsetToolBarListeners();
        canvasController.getToolBarController().update(node);
        canvasController.getAttributeController().update(node);
        canvasController.getAttributeController().setAttributeChangeListeners();
        canvasController.getToolBarController().setToolBarListeners();
    }
}
