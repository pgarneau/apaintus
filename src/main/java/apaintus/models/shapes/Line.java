package apaintus.models.shapes;

import apaintus.services.draw.DrawService;
import apaintus.services.draw.line.LineDrawService;

public class Line extends DrawableShape {
    private Line() {
        super();
    }

    public Line(ShapeAttributes shapeAttributes) {
        super(ShapeType.LINE, shapeAttributes);
    }

    @Override
    public DrawService getDrawService() {
        return new LineDrawService(this);
    }
}
