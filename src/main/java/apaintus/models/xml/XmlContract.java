package apaintus.models.xml;

import apaintus.models.Point;
import apaintus.models.shapes.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Rectangle.class, Circle.class, Line.class, Smiley.class, TextBox.class, Point.class})
public class XmlContract {
    private List<DrawableShape> shapeList;

    private XmlContract() {}

    public XmlContract(List<DrawableShape> shapeList) {
        this.shapeList = shapeList;
    }

    public List<DrawableShape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(List<DrawableShape> shapeList) {
        this.shapeList = shapeList;
    }
}
