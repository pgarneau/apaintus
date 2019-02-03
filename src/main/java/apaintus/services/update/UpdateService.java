package apaintus.services.update;

import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.ShapeAttributes;

public abstract class UpdateService {
    private DrawableShape shape;

    protected double orientation;
    protected double strokeSize;
    protected double width;
    protected double height;
    protected Point coordinates;
    protected String strokeColor;
    protected String fillColor;

    protected UpdateService(DrawableShape shape) {
        this.shape = shape;
        this.orientation = shape.getOrientation();
        this.strokeSize = shape.getStrokeSize();
        this.coordinates = shape.getCoordinates();
        this.strokeColor = shape.getStrokeColor();
        this.width = shape.getWidth();
        this.height = shape.getHeight();
    }

    public void update(Attribute attribute, String value) {
        switch (attribute) {
            case STROKE_COLOR:
                this.strokeColor = value;
                break;
        }
    }

    public void update(Attribute attribute, double value) {
        switch (attribute) {
            case STROKE_SIZE:
                this.strokeSize = value;
                break;
            case COORDINATE_X:
                this.coordinates = new Point(value, coordinates.getY());
                break;
            case COORDINATE_Y:
                this.coordinates = new Point(coordinates.getX(), value);
                break;
            case ORIENTATION:
                this.orientation = value;
                break;
            case WIDTH:
                this.width = value;
                break;
            case HEIGHT:
                this.height = value;
                break;
        }
    }

    protected void updateShape(){
        shape.update(ShapeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .withFillColor(fillColor)
                .withStrokeColor(strokeColor)
                .withStrokeSize(strokeSize)
                .build());
    }
}
