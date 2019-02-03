package apaintus.models.xml;

import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.Shape;

import java.util.List;

public class XmlContractTranslator {
    public static XmlContract translateTo(List<DrawableShape> shapes) {
        return new XmlContract(shapes);
    }

    public static List<DrawableShape> translateFrom(XmlContract xmlContract) {
        return xmlContract.getShapeList();
    }
}
