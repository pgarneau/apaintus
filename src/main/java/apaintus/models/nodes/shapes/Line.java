package apaintus.models.nodes.shapes;

import apaintus.models.Point;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.line.LineDrawService;
import apaintus.services.update.LineUpdateService;
import apaintus.services.update.UpdateService;

public class Line extends Shape {
    private Line() {
        super();
    }

    public Line(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.LINE;
    }

    @Override
    public void updateBoundingBox() {
        boundingBox.update(
                center,
                width,
                height,
                orientation
        );
    }

    @Override
    public void computeCenter() {
        double radianOrientation = Math.toRadians(orientation);
        double tempWidth = width / 2;
        double tempHeight = height / 2;

        center =  new Point(
                coordinates.getX() + (tempWidth * Math.cos(radianOrientation) + tempHeight * Math.sin(radianOrientation)),
                coordinates.getY() - (tempWidth * Math.sin(radianOrientation) - tempHeight * Math.cos(radianOrientation))
        );
    }

    @Override
    public DrawService getDrawService() {
        return new LineDrawService(this);
    }

    @Override
    public UpdateService getUpdateService() {
        return new LineUpdateService(this);
    }
}
