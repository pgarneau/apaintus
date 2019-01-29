package apaintus.models.shapes;

public class ShapeFactory {
    private static double[] dimensions;

    public static Shape createShape(ShapeType shapeType, double[] mousePosition, double[] lastMouseClickPosition, String fillColor, String strokeColor, int strokeSize) {
        switch (shapeType) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                return new Rectangle(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.RECTANGLE)
                                .withCoordinates(computeCoordinates(mousePosition, lastMouseClickPosition))
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
                return new Circle(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.CIRCLE)
                                .withCoordinates(computeCoordinates(mousePosition, lastMouseClickPosition))
                                .withWidth(dimensions[0])
                                .withHeight(dimensions[1])
                                .withOrientation(0)
                                .withFillColor(fillColor)
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );

            case LINE:
                return new Line(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.LINE)
                                .withCoordinates(lastMouseClickPosition)
                                .withDestinationCoordinates(mousePosition)
                                .withWidth(computeLineWidth(mousePosition, lastMouseClickPosition, strokeSize))
                                .withOrientation(computeOrientation(mousePosition, lastMouseClickPosition))
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );

            default:
                return null;
        }
    }

    public static void updateShape(Shape shape, double[] mousePosition, double[] lastMouseClickPosition, String fillColor, String strokeColor, int strokeSize) {
        switch (shape.getShapeAttributes().getShapeType()) {
            case RECTANGLE:
                dimensions = computeDimensions(mousePosition, lastMouseClickPosition);
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.RECTANGLE)
                                .withCoordinates(computeCoordinates(mousePosition, lastMouseClickPosition))
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
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.CIRCLE)
                                .withCoordinates(computeCoordinates(mousePosition, lastMouseClickPosition))
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
                shape.update(
                        ShapeAttributes
                                .builder()
                                .withShapeType(ShapeType.LINE)
                                .withCoordinates(lastMouseClickPosition)
                                .withDestinationCoordinates(mousePosition)
                                .withWidth(computeLineWidth(mousePosition, lastMouseClickPosition, strokeSize))
                                .withOrientation(computeOrientation(mousePosition, lastMouseClickPosition))
                                .withStrokeColor(strokeColor)
                                .withStrokeSize(strokeSize)
                                .build()
                );
                break;
        }
    }

    private static double[] computeCoordinates(double[] mousePosition, double[] lastMouseClickPosition) {
        double[] coordinates;

        boolean upMotion = mousePosition[1] - lastMouseClickPosition[1] <= 0.0;
        boolean rightMotion = mousePosition[0] - lastMouseClickPosition[0] > 0.0;

        if (upMotion && !rightMotion) {
            coordinates = mousePosition;
        } else if (upMotion && rightMotion) {
            coordinates = new double[] {lastMouseClickPosition[0], mousePosition[1]};
        } else if (!upMotion && rightMotion) {
            coordinates = lastMouseClickPosition;
        } else if (!upMotion && !rightMotion) {
            coordinates = new double[] {mousePosition[0], lastMouseClickPosition[1]};
        } else {
            System.err.print(
                    "this should not happen in any case. This probably means that the lastMouseClickPosition is null");
            coordinates = new double[] {0, 0};
        }

        return coordinates;
    }

    private static double[] computeDimensions(double[] mousePosition, double[] lastMouseClickPosition) {
        // Width is index 0
        // Height is index 1
        return new double[] {Math.abs(mousePosition[0] - lastMouseClickPosition[0]),
                Math.abs(mousePosition[1] - lastMouseClickPosition[1])};
    }

    private static double computeLineWidth(double[] mousePosition, double[] lastMouseClickPosition, int strokeSize) {
        double deltaX = mousePosition[0] - lastMouseClickPosition[0];
        double deltaY = mousePosition[1] - lastMouseClickPosition[1];

        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)) + strokeSize / 2;
    }

    private static double computeOrientation(double[] mousePosition, double[] lastMouseClickPosition) {
        double orientation = Math.toDegrees(Math.atan2(lastMouseClickPosition[1] - mousePosition[1],
                mousePosition[0] - lastMouseClickPosition[0]));

        if (orientation < 0.0) {
            orientation += 360.0;
        }

        return orientation;
    }
}
