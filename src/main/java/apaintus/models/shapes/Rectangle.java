package apaintus.models.shapes;

import apaintus.services.draw.rectangle.RectangleDrawService;

public class Rectangle extends Shape {
    private Rectangle() {
        super();
    }

    public Rectangle(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.RECTANGLE;
    }

    @Override
    public RectangleDrawService getDrawService() {
        return new RectangleDrawService(this);
    }
}
