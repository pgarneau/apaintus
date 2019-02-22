package apaintus.services.update;

import apaintus.models.Point;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.SelectionBox;
import apaintus.models.nodes.util.FactoryUtil;
import apaintus.models.snapgrid.SnapGrid;

public class SelectionBoxUpdateService implements UpdateService {
	private SelectionBox selectionBox;
	private double[] dimensions = new double[]{0, 0};
	private Point coordinates;

	public SelectionBoxUpdateService(SelectionBox selectionBox) {
		this.selectionBox = selectionBox;
	}

	@Override
	public void update(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid) {
		if (snapGrid.isActive()) {
			Point[] snapGridMousePositions = FactoryUtil.getSnapGridMousePositions(mousePosition, lastMouseClickPosition, snapGrid);
			mousePosition = snapGridMousePositions[0];
			lastMouseClickPosition = snapGridMousePositions[1];
		}

		dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, 0);
		coordinates = FactoryUtil.computeNodeCoordinates(mousePosition, lastMouseClickPosition);

		selectionBox.update(
				NodeAttributes
						.builder()
						.withCoordinates(coordinates)
						.withWidth(dimensions[0])
						.withHeight(dimensions[1])
						.build()
		);
	}
}
