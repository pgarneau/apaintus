package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.rectangle.RectangleDrawService;

import static apaintus.models.shapes.ShapeType.RECTANGLE;

public class Rectangle extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;

    public Rectangle(ShapeAttributes shapeAttributes) {
        super(RECTANGLE, shapeAttributes);

        width = shapeAttributes.getWidth();
        height = shapeAttributes.getHeight();
        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        width = shapeAttributes.getWidth();
        height = shapeAttributes.getHeight();
        fillColor = shapeAttributes.getFillColor();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public RectangleDrawService getDrawService() {
        return new RectangleDrawService(this);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
}
