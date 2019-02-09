package apaintus.models.shapes;

import apaintus.services.draw.textbox.TextBoxDrawService;

import static apaintus.models.shapes.ShapeType.TEXT_BOX;

public class TextBox extends DrawableShape {
    private double width;
    private double height;
    private String fillColor;
    private String text;

    private TextBox() {
        super();
    }

    public TextBox(ShapeAttributes shapeAttributes) {
        super(TEXT_BOX, shapeAttributes);

        text = new String("Enter text down below");
        width = shapeAttributes.getWidth();
        height = shapeAttributes.getHeight();
        fillColor = shapeAttributes.getFillColor();
        boundingBox = new BoundingBox(shapeAttributes);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);

        if (shapeAttributes.getText() != null )
        	text = shapeAttributes.getText();
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
    
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
