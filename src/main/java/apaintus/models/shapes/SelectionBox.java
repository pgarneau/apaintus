package apaintus.models.shapes;

import apaintus.models.Alignment;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.selectionBox.SelectionBoxDrawService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SelectionBox extends DrawableShape {
    private List<DrawableShape> shapes;
    private HashMap<DrawableShape, Point> shapesOriginalCoordinates = new HashMap<>();
    private Point originalCoordinates;

    public SelectionBox(ShapeAttributes shapeAttributes) {
        super(ShapeType.SELECTION_BOX, shapeAttributes);
        boundingBox = new BoundingBox(shapeAttributes);
        shapes = new ArrayList<>();
    }

    public void update(Attribute attribute, double step) {
        updateShape(attribute, step, this);

        for (DrawableShape shape : shapes) {
            if (shape.getShapeType() == ShapeType.SELECTION_BOX) {
                ((SelectionBox) shape).update(attribute, step);
            }
            updateShape(attribute, step, shape);
            shape.getBoundingBox().update(shape.getShapeAttributes());
        }

        boundingBox.update(getShapeAttributes());
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

                if (shape.getShapeType() != ShapeType.SELECTION_BOX) {
                    updateShapeCoordinates(shape);
                }
                break;
            default:
                break;
        }
    }

    public void add(DrawableShape shape) {
        shapes.add(shape);
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
        width = right - left;
        height = bottom - top;

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
                    if (isDuplicate(compositeShape)) {
                        return true;
                    }
                    continue;
                }
                if (!shapes.contains(compositeShape)) {
                    return false;
                }
            }
            return true;
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
                    shapes.remove(compositeShape);
                }
            }
        }

        for (DrawableShape shape : shapes) {
            shapesOriginalCoordinates.put(shape, shape.getCoordinates());
        }
        originalCoordinates = coordinates;
    }

    public void setSelected(boolean selected) {
        super.setSelected(selected);
        resize();
    }

    @Override
    public DrawService getDrawService() {
        return new SelectionBoxDrawService(this);
    }

    public void alignShapes(Alignment alignment) {
        switch (alignment) {
            case TOP:
            case BOTTOM:
                alignVertically(alignment);
                break;

            case RIGHT:
            case LEFT:
                alignHorizontally(alignment);
                break;
            default:
                break;
        }

        resize();
    }

    private void alignHorizontally(Alignment alignment) {
        for (DrawableShape shape : shapes) {
            double x = (alignment == Alignment.LEFT) ? coordinates.getX() : (coordinates.getX() + width) - shape.getWidth();
            shape.setCoordinates(new Point(x, shape.getCoordinates().getY()));
            shape.getBoundingBox().update(shape.getShapeAttributes());
        }
    }

    private void alignVertically(Alignment alignment) {
        for (DrawableShape shape : shapes) {
            double y = (alignment == Alignment.TOP) ? coordinates.getY() : (coordinates.getY() + height) - shape.getHeight();
            shape.setCoordinates(new Point(shape.getCoordinates().getX(), y));
            shape.getBoundingBox().update(shape.getShapeAttributes());
        }
    }

    private void updateShapeCoordinates(DrawableShape shape) {
        double angle = Math.toRadians(shape.getOrientation());

        Point shapeOriginalCoordinates = shapesOriginalCoordinates.get(shape);
        double x = shapeOriginalCoordinates.getX() - originalCoordinates.getX();
        double y = shapeOriginalCoordinates.getY() - originalCoordinates.getY();
        shape.setCoordinates(new Point(originalCoordinates.getX() + (x * Math.cos(angle) + y * Math.sin(angle)), originalCoordinates.getY() - (x * Math.sin(angle) - y * Math.cos(angle))));
    }
}
