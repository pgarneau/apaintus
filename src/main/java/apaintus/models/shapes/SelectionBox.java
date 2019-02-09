package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.selection_box.SelectionBoxDrawService;

import java.util.ArrayList;
import java.util.List;

public class SelectionBox extends DrawableShape {
    private List<DrawableShape> shapes;

    public SelectionBox(ShapeAttributes shapeAttributes) {
        super(ShapeType.SELECTION_BOX, shapeAttributes);
        boundingBox = new BoundingBox(shapeAttributes);
        shapes = new ArrayList<>();
    }

    public void update(ShapeAttributes shapeAttributes) {
        super.update(shapeAttributes);
        boundingBox.update(shapeAttributes);
    }

    public void add(DrawableShape shape) {
        shapes.add(shape);
    }

    public boolean remove(DrawableShape shape) {
        return (shapes.remove(shape));
    }

    public boolean isEmpty() {
        return shapes.isEmpty();
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public double getSize() {
        return shapes.size();
    }

    public DrawableShape getShape(int i) {
        return shapes.get(i);
    }

    public void resize() {
        double left = coordinates.getX() + width;
        double top = coordinates.getY() + height;
        double right = coordinates.getX();
        double bottom = coordinates.getY();

        for (DrawableShape shape : shapes) {
            for (Point vertices : shape.getBoundingBox().getVertices()) {
                left = (left > vertices.getX()) ? vertices.getX() : left;
                top = (top > vertices.getY()) ? vertices.getY() : top;
                right = (right < vertices.getX()) ? vertices.getX() : right;
                bottom = (bottom < vertices.getY()) ? vertices.getY() : bottom;
            }
        }
        coordinates = new Point(left, top);

        Point dimension = new Point(right - left, bottom - top);
        width = dimension.getX();
        height = dimension.getY();

        update(getShapeAttributes());
    }

    public boolean isDuplicate(DrawableShape shape) {
        SelectionBox selectionBox = (SelectionBox) shape;

        if (getCoordinates().getX() == selectionBox.getCoordinates().getX()
                && getCoordinates().getY() == selectionBox.getCoordinates().getY()
                && getWidth() == selectionBox.getWidth()
                && getHeight() == selectionBox.getHeight()
                && getSize() - 1 == selectionBox.getSize()) {
            for (int index = 0; index < getSize(); index++) {
                if (getShape(index) == selectionBox.getShape(index)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void optimize() {
        for (int index = 0; index < getSize(); index++) {
            if (shapes.get(index).getShapeType() == ShapeType.SELECTION_BOX) {
                for (DrawableShape compositeShape : ((SelectionBox) shapes.get(index)).getShapes()) {
                    if (remove(compositeShape)) {
                        index--;
                    }
                }
            }
        }
    }

    @Override
    public DrawService getDrawService() {
        return new SelectionBoxDrawService(this);
    }
}
