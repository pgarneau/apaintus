package apaintus.models.snapgrid;

import apaintus.models.Point;
import java.util.ArrayList;
import java.util.List;

public class SnapGrid {
	private double spacing = 0;
	private double drawingAreaWidth = 0;
	private double drawingAreaHeight = 0;
	
	private boolean isActive;
	
	private ArrayList<Point> snapgridPoints;
	
	public SnapGrid(double spacing, double drawingAreaWidth, double drawingAreaHeight, boolean active){

		if (spacing <= 0)
			throw new IllegalArgumentException("Trying to make a grid with a linespacing equal to 0");
		
		this.spacing = spacing * 10;
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapgridPoints = new ArrayList<>();
		computeAllPointInGrid();
	}
	
	private void computeAllPointInGrid() {
		snapgridPoints.clear();
		for (int y = 0; y <= drawingAreaHeight; y+=(spacing)) {
			for (int x = 0; x <= drawingAreaWidth; x+=(spacing)) {
				snapgridPoints.add(new Point(x,y));
			}
		}
	}
	
	public List<Point> getGridPoints(){
		return snapgridPoints;
	}
	
	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public void setSpacing(Double newValue) {
		this.spacing = newValue * 10;
		
		//we need to recompute all the points when the spacing has changed.
		computeAllPointInGrid();
	}

	public double getSpacing() {
		return spacing;
	}
}
