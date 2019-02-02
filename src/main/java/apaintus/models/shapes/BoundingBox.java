package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.bounding_box.BoundingBoxDrawService;

public class BoundingBox extends Shape {
    private static int STROKE_SIZE = 2;
    private static String STROKE_COLOR = "#000000";
    private static int LINE_DASH_SIZE = 4;

    private double width;
    private double height;
    private double shapeStrokeSize;
    private Point[] vertices;

    public BoundingBox(ShapeAttributes shapeAttributes) {
        super(ShapeType.BOUNDING_BOX, ShapeAttributes.builder()
                .withCoordinates(shapeAttributes.getCoordinates())
                .withOrientation(shapeAttributes.getOrientation())
                .withStrokeColor(STROKE_COLOR)
                .withStrokeSize(STROKE_SIZE)
                .build());

        this.width = shapeAttributes.getWidth();
        this.height = shapeAttributes.getHeight();
        this.shapeStrokeSize = shapeAttributes.getStrokeSize();

        this.vertices = computeVertices(coordinates, orientation, width, height);
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(ShapeAttributes.builder()
                .withCoordinates(shapeAttributes.getCoordinates())
                .withOrientation(shapeAttributes.getOrientation())
                .withStrokeColor(STROKE_COLOR).withStrokeSize(STROKE_SIZE)
                .build());

        this.width = shapeAttributes.getWidth();
        this.height = shapeAttributes.getHeight();
        this.shapeStrokeSize = shapeAttributes.getStrokeSize();

        this.vertices = computeVertices(coordinates, orientation, width, height);
    }

    @Override
    public DrawService getDrawService() {
        return new BoundingBoxDrawService(this);
    }

    private Point[] computeVertices(Point coordinates, double orientation, double width, double height) {
        return new Point[]{
                coordinates,
                computeCorner(coordinates, width, 0, orientation),
                computeCorner(coordinates, width, height, orientation),
                computeCorner(coordinates, 0, height, orientation)};
    }

    private Point computeCorner(Point origin, double x, double y, double orientation) {
        orientation = Math.toRadians(orientation);
        return new Point(origin.getX() + (x * Math.cos(orientation) + y * Math.sin(orientation)), origin.getY() - (x * Math.sin(orientation) - y * Math.cos(orientation)));
    }

    public int getLineDashSize() {
        return LINE_DASH_SIZE;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getShapeStrokeSize() {
        return shapeStrokeSize;
    }

    public void setShapeStrokeSize(double shapeStrokeSize) {
        this.shapeStrokeSize = shapeStrokeSize;
    }

    public Point[] getVertices() {
        return vertices;
    }

    public void setVertices(Point[] vertices) {
        this.vertices = vertices;
    }
    
    public static double getBoundingboxStrokeSize() {
    	return STROKE_SIZE;
    }
}
