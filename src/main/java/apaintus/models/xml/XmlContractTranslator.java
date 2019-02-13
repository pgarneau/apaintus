package apaintus.models.xml;

import apaintus.models.shapes.DrawableShape;

import java.util.List;

public class XmlContractTranslator {
    private XmlContractTranslator() {}

    public static XmlContract translateTo(List<DrawableShape> shapes) {
        return new XmlContract(shapes);
    }

    public static List<DrawableShape> translateFrom(XmlContract xmlContract) {
        return xmlContract.getShapeList();
    }
}
