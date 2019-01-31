package apaintus.services.draw;

import apaintus.models.Point;
import apaintus.models.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class DrawService {
    protected Shape shape;
    protected Point coordinates;
    protected double strokeSize;

    protected DrawService(Shape shape) {
        this.shape = shape;
        this.coordinates = shape.getCoordinates();
        this.strokeSize = shape.getStrokeSize();
    }

    public void draw(GraphicsContext context) {
        context.save();
        context.setStroke(Paint.valueOf(shape.getStrokeColor()));
        context.setLineWidth(strokeSize);
        context.transform(new Affine(new Rotate(-shape.getOrientation(), coordinates.getX(), coordinates.getY())));
    }
}
