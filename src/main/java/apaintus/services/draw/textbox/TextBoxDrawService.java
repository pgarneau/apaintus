package apaintus.services.draw.textbox;

import apaintus.models.shapes.TextBox;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class TextBoxDrawService extends DrawService {
    private TextBox textbox;

    public TextBoxDrawService(TextBox textbox) {
        super(textbox);
        this.textbox = textbox;
    }

    public void draw(GraphicsContext context) {
        super.draw(context);
        
        double fontSize = textbox.getStrokeSize();

        if (textbox.getFillColor().compareTo("0x00000000") == 0)
            textbox.setFillColor("0x000000ff");
        
        context.setFill(Paint.valueOf(textbox.getFillColor()));
        context.setFont(Font.font ("Verdana", fontSize));
        context.fillText(textbox.getText(), coordinates.getX(), coordinates.getY() + fontSize, textbox.getWidth());
        context.restore();
    }
}
