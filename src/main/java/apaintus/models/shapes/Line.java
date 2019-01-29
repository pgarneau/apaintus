package apaintus.models.shapes;

public class Line extends DrawableShape {
    private Line() {}

    public Line(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }

    @Override
    public void update(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }
}
