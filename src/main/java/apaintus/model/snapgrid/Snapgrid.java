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
		try {
			if (spacing <= 0)
				throw new ArithmeticException();
			this.spacing = spacing * 10;
		} catch (Exception e) {
			System.out.println("Trying to make a grid with a linespacing equal to 0");
			System.out.println("This probably mean you are trying to pass a value from the wrong place.");
			return;
		}
		this.drawingAreaWidth = drawingAreaWidth;
		this.drawingAreaHeight = drawingAreaHeight;
		this.isActive = active;
		this.snapgridPoints = new ArrayList<Point>();		
		computeAllPointInGrid();
	}
	
	private void computeAllPointInGrid() {
		snapgridPoints.clear();
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

	public Point computeNearestPoint(Point mousePosition) {
		Point newPoint = new Point(0, 0);
		double distance = Double.MAX_VALUE;
		for (Point pt : getGridPoints()) {
			double x = (Math.abs(pt.getX()-mousePosition.getX()));
			double y = (Math.abs(pt.getY()-mousePosition.getY()));
			double currentDistance = Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
			if(distance > currentDistance) {
				distance = currentDistance;
				newPoint = pt;
			}
		}
		return newPoint;
	}
}
