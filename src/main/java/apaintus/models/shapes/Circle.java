package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.circle.CircleDrawService;

public class Circle extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;

    public Circle(ShapeAttributes shapeAttributes) {
        super(ShapeType.CIRCLE, shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new CircleDrawService(this);
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
}
