package apaintus.models.xml;

import apaintus.models.shapes.Node;

import java.util.List;

public class XmlContractTranslator {
    private XmlContractTranslator() {}

    public static XmlContract translateTo(List<Node> nodeList) {
        return new XmlContract(nodeList);
    }

    public static List<Node> translateFrom(XmlContract xmlContract) {
        return xmlContract.getNodeList();
    }
}
