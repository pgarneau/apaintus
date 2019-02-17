package apaintus.models.xml;

import apaintus.models.Point;
import apaintus.models.shapes.*;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.util.List;

@XmlRootElement
@XmlSeeAlso({Rectangle.class, Circle.class, Line.class, Smiley.class, TextBox.class, Point.class})
public class XmlContract {
    private List<Node> nodeList;

    private XmlContract() {}

    public XmlContract(List<Node> nodeList) {
        this.nodeList = nodeList;
    }

    public List<Node> getNodeList() {
        return nodeList;
    }

    public void setNodeList(List<Node> nodeList) {
        this.nodeList = nodeList;
    }
}
