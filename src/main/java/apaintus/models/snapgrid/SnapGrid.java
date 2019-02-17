package apaintus.models.snapgrid;

import apaintus.models.Point;
import java.util.ArrayList;
import java.util.List;

public class SnapGrid {
	private double size = 0;
	private double drawingAreaWidth = 0;
	private double drawingAreaHeight = 0;

	private boolean isActive;

	private ArrayList<Point> snapGridPoints;

	public SnapGrid(double size, double drawingAreaWidth, double drawingAreaHeight, boolean active) {
		this.size = size * 10;
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapGridPoints = new ArrayList<>();
		computeAllPointInGrid();
	}

	private void computeAllPointInGrid() {
		snapGridPoints.clear();
		for (int y = 0; y <= drawingAreaHeight; y += (size)) {
			for (int x = 0; x <= drawingAreaWidth; x += (size)) {
				snapGridPoints.add(new Point(x, y));
			}
		}
	}

	public double getSize() {
		return size;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean active) {
		isActive = active;
	}

	public ArrayList<Point> getSnapGridPoints() {
		return snapGridPoints;
	}
}
	
