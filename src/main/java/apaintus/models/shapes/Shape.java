package apaintus.models.shapes;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Shape {
    protected ShapeType shapeType;
    protected double[] coordinates;
    protected double orientation;
    protected String strokeColor;
    protected int strokeSize;

    public Shape() {}

    public Shape(ShapeType shapeType, ShapeAttributes shapeAttributes) {
        this.shapeType = shapeType;
        this.coordinates = shapeAttributes.getCoordinates();
        this.orientation = shapeAttributes.getOrientation();
        this.strokeColor = shapeAttributes.getStrokeColor();
        this.strokeSize = shapeAttributes.getStrokeSize();
    }

    public ShapeType getShapeType() {
        return shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
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

    public int getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(int strokeSize) {
        this.strokeSize = strokeSize;
    }

    public void update(ShapeAttributes shapeAttributes) {
        this.coordinates = shapeAttributes.getCoordinates();
        this.orientation = shapeAttributes.getOrientation();
        this.strokeColor = shapeAttributes.getStrokeColor();
        this.strokeSize = shapeAttributes.getStrokeSize();
    }
}
