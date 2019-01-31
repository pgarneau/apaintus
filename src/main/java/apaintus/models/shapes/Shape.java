package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Shape {
    protected ShapeType shapeType;
    protected Point coordinates;
    protected double orientation;
    protected String strokeColor;
    protected double strokeSize;

    public Shape() {}

    public Shape(ShapeType shapeType, ShapeAttributes shapeAttributes) {
        this.shapeType = shapeType;
        this.coordinates = shapeAttributes.getCoordinates();
        this.orientation = shapeAttributes.getOrientation();
        this.strokeColor = shapeAttributes.getStrokeColor();
        this.strokeSize = shapeAttributes.getStrokeSize();
    }

    public void update(ShapeAttributes shapeAttributes) {
        this.coordinates = shapeAttributes.getCoordinates();
        this.orientation = shapeAttributes.getOrientation();
        this.strokeColor = shapeAttributes.getStrokeColor();
        this.strokeSize = shapeAttributes.getStrokeSize();
    }

    public abstract DrawService getDrawService();

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(double strokeSize) {
        this.strokeSize = strokeSize;
    }
}
