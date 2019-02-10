package apaintus.models.shapes;

import java.util.ArrayList;

import apaintus.model.snapgrid.Snapgrid;
import apaintus.models.Point;

public class ShapeFactory {
    private static double[] dimensions;
    private static Point coordinates;
    private static double orientation;
    private static double width;

    public static DrawableShape createShape(ShapeType shapeType, Point mousePosition, Point lastMouseClickPosition, String fillColor, String strokeColor, double strokeSize,double canvasWidth, double canvasHeight) {
        switch (shapeType) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                return new Rectangle(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );

            case CIRCLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                return new Circle(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );

            case LINE:
                orientation = computeOrientation(mousePosition, lastMouseClickPosition);
                width = computeLineWidth(mousePosition, lastMouseClickPosition, strokeSize);
                return new Line(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(lastMouseClickPosition)
                                .withDestinationCoordinates(mousePosition)
                                .withWidth(width)
                                .withHeight(strokeSize)
                                .withOrientation(orientation)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );

            case SMILEY:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                return new Smiley(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                
            case TEXT_BOX:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasHeight, canvasHeight, canvasHeight);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                return new TextBox(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );


            default:
                return null;
        }
    }

    public static void updateShape(Shape shape, Point mousePosition, Point lastMouseClickPosition, String fillColor, String strokeColor, double strokeSize,Snapgrid snapgrid, double canvasWidth, double canvasHeight) {
    	Point[] positions = new Point[2];
    	switch (shape.getShapeType()) {
            case RECTANGLE:
            	positions = ajustPointWithSnapgrid(snapgrid,mousePosition,lastMouseClickPosition,strokeSize);
            	mousePosition = positions[0];
            	lastMouseClickPosition = positions[1];
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
           
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;

            case CIRCLE:
            	positions = ajustPointWithSnapgrid(snapgrid,mousePosition,lastMouseClickPosition,strokeSize);
            	mousePosition = positions[0];
            	lastMouseClickPosition = positions[1];
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;

            case LINE:
            	positions = ajustPointWithSnapgrid(snapgrid,mousePosition,lastMouseClickPosition,strokeSize);
            	mousePosition = positions[0];
            	lastMouseClickPosition = positions[1];
                orientation = computeOrientation(mousePosition, lastMouseClickPosition);
                width = computeLineWidth(mousePosition, lastMouseClickPosition, strokeSize);
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(lastMouseClickPosition)
                                .withDestinationCoordinates(mousePosition)
                                .withWidth(width)
                                .withHeight(strokeSize)
                                .withOrientation(orientation)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;

            case SMILEY:
            	positions = ajustPointWithSnapgrid(snapgrid,mousePosition,lastMouseClickPosition,strokeSize);
            	mousePosition = positions[0];
            	lastMouseClickPosition = positions[1];
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;
                
            case TEXT_BOX:
            	positions = ajustPointWithSnapgrid(snapgrid,mousePosition,lastMouseClickPosition,strokeSize);
            	mousePosition = positions[0];
            	lastMouseClickPosition = positions[1];
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasWidth, canvasHeight, strokeSize);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition,strokeSize);
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;

        }
    }

    private static Point getToNextPointInGrid(Point lastPos, ArrayList<Point> gridPts) {
		Point newPoint = new Point(0,0);
		for(int i = 0; i<gridPts.size();i++) {
			Point pt = gridPts.get(i);
			if(pt.getX() == lastPos.getX() && pt.getY() == lastPos.getY())
				newPoint = gridPts.get(i+1);
		}
		return newPoint;
	}

	private static Point computeCoordinates(Point mousePosition, Point lastMouseClickPosition,double strokeSize) {
        Point coordinates;

        boolean upMotion = mousePosition.getY() - lastMouseClickPosition.getY() <= 0.0;
        boolean rightMotion = mousePosition.getX() - lastMouseClickPosition.getX() > 0.0;

        if (upMotion && !rightMotion) {
            coordinates = mousePosition;
        } else if (upMotion && rightMotion) {
            coordinates = new Point(lastMouseClickPosition.getX(), mousePosition.getY());
        } else if (!upMotion && rightMotion) {
            coordinates = lastMouseClickPosition;
        } else if (!upMotion && !rightMotion) {
            coordinates = new Point(mousePosition.getX(), lastMouseClickPosition.getY());
        } else {
            System.err.print(
                    "this should not happen in any case. This probably means that the lastMouseClickPosition is null");
            coordinates = new Point(0, 0);
        }
        
        if(coordinates.getX() == 0)
        	coordinates.setX(strokeSize/1);
		if(coordinates.getY() == 0)
			coordinates.setY(strokeSize/1);
        return coordinates;
    }

    private static double[] computeDimensions(Point mousePosition, Point lastMouseClickPosition, double canvasWidth, double canvasHeight,double strokeSize) {
        // Width is index 0
        // Height is index 1
        double[] dimension = new double[] {Math.abs(mousePosition.getX() - lastMouseClickPosition.getX()),
                Math.abs(mousePosition.getY() - lastMouseClickPosition.getY())};
        
        if(mousePosition.getX() >= canvasWidth)
        	dimension[0] = Math.abs(canvasWidth - lastMouseClickPosition.getX() - strokeSize);
        if(mousePosition.getY() >= canvasHeight)
        	dimension[1] = Math.abs(canvasHeight - lastMouseClickPosition.getY() - strokeSize);
        
        return dimension;
    }

    private static double computeLineWidth(Point mousePosition, Point lastMouseClickPosition, double strokeSize) {
        double deltaX = mousePosition.getX() - lastMouseClickPosition.getX();
        double deltaY = mousePosition.getY() - lastMouseClickPosition.getY();

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) + strokeSize / 2;
    }

    private static double computeOrientation(Point mousePosition, Point lastMouseClickPosition) {
        double orientation = Math.toDegrees(Math.atan2(lastMouseClickPosition.getY() - mousePosition.getY(),
                mousePosition.getX() - lastMouseClickPosition.getX()));

        if (orientation < 0.0) {
            orientation += 360.0;
        }

        return orientation;
    }
    
	private static Point computeNearestPoint(Point mousePosition,Snapgrid snapgrid, double strokeSize) {
		Point newPoint = new Point(0, 0);
		double distance = Double.MAX_VALUE;
		for (Point pt : snapgrid.getGridPoints()) {
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
	
	private static Point[] ajustPointWithSnapgrid(Snapgrid snapgrid, Point mousePosition, Point lastMouseClickPosition, double strokeSize) {
        Point newMousePosition = mousePosition;
        Point newLastMousePosition = lastMouseClickPosition;
        if (snapgrid.isActive()) {
            newMousePosition = computeNearestPoint(mousePosition, snapgrid, strokeSize);
            newLastMousePosition = computeNearestPoint(lastMouseClickPosition, snapgrid, strokeSize);
            if (newMousePosition == newLastMousePosition) {
                newMousePosition = getToNextPointInGrid(newMousePosition, snapgrid.getGridPoints());
            }
        }

        return new Point[]{newMousePosition, newLastMousePosition};
    }
}
