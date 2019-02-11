package apaintus.services.draw.selection_box;

import apaintus.models.shapes.SelectionBox;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;

public class SelectionBoxDrawService extends DrawService {
    private SelectionBox selectionBox;

    public SelectionBoxDrawService(SelectionBox selectionBox) {
        super(selectionBox);
        this.selectionBox = selectionBox;
    }

    @Override
    public void draw(GraphicsContext context) {
        return;
    }
}
