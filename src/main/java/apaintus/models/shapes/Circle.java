package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.circle.CircleDrawService;

public class Circle extends FillableShape {
    private Circle() {
        super();
    }

    public Circle(ShapeAttributes shapeAttributes) {
        super(ShapeType.CIRCLE, shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new CircleDrawService(this);
    }
}
