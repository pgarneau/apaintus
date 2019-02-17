package apaintus.services.draw;

import apaintus.models.shapes.Shape;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public abstract class ShapeDrawService extends NodeDrawService {
    protected Shape shape;
    protected double strokeSize;

    protected ShapeDrawService(Shape shape) {
        super(shape);
        this.shape = shape;
        strokeSize = this.shape.getStrokeSize();
    }

    public void draw(GraphicsContext context) {
        super.draw(context);

        context.save();
        context.setStroke(Paint.valueOf(shape.getStrokeColor()));
        context.setLineWidth(shape.getStrokeSize());
        context.transform(new Affine(new Rotate(-shape.getOrientation(), shape.getCenter().getX(), shape.getCenter().getY())));
    }
}
