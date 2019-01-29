package apaintus.models.shapes;

public class Rectangle extends DrawableShape {
    private Rectangle() {}

    public Rectangle(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }

    @Override
    public void update(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }
}
