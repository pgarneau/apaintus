package apaintus.services.draw.selectionBox;

import apaintus.models.nodes.SelectionBox;
import apaintus.services.draw.NodeDrawService;
import javafx.scene.canvas.GraphicsContext;

public class SelectionBoxDrawService extends NodeDrawService {
	SelectionBox selectionBox;

	public SelectionBoxDrawService(SelectionBox selectionBox) {
		super(selectionBox);
		this.selectionBox = selectionBox;
	}

	public void draw(GraphicsContext context) {
		super.draw(context);
	}
}
