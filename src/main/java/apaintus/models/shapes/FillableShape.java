package apaintus.models.shapes;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class FillableShape extends DrawableShape {
    protected String fillColor;

    protected FillableShape() {
        super();
    }

    protected FillableShape(ShapeType shapeType, ShapeAttributes shapeAttributes) {
        super(shapeType, shapeAttributes);
        fillColor = shapeAttributes.getFillColor();
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
        fillColor = shapeAttributes.getFillColor();
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    @Override
    public ShapeAttributes getShapeAttributes() {
        return ShapeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .withStrokeColor(strokeColor)
                .withStrokeSize(strokeSize)
                .withFillColor(fillColor)
                .withText(text)
                .build();
    }
}
