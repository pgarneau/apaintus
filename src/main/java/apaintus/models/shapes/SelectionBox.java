package apaintus.models.shapes;

import apaintus.models.Alignment;
import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.NodeDrawService;
import apaintus.services.draw.ShapeDrawService;
import apaintus.services.draw.selectionBox.SelectionBoxDrawService;

import java.util.List;

public class SelectionBox extends Node {
    public static final int STROKE_SIZE = 2;
    private static final String STROKE_COLOR = "#000000";
    private static final int LINE_DASH_SIZE = 4;

    private List<Node> nodeList;

    public SelectionBox(NodeAttributes nodeAttributes) {
        super();
        nodeType = NodeType.SELECTION_BOX;
        coordinates = nodeAttributes.getCoordinates();
        width = nodeAttributes.getWidth();
        height = nodeAttributes.getHeight();
        orientation = nodeAttributes.getOrientation();
    }

    @Override
    public void update(NodeAttributes nodeAttributes) {
        coordinates = nodeAttributes.getCoordinates();
        width = nodeAttributes.getWidth();
        height = nodeAttributes.getHeight();
        orientation = nodeAttributes.getOrientation();
        boundingBox.update(coordinates, width, height, orientation);
    }

    @Override
    public NodeDrawService getDrawService() {
        return new SelectionBoxDrawService(this);
    }

    public void add(Node node) {
        nodeList.add(node);
        resize();
    }

    private void resize() {
        double left = coordinates.getX() + width;
        double top = coordinates.getY() + height;
        double right = coordinates.getX();
        double bottom = coordinates.getY();

        for (Node node : nodeList) {
            for (Point vertices : node.getBoundingBox().getVertices()) {
                left = (left > vertices.getX()) ? vertices.getX() : left;
                top = (top > vertices.getY()) ? vertices.getY() : top;
                right = (right < vertices.getX()) ? vertices.getX() : right;
                bottom = (bottom < vertices.getY()) ? vertices.getY() : bottom;
            }
        }
        coordinates = new Point(left, top);

        width = right - left;
        height = bottom - top;
        boundingBox.update(coordinates, width, height, 0);
    }


//    public void update(Attribute attribute, double step) {
//        if (shapes.isEmpty()) {
//            return;
//        }
//        for (DrawableShape shape : shapes) {
//            if (shape.getShapeType() == ShapeType.SELECTION_BOX) {
//                ((SelectionBox) shape).update(attribute, step);
//            }
//            updateShape(attribute, step, shape);
//            shape.getBoundingBox().update(shape.getShapeAttributes());
//        }
//
//        updateShape(attribute, step, this);
//
//        // This prevents the selection box to rotate too.
//        orientation = 0;
//        resize();
//    }
//
//    private void updateShape(Attribute attribute, double step, DrawableShape shape) {
//        switch (attribute) {
//            case COORDINATE_X:
//                shape.setCoordinates(new Point(shape.getCoordinates().getX() + step, shape.getCoordinates().getY()));
//                break;
//            case COORDINATE_Y:
//                shape.setCoordinates(new Point(shape.getCoordinates().getX(), shape.getCoordinates().getY() + step));
//                break;
//            case ORIENTATION:
//                shape.setOrientation(shape.getOrientation() + step);
//                break;
//            default:
//                break;
//        }
//    }
//
//    public List<DrawableShape> getShapes() {
//        return shapes;
//    }
//
//    public double getSize() {
//        return shapes.size();
//    }
//
//    public boolean isEmpty() {
//        return shapes.isEmpty();
//    }
//
//    public DrawableShape getShape(int i) {
//        return shapes.get(i);
//    }
//
//
//    public boolean isDuplicate(DrawableShape shape) {
//        SelectionBox selectionBox = (SelectionBox) shape;
//
//        if (getCoordinates().getX() == selectionBox.getCoordinates().getX()
//                && getCoordinates().getY() == selectionBox.getCoordinates().getY()
//                && getWidth() == selectionBox.getWidth()
//                && getHeight() == selectionBox.getHeight()) {
//            for (DrawableShape compositeShape : selectionBox.getShapes()) {
//                if (compositeShape.getShapeType() == ShapeType.SELECTION_BOX) {
//                    if(isDuplicate(compositeShape)) {
//                        return true;
//                    }
//                    continue;
//                }
//                if (!shapes.contains(compositeShape)) {
//                    return false;
//                }
//            }
//
//            return true;
//        }
//
//        return false;
//    }
//
//    public void clear() {
//        shapes.clear();
//    }
//
//    public boolean contains(DrawableShape shape) {
//        for (Point vertices : shape.getBoundingBox().getVertices()) {
//            if (!super.contains(vertices)) {
//                return false;
//            }
//        }
//
//        return true;
//    }
//
//    public void optimize() {
//        for (int index = 0; index < getSize(); index++) {
//            if (shapes.get(index).getShapeType() == ShapeType.SELECTION_BOX) {
//                for (DrawableShape compositeShape : ((SelectionBox) shapes.get(index)).getShapes()) {
//                    shapes.remove(compositeShape);
//                }
//            }
//        }
//    }
//
//    public void setSelected(boolean selected) {
//        super.setSelected(selected);
//        resize();
//    }
//
//
//    public void alignShapes(Alignment alignment) {
//        switch (alignment) {
//            case TOP:
//                alignTop();
//                break;
//
//            case RIGHT:
//                alignRight();
//                break;
//
//            case BOTTOM:
//                alignBottom();
//                break;
//
//            case LEFT:
//                alignLeft();
//                break;
//
//            default:
//                break;
//        }
//
//        resize();
//    }
//
//    private void alignLeft() {
//        for (DrawableShape shape : shapes) {
//            shape.setCoordinates(new Point(coordinates.getX(), shape.getCoordinates().getY()));
//            shape.getBoundingBox().update(shape.getShapeAttributes());
//        }
//    }
//
//    private void alignRight() {
//        for (DrawableShape shape : shapes) {
//            shape.setCoordinates(new Point((coordinates.getX() + width) - shape.getWidth(), shape.getCoordinates().getY()));
//            shape.getBoundingBox().update(shape.getShapeAttributes());
//        }
//    }
//
//    private void alignTop() {
//        for (DrawableShape shape : shapes) {
//            shape.setCoordinates(new Point(shape.getCoordinates().getX(), coordinates.getY()));
//            shape.getBoundingBox().update(shape.getShapeAttributes());
//        }
//    }
//
//    private void alignBottom() {
//        for (DrawableShape shape : shapes) {
//            shape.setCoordinates(new Point(shape.getCoordinates().getX(), (coordinates.getY() + height) - shape.getHeight()));
//            shape.getBoundingBox().update(shape.getShapeAttributes());
//        }
//    }
}
