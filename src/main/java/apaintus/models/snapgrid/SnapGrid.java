package apaintus.models.snapgrid;

import apaintus.models.Point;

import java.util.ArrayList;

public class SnapGrid {
	private double spacing = 10;
	private double drawingAreaWidth;
	private double drawingAreaHeight;
	
	private boolean isActive;
	
	private ArrayList<Point> snapgridPoints= new ArrayList<>();
	
	public SnapGrid(double drawingAreaWidth, double drawingAreaHeight, boolean active){
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
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

	public void setSpacing(Double newValue) {
		this.spacing = newValue;
		
		//we need to recompute all the points when the spacing has changed.
		computeAllPointInGrid();
	}
	
	public ArrayList<Point> getSnapGridPoints() {
		return snapGridPoints;
	}
}
	
