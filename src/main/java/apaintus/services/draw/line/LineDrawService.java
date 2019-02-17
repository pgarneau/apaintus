package apaintus.services.draw.line;

import apaintus.models.shapes.Line;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.ShapeDrawService;
import apaintus.services.draw.boundingBox.BoundingBoxDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class LineDrawService extends ShapeDrawService {
    Line line;

    public LineDrawService(Line line) {
        super(line);
        this.line = line;
    }

    @Override
    public void draw(GraphicsContext context) {
        coordinates = line.getCoordinates();

        if (node.isSelected()) {
            DrawService drawService = new BoundingBoxDrawService(node.getBoundingBox());
            drawService.draw(context);
        }

        context.save();
        context.setStroke(Paint.valueOf(shape.getStrokeColor()));
        context.setLineWidth(shape.getStrokeSize());
        context.transform(new Affine(new Rotate(-shape.getOrientation(), shape.getCoordinates().getX(), shape.getCoordinates().getY())));

        context.setFill(Paint.valueOf(line.getStrokeColor()));
        context.beginPath();
        context.fillRect(coordinates.getX(), coordinates.getY(), line.getWidth(), line.getStrokeSize());
        context.closePath();
        context.restore();
    }
}
