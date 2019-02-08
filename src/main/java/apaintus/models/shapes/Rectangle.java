package apaintus.models.shapes;

import apaintus.services.draw.rectangle.RectangleDrawService;

import static apaintus.models.shapes.ShapeType.RECTANGLE;

public class Rectangle extends FillableShape {

    private Rectangle() {
        super();
    }

    public Rectangle(ShapeAttributes shapeAttributes) {
        super(RECTANGLE, shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
    }

    @Override
    public RectangleDrawService getDrawService() {
        return new RectangleDrawService(this);
    }
}
