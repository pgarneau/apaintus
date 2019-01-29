package apaintus.models.shapes;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Shape {
    protected ShapeAttributes shapeAttributes;

    public Shape() {}

    public Shape(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }

    public abstract void update(ShapeAttributes shapeAttributes);

    public ShapeAttributes getShapeAttributes() {
        return shapeAttributes;
    }
}
