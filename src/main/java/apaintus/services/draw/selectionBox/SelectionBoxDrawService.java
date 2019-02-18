package apaintus.services.draw.selectionBox;

import apaintus.models.shapes.SelectionBox;
import apaintus.services.draw.NodeDrawService;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;

public class SelectionBoxDrawService extends NodeDrawService {
    public SelectionBoxDrawService(SelectionBox selectionBox) {
        super(selectionBox);
    }

    public void draw(GraphicsContext context) {
    	super.draw(context);
    }
}
