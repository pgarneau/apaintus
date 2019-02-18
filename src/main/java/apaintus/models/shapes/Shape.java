package apaintus.models.shapes;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

@XmlTransient
public abstract class Shape extends Node {
    protected String fillColor;
    protected String strokeColor;
    protected double strokeSize;

    protected Shape() {
        super();
    }

    public Shape(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes);
        fillColor = shapeAttributes.getFillColor();
        strokeColor = shapeAttributes.getStrokeColor();
        strokeSize = shapeAttributes.getStrokeSize();
    }

    @Override
    public void updateBoundingBox() {
        boundingBox.update(
                center,
                width + strokeSize,
                height + strokeSize,
                orientation
        );
    }

    @Override
    public List<Node> getNodeList() {
        return null;
    }

    public String getFillColor() {
        return fillColor;
    }

    public void setFillColor(String fillColor) {
        this.fillColor = fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(String strokeColor) {
        this.strokeColor = strokeColor;
    }

    public double getStrokeSize() {
        return strokeSize;
    }

    public void setStrokeSize(double strokeSize) {
        this.strokeSize = strokeSize;
    }
}
