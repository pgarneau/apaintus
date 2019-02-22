package apaintus.services.update;

import apaintus.models.Point;
import apaintus.models.snapgrid.SnapGrid;

public interface UpdateService {
	void update(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid);
}
