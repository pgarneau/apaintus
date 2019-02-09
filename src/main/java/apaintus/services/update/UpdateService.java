package apaintus.services.update;

import apaintus.controllers.AttributeController;
import apaintus.controllers.ToolBarController;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.ShapeAttributes;

public class UpdateService {
    private AttributeController attributeController;
    private ToolBarController toolBarController;

    public UpdateService(AttributeController attributeController, ToolBarController toolBarController) {
        this.attributeController = attributeController;
        this.toolBarController = toolBarController;
    }

    public ShapeAttributes getAttributes() {
        Point coordinates = new Point(attributeController.getSpinnerValue(Attribute.COORDINATE_X), attributeController.getSpinnerValue(Attribute.COORDINATE_Y));

        return ShapeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withOrientation(attributeController.getSpinnerValue(Attribute.ORIENTATION))
                .withHeight(attributeController.getSpinnerValue(Attribute.HEIGHT))
                .withWidth(attributeController.getSpinnerValue(Attribute.WIDTH))
                .withStrokeSize(toolBarController.getStrokeSize())
                .withStrokeColor(toolBarController.getStrokeColor().toString())
                .withFillColor(toolBarController.getFillColor().toString())
                .withText(attributeController.getTextAreaValue())
                .build();
    }
}
