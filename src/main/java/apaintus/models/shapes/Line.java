package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.line.LineDrawService;

public class Line extends DrawableShape {
    private double width;
    private Point destinationCoordinates;

    public Line(ShapeAttributes shapeAttributes) {
        super(ShapeType.LINE, shapeAttributes);

        width = shapeAttributes.getWidth();
        destinationCoordinates = shapeAttributes.getDestinationCoordinates();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        width = shapeAttributes.getWidth();
        destinationCoordinates = shapeAttributes.getDestinationCoordinates();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new LineDrawService(this);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(Point destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }
}
