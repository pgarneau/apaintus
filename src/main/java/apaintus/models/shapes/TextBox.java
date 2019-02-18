package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.textbox.TextBoxDrawService;

public class TextBox extends Shape {
    private static final String DEFAULT_TEXT = "Enter text";
    private String text;

    private TextBox() {
        super();
    }

    public TextBox(NodeAttributes nodeAttributes, ShapeAttributes shapeAttributes) {
        super(nodeAttributes, shapeAttributes);
        nodeType = NodeType.RECTANGLE;
        this.text = DEFAULT_TEXT;
    }

    @Override
    public TextBoxDrawService getDrawService() {
        return new TextBoxDrawService(this);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
