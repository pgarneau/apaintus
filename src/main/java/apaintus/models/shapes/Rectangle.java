package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.rectangle.RectangleDrawService;

public class Rectangle extends Shape {
    private Rectangle() {
        super();
    }

    public Rectangle(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.RECTANGLE;
        boundingBox = new BoundingBox(
                center,
                width + strokeSize,
                height + strokeSize,
                orientation);
    }

    @Override
    public RectangleDrawService getDrawService() {
        return new RectangleDrawService(this);
    }
}
