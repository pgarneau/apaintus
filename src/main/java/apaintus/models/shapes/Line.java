package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.line.LineDrawService;

public class Line extends DrawableShape {
    private Point destinationCoordinates;

    public Line(ShapeAttributes shapeAttributes) {
        super(ShapeType.LINE, shapeAttributes);

        destinationCoordinates = shapeAttributes.getDestinationCoordinates();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        destinationCoordinates = shapeAttributes.getDestinationCoordinates();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new LineDrawService(this);
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public void setDestinationCoordinates(Point destinationCoordinates) {
        this.destinationCoordinates = destinationCoordinates;
    }
}
