package apaintus.services.draw.textbox;

import apaintus.models.shapes.TextBox;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class TextBoxDrawService extends DrawService {
    private TextBox textbox;

    public TextBoxDrawService(TextBox textbox) {
        super(textbox);
        this.textbox = textbox;
    }

    public void draw(GraphicsContext context) {
        double width = textbox.getWidth();
        double height = textbox.getHeight();

        super.draw(context);

        context.setFill(Paint.valueOf(textbox.getFillColor()));
        context.strokeRect(coordinates.getX(), coordinates.getY(), width, height);
        context.fillRect(coordinates.getX() + strokeSize / 2 - 1, coordinates.getY() + strokeSize / 2 - 1, width - strokeSize + 2, height - strokeSize + 2);
        context.restore();
    }
}
