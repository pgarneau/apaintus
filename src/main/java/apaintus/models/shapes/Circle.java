package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.circle.CircleDrawService;

public class Circle extends FillableShape {
    public Circle(ShapeAttributes shapeAttributes) {
        super(ShapeType.CIRCLE, shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new CircleDrawService(this);
    }
}
