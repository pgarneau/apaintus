package apaintus.services.draw.circle;

import apaintus.models.nodes.shapes.Circle;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class CircleDrawService extends ShapeDrawService {
    Circle circle;

    public CircleDrawService(Circle circle) {
        super(circle);
        this.circle = circle;
    }

    @Override
    public void draw(GraphicsContext context) {
        double width = circle.getWidth();
        double height = circle.getHeight();

        super.draw(context);

        context.setFill(Paint.valueOf(circle.getFillColor()));
        context.strokeOval(coordinates.getX(), coordinates.getY(), width, height);
        context.fillOval(coordinates.getX() + strokeSize / 2 - 1, coordinates.getY() + strokeSize / 2 - 1, width - strokeSize + 2, height - strokeSize + 2);
        context.restore();
    }
}
