package apaintus.services.draw;

import apaintus.models.Point;
import apaintus.models.nodes.Node;
import apaintus.services.draw.boundingBox.BoundingBoxDrawService;
import javafx.scene.canvas.GraphicsContext;

public abstract class NodeDrawService implements DrawService {
    protected Node node;
    protected Point coordinates;

    protected NodeDrawService(Node node) {
        this.node = node;
        coordinates = this.node.getCoordinates();
    }

    @Override
    public void draw(GraphicsContext context) {
        if (node.isSelected()) {
            DrawService drawService = new BoundingBoxDrawService(node.getBoundingBox());
            drawService.draw(context);
        }
    }
}
