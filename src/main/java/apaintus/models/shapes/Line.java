package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.line.LineDrawService;

public class Line extends DrawableShape {
    public Line(ShapeAttributes shapeAttributes) {
        super(ShapeType.LINE, shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new LineDrawService(this);
    }
}
