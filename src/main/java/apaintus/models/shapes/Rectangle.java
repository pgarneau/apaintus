package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.rectangle.RectangleDrawService;
import apaintus.services.update.UpdateService;
import apaintus.services.update.rectangle.RectangleUpdateService;

import static apaintus.models.shapes.ShapeType.RECTANGLE;

public class Rectangle extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;

    public Rectangle(ShapeAttributes shapeAttributes) {
        super(RECTANGLE, shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public RectangleDrawService getDrawService() {
        return new RectangleDrawService(this);
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public UpdateService getUpdateService() {
        return new RectangleUpdateService(this);
    }
}
