package apaintus.models.xml;

import apaintus.models.shapes.Shape;

import java.util.List;

public class XmlContractTranslator {
    public static XmlContract translateTo(List<Shape> shapes) {
        return new XmlContract(shapes);
    }

    public static List<Shape> translateFrom(XmlContract xmlContract) {
        return xmlContract.getShapeList();
    }
}
