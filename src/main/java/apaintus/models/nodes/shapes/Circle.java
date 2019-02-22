package apaintus.models.nodes.shapes;

import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.circle.CircleDrawService;
import apaintus.services.update.CircleUpdateService;
import apaintus.services.update.UpdateService;

public class Circle extends Shape {
    private Circle() {
        super();
    }

    public Circle(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.CIRCLE;
    }

    @Override
    public DrawService getDrawService() {
        return new CircleDrawService(this);
    }

    @Override
    public UpdateService getUpdateService() {
        return new CircleUpdateService(this);
    }
}
