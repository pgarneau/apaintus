package apaintus.models.shapes;

import javax.xml.bind.annotation.XmlTransient;

@XmlTransient
public abstract class Shape {
    protected ShapeAttributes shapeAttributes;

    public Shape() {}

    public void update(ShapeAttributes shapeAttributes) {
        this.shapeAttributes = shapeAttributes;
    }

    public ShapeAttributes getShapeAttributes() {
        return shapeAttributes;
    }
}
