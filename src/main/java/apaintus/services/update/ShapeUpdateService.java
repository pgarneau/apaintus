package apaintus.services.update;

import apaintus.models.Point;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.shapes.Shape;
import apaintus.models.nodes.util.FactoryUtil;
import apaintus.models.snapgrid.SnapGrid;


public abstract class ShapeUpdateService implements UpdateService {
	protected Shape shape;
	protected Point mousePosition;
	protected Point lastMouseClickPosition;
	protected Point coordinates;
	protected double[] dimensions = new double[]{0, 0};

	protected ShapeUpdateService(Shape shape) {
		this.shape = shape;
	}

	public void update(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid) {
		adjustMousePosition(mousePosition, lastMouseClickPosition, snapGrid);

		dimensions = FactoryUtil.computeDimensions(this.mousePosition, this.lastMouseClickPosition, canvasDimensions, shape.getStrokeSize());
		coordinates = FactoryUtil.computeShapeCoordinates(this.mousePosition, this.lastMouseClickPosition, shape.getStrokeSize());

		shape.update(
				NodeAttributes
						.builder()
						.withCoordinates(coordinates)
						.withWidth(dimensions[0])
						.withHeight(dimensions[1])
						.build()
		);
	}

	protected void adjustMousePosition(Point mousePosition, Point lastMouseClickPosition, SnapGrid snapGrid) {
		if (snapGrid.isActive()) {
			Point[] snapGridMousePositions = FactoryUtil.getSnapGridMousePositions(mousePosition, lastMouseClickPosition, snapGrid);
			this.mousePosition = snapGridMousePositions[0];
			this.lastMouseClickPosition = snapGridMousePositions[1];
		} else {
			this.mousePosition = mousePosition;
			this.lastMouseClickPosition = lastMouseClickPosition;
		}
	}
}
