package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.smiley.SmileyDrawService;

public class Smiley extends Shape {
    private Smiley() {
        super();
    }

    public Smiley(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.CIRCLE;
    }

    @Override
    public SmileyDrawService getDrawService() {
        return new SmileyDrawService(this);
    }
}
