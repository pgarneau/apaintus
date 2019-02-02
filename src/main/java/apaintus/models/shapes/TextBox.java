package apaintus.models.shapes;

import apaintus.services.draw.textbox.TextBoxDrawService;

import static apaintus.models.shapes.ShapeType.TEXTBOX;

public class TextBox extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;

    public TextBox(ShapeAttributes shapeAttributes) {
        super(TEXTBOX, shapeAttributes);

        width = shapeAttributes.getWidth();
        height = shapeAttributes.getHeight();
        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        width = shapeAttributes.getWidth();
        height = shapeAttributes.getHeight();
        fillColor = shapeAttributes.getFillColor();
        boundingBox.update(shapeAttributes);
    }

    @Override
    public TextBoxDrawService getDrawService() {
        return new TextBoxDrawService(this);
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }
}
