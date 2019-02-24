package apaintus.models.snapgrid;

import apaintus.models.Point;

import java.util.ArrayList;

public class SnapGrid {
    private double gradation = 10;
    private double drawingAreaWidth;
    private double drawingAreaHeight;

    private boolean isActive;

    private ArrayList<Point> snapGridPoints = new ArrayList<>();

    public SnapGrid(double drawingAreaWidth, double drawingAreaHeight) {
        this.drawingAreaWidth = drawingAreaWidth;
        this.drawingAreaHeight = drawingAreaHeight;
        isActive = false;
    }

    private void computeAllPointInGrid() {
        snapGridPoints.clear();
        for (int y = 0; y <= drawingAreaHeight; y += (gradation)) {
            for (int x = 0; x <= drawingAreaWidth; x += (gradation)) {
                snapGridPoints.add(new Point(x, y));
            }
        }
    }

    public double getGradation() {
        return gradation;
    }

    public void setGradation(double size) {
        this.gradation = size;
        computeAllPointInGrid();
    }

    public boolean isActive() {
        return isActive;
    }

    public void toggle() {
        isActive = !isActive;
    }

    public ArrayList<Point> getSnapGridPoints() {
        return snapGridPoints;
    }
}
	
