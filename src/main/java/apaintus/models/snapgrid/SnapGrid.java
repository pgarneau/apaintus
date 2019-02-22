package apaintus.models.snapgrid;

import apaintus.models.Point;

import java.util.ArrayList;

public class SnapGrid {
	private double gradation = 10;
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
		for (int y = 0; y <= drawingAreaHeight; y+=(gradation)) {
			for (int x = 0; x <= drawingAreaWidth; x+=(gradation)) {
				snapgridPoints.add(new Point(x,y));
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

	public void setGradation(Double newValue) {
		this.gradation = newValue;
		computeAllPointInGrid();
	}

	public double getGradation() {
		return gradation;
	}
	
	
	public ArrayList<Point> getSnapGridPoints() {
		return snapGridPoints;
	}
}
	
