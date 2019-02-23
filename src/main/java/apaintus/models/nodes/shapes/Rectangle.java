package apaintus.models.nodes.shapes;

import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.rectangle.RectangleDrawService;
import apaintus.services.update.RectangleUpdateService;
import apaintus.services.update.UpdateService;

public class Rectangle extends Shape {
    private Rectangle() {
        super();
    }

    public Rectangle(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.RECTANGLE;
    }

    @Override
    public DrawService getDrawService() {
        return new RectangleDrawService(this);
    }

    @Override
    public UpdateService getUpdateService() {
        return new RectangleUpdateService(this);
    }
}
