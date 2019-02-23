package apaintus.models.nodes.shapes;

import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.smiley.SmileyDrawService;
import apaintus.services.update.SmileyUpdateService;
import apaintus.services.update.UpdateService;

public class Smiley extends Shape {
    private Smiley() {
        super();
    }

    public Smiley(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.SMILEY;
    }

    @Override
    public DrawService getDrawService() {
        return new SmileyDrawService(this);
    }

    @Override
    public UpdateService getUpdateService() {
        return new SmileyUpdateService(this);
    }
}
