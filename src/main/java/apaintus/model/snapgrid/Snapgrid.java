package apaintus.model.snapgrid;

import apaintus.models.Point;
import java.util.ArrayList;

public class Snapgrid {
	
	private double spacing = 0;
	private double drawingAreaWidth = 0;
	private double drawingAreaHeight = 0;
	
	private boolean isActive;
	
	private ArrayList<Point> snapgridPoints;
	
	public Snapgrid(double spacing, double drawingAreaWidth, double drawingAreaHeight, boolean active){
		this.spacing = spacing;
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapgridPoints = new ArrayList<Point>();
		if(this.isActive){
			//send directive to canvas to draw snapgrid.
		}
		
		computeAllPointInGrid();
	}
	
	private void computeAllPointInGrid() {
		for (int i = 0; i < drawingAreaHeight; i+=spacing) {
			for (int j = 0; j < drawingAreaWidth; j+=spacing) {
				snapgridPoints.add(new Point(i,j));
			}
		}
	}
	
	public ArrayList<Point> getGridPoints(){
		return snapgridPoints;
	}
	
	public boolean isActive() {
		return this.isActive;
	}

	public void setActive(boolean active) {
		this.isActive = active;
	}

	public void setSpacing(Double newValue) {
		this.spacing = newValue;
		
		//we need to recompute all the points when the spacing has changed.
		computeAllPointInGrid();
	}

	public double getSpacing() {
		return spacing;
	}
}
