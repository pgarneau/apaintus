package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.models.ToolBarAttributes;
import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.toolbar.ActiveTool;

import java.util.List;

public class NodeFactory {
    public static double[] dimensions = new double[]{0, 0};
    private static Point coordinates;
    private static double orientation;

    private NodeFactory() {
    }

    public static Node createNode(ActiveTool activeTool, Point mousePosition, Point lastMouseClickPosition, ToolBarAttributes toolBarAttributes, double[] canvasDimensions) {
        switch (activeTool) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

                return new Rectangle(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .build(),
                        ShapeAttributes
                                .builder()
                                .withFillColor(toolBarAttributes.getFillColor())
                                .withStrokeColor(toolBarAttributes.getStrokeColor())
                                .withStrokeSize(toolBarAttributes.getStrokeSize())
                                .build()
                );

            case CIRCLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

                return new Circle(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .build(),
                        ShapeAttributes
                                .builder()
                                .withFillColor(toolBarAttributes.getFillColor())
                                .withStrokeColor(toolBarAttributes.getStrokeColor())
                                .withStrokeSize(toolBarAttributes.getStrokeSize())
                                .build()
                );

            case LINE:
                orientation = computeOrientation(mousePosition, lastMouseClickPosition);
                dimensions[0] = computeLineWidth(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

                return new Line(
                        NodeAttributes
                                .builder()
                                .withCoordinates(lastMouseClickPosition)
                                .withWidth(dimensions[0])
                                .withHeight(toolBarAttributes.getStrokeSize())
                                .withOrientation(orientation)
                                .build(),
                        ShapeAttributes
                                .builder()
                                .withStrokeColor(toolBarAttributes.getStrokeColor())
                                .withStrokeSize(toolBarAttributes.getStrokeSize())
                                .build()
                );

            case SMILEY:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

                return new Smiley(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .build(),
                        ShapeAttributes
                                .builder()
                                .withFillColor(toolBarAttributes.getFillColor())
                                .withStrokeColor(toolBarAttributes.getStrokeColor())
                                .withStrokeSize(toolBarAttributes.getStrokeSize())
                                .build()
                );

            case TEXT_BOX:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

                return new TextBox(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .build(),
                        ShapeAttributes
                                .builder()
                                .withFillColor(toolBarAttributes.getFillColor())
                                .withStrokeColor(toolBarAttributes.getStrokeColor())
                                .withStrokeSize(toolBarAttributes.getStrokeSize())
                                .build()
                );

            case SELECT:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, SelectionBox.STROKE_SIZE);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, SelectionBox.STROKE_SIZE);
                return new SelectionBox(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .build()
                );

            default:
                return null;
        }
    }

    public static void updateNode(Node node, Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid) {
        if (snapGrid.isActive()) {
            Point[] snapGridMousePositions = getSnapGridMousePositions(mousePosition, lastMouseClickPosition, snapGrid);
            mousePosition = snapGridMousePositions[0];
            lastMouseClickPosition = snapGridMousePositions[1];
        }

        switch (node.getNodeType()) {
            case RECTANGLE:
            case CIRCLE:
            case SMILEY:
            case TEXT_BOX:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, Node.get(node, "strokeSize"));
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, Node.get(node, "strokeSize"));

                node.update(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .build()

                );
                break;

            case SELECTION_BOX:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, 0);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition, 0);

                node.update(
                        NodeAttributes
                                .builder()
                                .withCoordinates(coordinates)
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .build()

                );
                break;

            case LINE:
                orientation = computeOrientation(mousePosition, lastMouseClickPosition);
                dimensions[0] = computeLineWidth(mousePosition, lastMouseClickPosition, Node.get(node, "strokeSize"));

                node.update(
                        NodeAttributes
                                .builder()
                                .withCoordinates(lastMouseClickPosition)
                                .withWidth(dimensions[0])
                                .withHeight(Node.get(node, "strokeSize"))
                                .withOrientation(orientation)
                                .build()
                );
                break;

            default:
                break;
        }
    }

    private static Point getToNextPointInGrid(Point lastPos, List<Point> gridPts) {
        Point newPoint = new Point(0, 0);
        for (int i = 0; i < gridPts.size(); i++) {
            Point pt = gridPts.get(i);
            if (pt.getX() == lastPos.getX() && pt.getY() == lastPos.getY())
                newPoint = gridPts.get(i + 1);
        }
        return newPoint;
    }

    private static Point computeCoordinates(Point mousePosition, Point lastMouseClickPosition, double strokeSize) {
        Point coordinates;

        boolean upMotion = mousePosition.getY() - lastMouseClickPosition.getY() <= 0.0;
        boolean rightMotion = mousePosition.getX() - lastMouseClickPosition.getX() > 0.0;

        if (upMotion && !rightMotion) {
            coordinates = mousePosition;
        } else if (upMotion && rightMotion) {
            coordinates = new Point(lastMouseClickPosition.getX(), mousePosition.getY());
        } else if (rightMotion) {
            coordinates = lastMouseClickPosition;
        } else {
            coordinates = new Point(mousePosition.getX(), lastMouseClickPosition.getY());
        }

        if (coordinates.getX() == 0)
            coordinates.setX(strokeSize / 1);
        if (coordinates.getY() == 0)
            coordinates.setY(strokeSize / 1);
        return coordinates;
    }

    private static double[] computeDimensions(Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions, double strokeSize) {
        // Width is index 0
        // Height is index 1
        double[] dimension = new double[]{Math.abs(mousePosition.getX() - lastMouseClickPosition.getX()),
                Math.abs(mousePosition.getY() - lastMouseClickPosition.getY())};

        if (mousePosition.getX() >= canvasDimensions[0])
            dimension[0] = Math.abs(canvasDimensions[0] - lastMouseClickPosition.getX() - strokeSize);
        if (mousePosition.getY() >= canvasDimensions[1])
            dimension[1] = Math.abs(canvasDimensions[1] - lastMouseClickPosition.getY() - strokeSize);

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

    private static Point computeNearestPoint(Point mousePosition, apaintus.models.snapgrid.SnapGrid snapgrid) {
        Point newPoint = new Point(0, 0);
        double distance = Double.MAX_VALUE;
        for (Point pt : snapgrid.getSnapGridPoints()) {
            double x = (Math.abs(pt.getX() - mousePosition.getX()));
            double y = (Math.abs(pt.getY() - mousePosition.getY()));
            double currentDistance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
            if (distance > currentDistance) {
                distance = currentDistance;
                newPoint = pt;
            }
        }

        return newPoint;
    }

    private static Point[] getSnapGridMousePositions(Point mousePosition, Point lastMouseClickPosition, SnapGrid snapGrid) {
        Point newMousePosition;
        Point newLastMousePosition;

        newMousePosition = computeNearestPoint(mousePosition, snapGrid);
        newLastMousePosition = computeNearestPoint(lastMouseClickPosition, snapGrid);

        if (newMousePosition == newLastMousePosition) {
            newMousePosition = getToNextPointInGrid(newMousePosition, snapGrid.getSnapGridPoints());
        }

        return new Point[]{newMousePosition, newLastMousePosition};
    }
}
