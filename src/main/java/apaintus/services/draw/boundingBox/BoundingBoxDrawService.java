package apaintus.services.draw.boundingBox;

import apaintus.models.Point;
import apaintus.models.shapes.BoundingBox;
import apaintus.models.shapes.Shape;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;

public class BoundingBoxDrawService implements DrawService {
    private static final Color STROKE_COLOR = Color.BLACK;
    private static final int LINE_DASH_SIZE = 4;

    private BoundingBox boundingBox;
    private double[] x;
    private double[] y;

    public BoundingBoxDrawService(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
        Point[] vertices = this.boundingBox.getVertices();
        x = new double[] {vertices[0].getX(), vertices[1].getX(), vertices[2].getX(), vertices[3].getX()};
        y = new double[] {vertices[0].getY(), vertices[1].getY(), vertices[2].getY(), vertices[3].getY()};
    }

    @Override
    public void draw(GraphicsContext context) {
        context.save();
        context.setStroke(STROKE_COLOR);
        context.setLineWidth(BoundingBox.STROKE_SIZE);
        context.setLineDashes(LINE_DASH_SIZE);
        context.strokePolygon(x, y, 4);
        context.restore();
    }
}
