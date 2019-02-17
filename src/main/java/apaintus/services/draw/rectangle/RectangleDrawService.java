package apaintus.services.draw.rectangle;

import apaintus.models.shapes.Rectangle;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class RectangleDrawService extends ShapeDrawService {
    private Rectangle rectangle;

    public RectangleDrawService(Rectangle rectangle) {
        super(rectangle);
        this.rectangle = rectangle;
    }

    public void draw(GraphicsContext context) {
        double width = rectangle.getWidth();
        double height = rectangle.getHeight();

        super.draw(context);

        context.setFill(Paint.valueOf(rectangle.getFillColor()));
        context.strokeRect(coordinates.getX(), coordinates.getY(), width, height);
        context.fillRect(coordinates.getX() + strokeSize / 2 - 1, coordinates.getY() + strokeSize / 2 - 1, width - strokeSize + 2, height - strokeSize + 2);
        context.restore();
    }
}
