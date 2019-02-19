package apaintus.services.draw.selectionBox;

import apaintus.models.shapes.SelectionBox;
import apaintus.services.draw.NodeDrawService;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;

public class SelectionBoxDrawService extends NodeDrawService {
    SelectionBox selectionBox;
    public SelectionBoxDrawService(SelectionBox selectionBox) {
        super(selectionBox);
        this.selectionBox = selectionBox;
    }

    public void draw(GraphicsContext context) {
    	super.draw(context);
    	context.fillOval(selectionBox.getCoordinates().getX(), selectionBox.getCoordinates().getY(), 10, 10);
    	context.fillOval(selectionBox.getCenter().getX(), selectionBox.getCenter().getY(), 10, 10);
    }
}
