package apaintus.models.shapes;

import apaintus.services.draw.ShapeDrawService;
import apaintus.services.draw.circle.CircleDrawService;

public class Circle extends Shape {
    private Circle() {
        super();
    }

    public Circle(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.CIRCLE;
    }

    @Override
    public ShapeDrawService getDrawService() {
        return new CircleDrawService(this);
    }
}
