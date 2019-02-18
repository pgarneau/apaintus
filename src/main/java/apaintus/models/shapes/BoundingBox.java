package apaintus.models.shapes;

import apaintus.models.Point;

public class BoundingBox {
    public static final int STROKE_SIZE = 2;

    private Point[] vertices;
    private Point coordinates;
    private Point center;
    private double width;
    private double height;
    private double orientation;

    public BoundingBox() {}

    public void update(Point center, double width, double height, double orientation) {
        this.center = center;
        this.width = width + STROKE_SIZE;
        this.height = height + STROKE_SIZE;
        this.orientation = orientation;

        vertices = computeVertices();
    }

    // Credit: https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
    public boolean contains(Point point) {
        int i;
        int j;
        boolean result = false;

        for (i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            if ((vertices[i].getY() > point.getY()) != (vertices[j].getY() > point.getY()) &&
                    (point.getX() < (vertices[j].getX() - vertices[i].getX()) * (point.getY() - vertices[i].getY()) / (vertices[j].getY() - vertices[i].getY()) + vertices[i].getX())) {
                result = !result;
            }
        }

        return result;
    }

    private Point[] computeVertices() {
        return new Point[]{
                computeCorner(center, -width / 2, -height / 2, orientation),
                computeCorner(center, width / 2, -height / 2, orientation),
                computeCorner(center, width / 2, height / 2, orientation),
                computeCorner(center, -width / 2, height / 2, orientation)
        };
    }

    private Point computeCorner(Point origin, double x, double y, double orientation) {
        orientation = Math.toRadians(orientation);
        return new Point(origin.getX() + (x * Math.cos(orientation) + y * Math.sin(orientation)), origin.getY() - (x * Math.sin(orientation) - y * Math.cos(orientation)));
    }

    public Point[] getVertices() {
        return vertices;
    }
}
