package apaintus.services.update;

import apaintus.models.Point;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.shapes.Line;
import apaintus.models.nodes.util.FactoryUtil;
import apaintus.models.snapgrid.SnapGrid;

public class LineUpdateService extends ShapeUpdateService {
	private Line line;
	private double orientation;

	public LineUpdateService(Line line) {
		super(line);
		this.line = line;
	}

	@Override
	public void update(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid) {
		orientation = FactoryUtil.computeOrientation(mousePosition, lastMouseClickPosition);
		dimensions[0] = FactoryUtil.computeLineWidth(mousePosition, lastMouseClickPosition, line.getStrokeSize());

		adjustMousePosition(mousePosition, lastMouseClickPosition, snapGrid);

		line.update(
				NodeAttributes
						.builder()
						.withCoordinates(lastMouseClickPosition)
						.withWidth(dimensions[0])
						.withHeight(line.getStrokeSize())
						.withOrientation(orientation)
						.build()
		);
	}
}
