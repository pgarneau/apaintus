package apaintus.models.shapes;

import apaintus.services.draw.smiley.SmileyDrawService;

public class Smiley extends FillableShape {
    private Smiley() {
        super();
    }

    public Smiley(ShapeAttributes shapeAttributes) {
        super(ShapeType.SMILEY, shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
    }

    @Override
    public SmileyDrawService getDrawService() {
        return new SmileyDrawService(this);
    }
}
