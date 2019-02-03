package apaintus.models.shapes;

import apaintus.models.Point;

public class ShapeFactory {
    private static double[] dimensions;
    private static Point coordinates;
    private static double orientation;
    private static double width;

    public static DrawableShape createShape(ShapeType shapeType, Point mousePosition, Point lastMouseClickPosition, String fillColor, String strokeColor, double strokeSize) {
        switch (shapeType) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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

    public static void updateShape(Shape shape, Point mousePosition, Point lastMouseClickPosition, String fillColor, String strokeColor, double strokeSize) {
        switch (shape.getShapeType()) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                coordinates = computeCoordinates(mousePosition, lastMouseClickPosition);
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

    private static Point computeCoordinates(Point mousePosition, Point lastMouseClickPosition) {
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

        return coordinates;
    }

    private static double[] computeDimensions(Point mousePosition, Point lastMouseClickPosition) {
        // Width is index 0
        // Height is index 1
        return new double[] {Math.abs(mousePosition.getX() - lastMouseClickPosition.getX()),
                Math.abs(mousePosition.getY() - lastMouseClickPosition.getY())};
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
}
