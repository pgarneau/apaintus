package apaintus.models.shapes;

public class Circle extends DrawableShape {
    private Circle() {}

    public Circle(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }

    @Override
    public void update(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }
}
