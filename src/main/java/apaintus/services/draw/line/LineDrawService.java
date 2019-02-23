package apaintus.services.draw.line;

import apaintus.models.nodes.shapes.Line;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class LineDrawService extends ShapeDrawService {
    Line line;

    public LineDrawService(Line line) {
        super(line);
        this.line = line;
    }

    @Override
    public void draw(GraphicsContext context) {
        coordinates = line.getCoordinates();

        super.draw(context);

        context.setFill(Paint.valueOf(line.getStrokeColor()));
        context.beginPath();
        context.fillRect(coordinates.getX(), coordinates.getY(), line.getWidth(), line.getStrokeSize());
        context.closePath();
        context.restore();
    }
}
