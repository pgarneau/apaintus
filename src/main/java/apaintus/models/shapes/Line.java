package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.ShapeDrawService;
import apaintus.services.draw.line.LineDrawService;

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
        System.out.println("I AM A LINE");
        System.out.println(height);
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
    public ShapeDrawService getDrawService() {
        return new LineDrawService(this);
    }
}
