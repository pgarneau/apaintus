package apaintus.models.shapes;

import apaintus.models.Attribute;
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

    public void update(Attribute attribute, double step) {
        if (shapes.isEmpty()) {
            return;
        }
        for (DrawableShape shape : shapes) {
            if (shape.getShapeType() == ShapeType.SELECTION_BOX) {
                ((SelectionBox) shape).update(attribute, step);
            }
            updateShape(attribute, step, shape);
            shape.getBoundingBox().update(shape.getShapeAttributes());
        }

        updateShape(attribute, step, this);

        // This prevents the selection box to rotate too.
        orientation = 0;
        resize();
    }

    private void updateShape(Attribute attribute, double step, DrawableShape shape) {
        switch (attribute) {
            case COORDINATE_X:
                shape.setCoordinates(new Point(shape.getCoordinates().getX() + step, shape.getCoordinates().getY()));
                break;
            case COORDINATE_Y:
                shape.setCoordinates(new Point(shape.getCoordinates().getX(), shape.getCoordinates().getY() + step));
                break;
            case ORIENTATION:
                shape.setOrientation(shape.getOrientation() + step);
                break;
        }
    }

    public void add(DrawableShape shape) {
        shapes.add(shape);
    }

    public boolean remove(DrawableShape shape) {
        return (shapes.remove(shape));
    }

    public List<DrawableShape> getShapes() {
        return shapes;
    }

    public double getSize() {
        return shapes.size();
    }

    public boolean isEmpty() {
        return shapes.isEmpty();
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

        boundingBox.update(getShapeAttributes());
    }

    public boolean isDuplicate(DrawableShape shape) {
        SelectionBox selectionBox = (SelectionBox) shape;

        if (getCoordinates().getX() == selectionBox.getCoordinates().getX()
                && getCoordinates().getY() == selectionBox.getCoordinates().getY()
                && getWidth() == selectionBox.getWidth()
                && getHeight() == selectionBox.getHeight()) {
            for (DrawableShape compositeShape : selectionBox.getShapes()) {
                if (compositeShape.getShapeType() == ShapeType.SELECTION_BOX) {
                    if(isDuplicate(compositeShape)) {
                        return true;
                    }
                    continue;
                }
                if (shapes.contains(compositeShape)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void clear() {
        shapes.clear();
    }

    public boolean contains(DrawableShape shape) {
        for (Point vertices : shape.getBoundingBox().getVertices()) {
            if (!super.contains(vertices)) {
                return false;
            }
        }

        return true;
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

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        resize();
    }

    @Override
    public DrawService getDrawService() {
        return new SelectionBoxDrawService(this);
    }
}
