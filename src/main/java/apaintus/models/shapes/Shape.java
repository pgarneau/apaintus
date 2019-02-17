package apaintus.models.shapes;

import apaintus.models.Point;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Shape extends Node {
    protected String fillColor;
    protected String strokeColor;
    protected double strokeSize;

    protected Shape() {
        super();
    }

    public Shape(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes);
        fillColor = shapeAttributes.getFillColor();
        strokeColor = shapeAttributes.getStrokeColor();
        strokeSize = shapeAttributes.getStrokeSize();
    }

    @Override
    public void update(NodeAttributes nodeAttributes) {
        coordinates = nodeAttributes.getCoordinates();
        width = nodeAttributes.getWidth();
        height = nodeAttributes.getHeight();
        orientation = nodeAttributes.getOrientation();
        center = computeCenter();
        boundingBox.update(
                center,
                width + strokeSize,
                height + strokeSize,
                orientation);
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
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
