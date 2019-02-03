package apaintus.models.shapes;

import apaintus.services.draw.smiley.SmileyDrawService;
import apaintus.services.update.UpdateService;
import apaintus.services.update.smiley.SmileyUpdateService;

public class Smiley extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;

    public Smiley(ShapeAttributes shapeAttributes) {
        super(ShapeType.SMILEY, shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        fillColor = shapeAttributes.getFillColor();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public SmileyDrawService getDrawService() {
        return new SmileyDrawService(this);
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public UpdateService getUpdateService() {
        return new SmileyUpdateService(this);
    }
}
