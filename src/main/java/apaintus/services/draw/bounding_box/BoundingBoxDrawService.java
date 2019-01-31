package apaintus.services.draw.bounding_box;

import apaintus.models.shapes.BoundingBox;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;

public class BoundingBoxDrawService extends DrawService {
    BoundingBox boundingBox;

    public BoundingBoxDrawService(BoundingBox boundingBox) {
        super(boundingBox);
        this.boundingBox = boundingBox;
    }

    @Override
    public void draw(GraphicsContext context) {
        double width = boundingBox.getWidth();
        double height = boundingBox.getHeight();
        double shapeStrokeSize = boundingBox.getShapeStrokeSize();

        super.draw(context);

        context.setLineDashes(boundingBox.getLineDashSize());
        context.strokeRect(coordinates.getX() - shapeStrokeSize / 2 - strokeSize / 2, coordinates.getY() - shapeStrokeSize / 2 - strokeSize / 2, width + shapeStrokeSize + strokeSize, height + shapeStrokeSize + strokeSize);
        context.restore();
    }
}
