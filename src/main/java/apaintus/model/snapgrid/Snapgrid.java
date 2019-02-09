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
		this.spacing = spacing * 10;
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapgridPoints = new ArrayList<Point>();		
		computeAllPointInGrid();
	}
	
	private void computeAllPointInGrid() {
		snapgridPoints.clear();
		System.out.println(drawingAreaHeight/spacing);
		System.out.println(drawingAreaWidth/spacing);
		for (int y = (int)spacing; y <= drawingAreaHeight; y+=spacing) {
			for (int x = (int)spacing; x <= drawingAreaWidth; x+=spacing) {
				snapgridPoints.add(new Point(x,y));
				
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
		this.spacing = newValue * 10;
		
		//we need to recompute all the points when the spacing has changed.
		computeAllPointInGrid();
	}

	public double getSpacing() {
		return spacing;
	}
}
