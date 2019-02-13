package apaintus.services.draw.selectionBox;

import apaintus.models.shapes.SelectionBox;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;

public class SelectionBoxDrawService extends DrawService {
    public SelectionBoxDrawService(SelectionBox selectionBox) {
        super(selectionBox);
    }

    @Override
    public void draw(GraphicsContext context) {
        return;
    }
}
