package apaintus.models.nodes.util;

import apaintus.models.Point;
import apaintus.models.snapgrid.SnapGrid;

import java.util.List;

public class FactoryUtil {
	public static Point computeNodeCoordinates(Point mousePosition, Point lastMouseClickPosition) {
		Point coordinates;

		boolean upMotion = mousePosition.getY() - lastMouseClickPosition.getY() <= 0.0;
		boolean rightMotion = mousePosition.getX() - lastMouseClickPosition.getX() > 0.0;

		if (upMotion && !rightMotion) {
			coordinates = mousePosition;
		} else if (upMotion && rightMotion) {
			coordinates = new Point(lastMouseClickPosition.getX(), mousePosition.getY());
		} else if (rightMotion) {
			coordinates = lastMouseClickPosition;
		} else {
			coordinates = new Point(mousePosition.getX(), lastMouseClickPosition.getY());
		}

		return coordinates;
	}

	public static Point computeShapeCoordinates(Point mousePosition, Point lastMouseClickPosition, double strokeSize) {
		Point coordinates = computeNodeCoordinates(mousePosition, lastMouseClickPosition);

		if (coordinates.getX() == 0)
			coordinates.setX(strokeSize);

		if (coordinates.getY() == 0)
			coordinates.setY(strokeSize);

		return coordinates;
	}

	public static double[] computeDimensions(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, double strokeSize) {
		// Width is index 0
		// Height is index 1
		double[] dimension = new double[]{Math.abs(mousePosition.getX() - lastMouseClickPosition.getX()),
				Math.abs(mousePosition.getY() - lastMouseClickPosition.getY())};

		if (mousePosition.getX() >= canvasDimensions[0])
			dimension[0] = Math.abs(canvasDimensions[0] - lastMouseClickPosition.getX() - strokeSize);
		if (mousePosition.getY() >= canvasDimensions[1])
			dimension[1] = Math.abs(canvasDimensions[1] - lastMouseClickPosition.getY() - strokeSize);

		return dimension;
	}

	public static double computeLineWidth(Point mousePosition, Point lastMouseClickPosition, double strokeSize) {
		double deltaX = mousePosition.getX() - lastMouseClickPosition.getX();
		double deltaY = mousePosition.getY() - lastMouseClickPosition.getY();

		return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) + strokeSize / 2;
	}

	public static double computeOrientation(Point mousePosition, Point lastMouseClickPosition) {
		double orientation = Math.toDegrees(Math.atan2(lastMouseClickPosition.getY() - mousePosition.getY(),
				mousePosition.getX() - lastMouseClickPosition.getX()));

		if (orientation < 0.0) {
			orientation += 360.0;
		}

		return orientation;
	}

	public static Point computeNearestPoint(Point mousePosition, SnapGrid snapGrid) {
		Point newPoint = new Point(0, 0);
		double distance = Double.MAX_VALUE;

		for (Point point : snapGrid.getSnapGridPoints()) {
			double x = (Math.abs(point.getX() - mousePosition.getX()));
			double y = (Math.abs(point.getY() - mousePosition.getY()));
			double currentDistance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

			if (distance > currentDistance) {
				distance = currentDistance;
				newPoint = point;
			}
		}

		return newPoint;
	}

	public static Point[] getSnapGridMousePositions(Point mousePosition, Point lastMouseClickPosition, SnapGrid snapGrid) {
		Point newMousePosition;
		Point newLastMousePosition;

		newMousePosition = computeNearestPoint(mousePosition, snapGrid);
		newLastMousePosition = computeNearestPoint(lastMouseClickPosition, snapGrid);

		if (newMousePosition == newLastMousePosition) {
			newMousePosition = getToNextPointInGrid(newMousePosition, snapGrid.getSnapGridPoints());
		}

		return new Point[]{newMousePosition, newLastMousePosition};
	}

	public static Point getToNextPointInGrid(Point lastPosition, List<Point> gridPoints) {
		Point newPoint = new Point(0, 0);

		for (int i = 0; i < gridPoints.size(); i++) {
			Point pt = gridPoints.get(i);

			if (pt.getX() == lastPosition.getX() && pt.getY() == lastPosition.getY())
				newPoint = gridPoints.get(i + 1);
		}

		return newPoint;
	}
}
