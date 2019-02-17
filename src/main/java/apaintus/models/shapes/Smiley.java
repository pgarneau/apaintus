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
        boundingBox = new BoundingBox(
                new Point(coordinates.getX() - strokeSize / 2, coordinates.getY() - strokeSize / 2),
                width + strokeSize,
                height + strokeSize,
                orientation);
    }

    @Override
    public SmileyDrawService getDrawService() {
        return new SmileyDrawService(this);
    }
}
