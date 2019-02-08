package apaintus.model.snapgrid;

import java.util.ArrayList;
import java.util.Vector;

public class Snapgrid {
	
	private double spacing = 0;
	private double drawingAreaWidth = 0;
	private double drawingAreaHeight = 0;
	
	private boolean isActive;
	
	private ArrayList<Vector<Integer>> snapgridPoints;
	
	public Snapgrid(int spacing, double drawingAreaWidth, double drawingAreaHeight, boolean active){
		this.spacing = spacing;
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapgridPoints = new ArrayList<Vector<Integer>>();
		if(this.isActive){
			//send directive to canvas to draw snapgrid.
		}
		
		computeAllPointInGrid();
	}
	
	private void computeAllPointInGrid() {
		for (int i = 0; i < drawingAreaHeight; i+=spacing) {
			for (int j = 0; j < drawingAreaWidth; j+=spacing) {
				Vector<Integer> gridPoint = new Vector<Integer>(i,j);
				snapgridPoints.add(gridPoint);
			}
		}
	}
	
	public ArrayList<Vector<Integer>> getGridPoints(){
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
}
