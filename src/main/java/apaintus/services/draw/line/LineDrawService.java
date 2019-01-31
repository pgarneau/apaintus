package apaintus.services.draw.line;

import apaintus.models.shapes.Line;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class LineDrawService extends DrawService {
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
