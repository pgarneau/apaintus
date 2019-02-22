package apaintus.models.nodes.shapes;

import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.services.draw.textbox.TextBoxDrawService;
import apaintus.services.update.TextBoxUpdateService;
import apaintus.services.update.UpdateService;

public class TextBox extends Shape {
    private static final String DEFAULT_TEXT = "Enter text";
    private String text;

    private TextBox() {
        super();
    }

    public TextBox(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.TEXT_BOX;
        this.text = DEFAULT_TEXT;
    }

    @Override
    public TextBoxDrawService getDrawService() {
        return new TextBoxDrawService(this);
    }

    @Override
    public UpdateService getUpdateService() {
        return new TextBoxUpdateService(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
