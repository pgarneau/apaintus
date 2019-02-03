package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.update.UpdateService;
import apaintus.services.update.rectangle.RectangleUpdateService;

public abstract class DrawableShape extends Shape {
    protected boolean selected = true;
    protected BoundingBox boundingBox;

    protected DrawableShape(ShapeType shapeType, ShapeAttributes shapeAttributes) {
        super(shapeType, shapeAttributes);
    }

    // Credit: https://stackoverflow.com/questions/8721406/how-to-determine-if-a-point-is-inside-a-2d-convex-polygon
    public boolean contains(Point point) {
        int i, j;
        boolean result = false;

        Point[] vertices = boundingBox.getVertices();

        for (i = 0, j = vertices.length - 1; i < vertices.length; j = i++) {
            if ((vertices[i].getY() > point.getY()) != (vertices[j].getY() > point.getY()) &&
                    (point.getX() < (vertices[j].getX() - vertices[i].getX()) * (point.getY() - vertices[i].getY()) / (vertices[j].getY() - vertices[i].getY()) + vertices[i].getX())) {
                result = !result;
            }
        }

        return result;
    }

    public abstract UpdateService getUpdateService();

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
