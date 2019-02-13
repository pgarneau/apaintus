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
        Point coordinates = new Point(attributeController.getDoubleAttribute(Attribute.COORDINATE_X), attributeController.getDoubleAttribute(Attribute.COORDINATE_Y));

        return ShapeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withOrientation(attributeController.getDoubleAttribute(Attribute.ORIENTATION))
                .withHeight(attributeController.getDoubleAttribute(Attribute.HEIGHT))
                .withWidth(attributeController.getDoubleAttribute(Attribute.WIDTH))
                .withStrokeSize(toolBarController.getStrokeSize())
                .withStrokeColor(toolBarController.getStrokeColor().toString())
                .withFillColor(toolBarController.getFillColor().toString())
                .withText(attributeController.getStringAttribute(Attribute.TEXT))
                .build();
    }
}
