package apaintus.models.xml;

import apaintus.models.shapes.Shape;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement
//@XmlSeeAlso({Rectangle.class, Circle.class, Line.class, Heart.class})
public class XmlContract {
    private List<Shape> shapeList;

    private XmlContract() {}

    public XmlContract(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<Shape> shapeList) {
        this.shapeList = shapeList;
    }
}
