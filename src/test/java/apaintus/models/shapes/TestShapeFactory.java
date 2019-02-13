package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.models.snapgrid.SnapGrid;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestShapeFactory {
    private static final String FILL_COLOR = "Black";
    private static final String STROKE_COLOR = "Red";

    @Test
    public void testShapeFactoryCreateShape() {

        Point mousePosition = new Point(65.0, 76.0);
        Point lastMouseClickPosition = new Point(23.0, 45.0);
        double strokeSize = 5.0;
        double canvasWidth = 600.0;
        double canvasHeight = 800.0;

        assertAll(() -> assertNotNull(ShapeFactory.createShape(ShapeType.RECTANGLE, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.CIRCLE, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.LINE, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.SMILEY, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.TEXT_BOX, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.SELECTION_BOX, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNull(ShapeFactory.createShape(ShapeType.BOUNDING_BOX, mousePosition, lastMouseClickPosition, FILL_COLOR, STROKE_COLOR, strokeSize, canvasWidth, canvasHeight))
        );
    }

    @Test
    public void testShapeFactoryUpdateShape() {
        Point mousePosition = new Point(65.0, 76.0);
        Point lastMouseClickPosition = new Point(23.0, 45.0);
        double width = Math.abs(65.0 - 23.0);
        double height = Math.abs(76.0 - 45.0);
        String fillColor = "Black";
        String strokeColor = "Red";
        double strokeSize = 5.0;
        double canvasWidth = 600.0;
        double canvasHeight = 800.0;

        Rectangle testRectShape = mock(Rectangle.class);
        when(testRectShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);
        when(testRectShape.getStrokeSize()).thenReturn(5.0);
        when(testRectShape.getOrientation()).thenReturn(0.0);
        when(testRectShape.getWidth()).thenReturn(width);
        when(testRectShape.getHeight()).thenReturn(height);
        when(testRectShape.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testRectShape.getStrokeColor()).thenReturn(STROKE_COLOR);
        when(testRectShape.getFillColor()).thenReturn(FILL_COLOR);
        Mockito.doCallRealMethod().when(testRectShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testRectShape).setStrokeColor(any(String.class));

        Circle testCircleShape = mock(Circle.class);
        when(testCircleShape.getShapeType()).thenReturn(ShapeType.CIRCLE);
        when(testCircleShape.getStrokeSize()).thenReturn(5.0);
        when(testCircleShape.getOrientation()).thenReturn(0.0);
        when(testCircleShape.getWidth()).thenReturn(width);
        when(testCircleShape.getHeight()).thenReturn(height);
        when(testCircleShape.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testCircleShape.getStrokeColor()).thenReturn(STROKE_COLOR);
        when(testCircleShape.getFillColor()).thenReturn(FILL_COLOR);
        Mockito.doCallRealMethod().when(testCircleShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testCircleShape).setStrokeColor(any(String.class));

        Line testLineShape = mock(Line.class);
        when(testLineShape.getShapeType()).thenReturn(ShapeType.LINE);
        when(testLineShape.getStrokeSize()).thenReturn(5.0);
        when(testLineShape.getOrientation()).thenReturn(0.0);
        when(testLineShape.getWidth()).thenReturn(width);
        when(testLineShape.getHeight()).thenReturn(height);
        when(testLineShape.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testLineShape.getStrokeColor()).thenReturn(STROKE_COLOR);
        Mockito.doCallRealMethod().when(testLineShape).setStrokeColor(any(String.class));

        Smiley testSmileyShape = mock(Smiley.class);
        when(testSmileyShape.getShapeType()).thenReturn(ShapeType.SMILEY);
        when(testSmileyShape.getStrokeSize()).thenReturn(5.0);
        when(testSmileyShape.getOrientation()).thenReturn(0.0);
        when(testSmileyShape.getWidth()).thenReturn(width);
        when(testSmileyShape.getHeight()).thenReturn(height);
        when(testSmileyShape.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testSmileyShape.getStrokeColor()).thenReturn(STROKE_COLOR);
        when(testSmileyShape.getFillColor()).thenReturn(FILL_COLOR);
        Mockito.doCallRealMethod().when(testSmileyShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testSmileyShape).setStrokeColor(any(String.class));

        TextBox testTextBox = mock(TextBox.class);
        when(testTextBox.getShapeType()).thenReturn(ShapeType.TEXT_BOX);
        when(testTextBox.getStrokeSize()).thenReturn(5.0);
        when(testTextBox.getOrientation()).thenReturn(0.0);
        when(testTextBox.getWidth()).thenReturn(width);
        when(testTextBox.getHeight()).thenReturn(height);
        when(testTextBox.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testTextBox.getStrokeColor()).thenReturn(STROKE_COLOR);
        when(testTextBox.getFillColor()).thenReturn(FILL_COLOR);
        Mockito.doCallRealMethod().when(testTextBox).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testTextBox).setStrokeColor(any(String.class));

        BoundingBox testBoundingBox = mock(BoundingBox.class);
        when(testBoundingBox.getShapeType()).thenReturn(ShapeType.BOUNDING_BOX);
        when(testBoundingBox.getStrokeSize()).thenReturn(5.0);
        when(testBoundingBox.getOrientation()).thenReturn(0.0);
        when(testBoundingBox.getWidth()).thenReturn(width);
        when(testBoundingBox.getHeight()).thenReturn(height);
        when(testBoundingBox.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testBoundingBox.getStrokeColor()).thenReturn(STROKE_COLOR);
        Mockito.doCallRealMethod().when(testBoundingBox).setStrokeColor(any(String.class));

        SelectionBox testSelectionBox = mock(SelectionBox.class);
        when(testSelectionBox.getShapeType()).thenReturn(ShapeType.SELECTION_BOX);
        when(testSelectionBox.getStrokeSize()).thenReturn(5.0);
        when(testSelectionBox.getOrientation()).thenReturn(0.0);
        when(testSelectionBox.getWidth()).thenReturn(width);
        when(testSelectionBox.getHeight()).thenReturn(height);
        when(testSelectionBox.getCoordinates()).thenReturn(new Point(45.0, 67.0));
        when(testSelectionBox.getStrokeColor()).thenReturn(STROKE_COLOR);
        Mockito.doCallRealMethod().when(testSelectionBox).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(40.0);

        ShapeFactory.updateShape(testRectShape,
                lastMouseClickPosition,
                mousePosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.RECTANGLE, testRectShape.getShapeType()),
                () -> assertEquals(height, testRectShape.getHeight()),
                () -> assertEquals(width, testRectShape.getWidth()),
                () -> assertEquals(strokeSize, testRectShape.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testRectShape.getStrokeColor()),
                () -> assertEquals(FILL_COLOR, testRectShape.getFillColor())
        );

        ShapeFactory.updateShape(testCircleShape,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.CIRCLE, testCircleShape.getShapeType()),
                () -> assertEquals(height, testCircleShape.getHeight()),
                () -> assertEquals(width, testCircleShape.getWidth()),
                () -> assertEquals(strokeSize, testCircleShape.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testCircleShape.getStrokeColor()),
                () -> assertEquals(FILL_COLOR, testCircleShape.getFillColor())
        );

        ShapeFactory.updateShape(testLineShape,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.LINE, testLineShape.getShapeType()),
                () -> assertEquals(height, testLineShape.getHeight()),
                () -> assertEquals(width, testLineShape.getWidth()),
                () -> assertEquals(strokeSize, testLineShape.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testLineShape.getStrokeColor())
        );

        ShapeFactory.updateShape(testSmileyShape,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.SMILEY, testSmileyShape.getShapeType()),
                () -> assertEquals(height, testSmileyShape.getHeight()),
                () -> assertEquals(width, testSmileyShape.getWidth()),
                () -> assertEquals(strokeSize, testSmileyShape.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testSmileyShape.getStrokeColor()),
                () -> assertEquals(FILL_COLOR, testSmileyShape.getFillColor())
        );

        ShapeFactory.updateShape(testTextBox,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.TEXT_BOX, testTextBox.getShapeType()),
                () -> assertEquals(height, testTextBox.getHeight()),
                () -> assertEquals(width, testTextBox.getWidth()),
                () -> assertEquals(strokeSize, testTextBox.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testTextBox.getStrokeColor()),
                () -> assertEquals(FILL_COLOR, testTextBox.getFillColor())
        );

        ShapeFactory.updateShape(testBoundingBox,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.BOUNDING_BOX, testBoundingBox.getShapeType()),
                () -> assertEquals(height, testBoundingBox.getHeight()),
                () -> assertEquals(width, testBoundingBox.getWidth()),
                () -> assertEquals(strokeSize, testBoundingBox.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testBoundingBox.getStrokeColor())
        );

        ShapeFactory.updateShape(testSelectionBox,
                mousePosition,
                lastMouseClickPosition,
                FILL_COLOR,
                STROKE_COLOR,
                strokeSize,
                testSnapGrid,
                canvasWidth,
                canvasHeight);
        assertAll(
                () -> assertEquals(ShapeType.SELECTION_BOX, testSelectionBox.getShapeType()),
                () -> assertEquals(height, testSelectionBox.getHeight()),
                () -> assertEquals(width, testSelectionBox.getWidth()),
                () -> assertEquals(strokeSize, testSelectionBox.getStrokeSize()),
                () -> assertEquals(STROKE_COLOR, testSelectionBox.getStrokeColor())
        );
    }

    @Test
    public void testMultipleMouseDirection(){
    }
}
