package apaintus.services.draw.textbox;

import apaintus.models.nodes.shapes.TextBox;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TextBoxDrawService extends ShapeDrawService {
    private TextBox textbox;

    public TextBoxDrawService(TextBox textbox) {
        super(textbox);
        this.textbox = textbox;
    }

    public void draw(GraphicsContext context) {
        super.draw(context);
        
        double fontSize = textbox.getStrokeSize();

        context.setFill(Paint.valueOf(textbox.getStrokeColor()));
        context.setFont(Font.font ("Verdana", fontSize));
        context.fillText(textbox.getText(), coordinates.getX(), coordinates.getY() + fontSize, textbox.getWidth());
        context.restore();
    }
}
