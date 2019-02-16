package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.models.snapgrid.SnapGrid;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestShapeFactory {
    Point mousePositionCreate = new Point(65.0, 76.0);
    Point lastMouseClickPositionCreate = new Point(23.0, 45.0);
    String fillColorCreate = "Black";
    String strokeColorCreate = "Red";
    double strokeSizeCreate = 5.0;
    double canvasWidthCreate = 600.0;
    double canvasHeightCreate = 800.0;

    Point mousePositionUpdate = new Point(65.0, 76.0);
    Point lastMouseClickPositionUpdate = new Point(23.0, 45.0);
    double widthUpdate = Math.abs(65.0 - 23.0);
    double heightUpdate = Math.abs(76.0 - 45.0);
    String fillColorUpdate = "Black";
    String strokeColorUpdate = "Red";
    double strokeSizeUpdate = 5.0;
    double canvasWidthUpdate = 600.0;
    double canvasHeightUpdate = 800.0;

    @Test
    public void testShapeFactoryCreateRectangleShape() {
        assertNotNull(ShapeFactory.createShape(ShapeType.RECTANGLE, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateCircleShape() {
        assertNotNull(ShapeFactory.createShape(ShapeType.CIRCLE, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateLineShape() {
        assertNotNull(ShapeFactory.createShape(ShapeType.LINE, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateSmiley() {
        assertNotNull(ShapeFactory.createShape(ShapeType.SMILEY, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateTextBoxShape() {
        assertNotNull(ShapeFactory.createShape(ShapeType.TEXT_BOX, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateSelectionBoxShape() {
        assertNotNull(ShapeFactory.createShape(ShapeType.SELECTION_BOX, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryCreateBoundingBoxShape() {
        assertNull(ShapeFactory.createShape(ShapeType.BOUNDING_BOX, mousePositionCreate, lastMouseClickPositionCreate, fillColorCreate, strokeColorCreate, strokeSizeCreate, canvasWidthCreate, canvasHeightCreate));
    }

    @Test
    public void testShapeFactoryUpdateRectangleShape() {
        double spacing = 40.0;
        double strokeSize = 5.0;
        double orientation = 0.0;
        Point coordinates = new Point(45.0, 67.0);

        Rectangle testRectShape = mock(Rectangle.class);
        when(testRectShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);
        when(testRectShape.getStrokeSize()).thenReturn(strokeSize);
        when(testRectShape.getOrientation()).thenReturn(orientation);
        when(testRectShape.getWidth()).thenReturn(widthUpdate);
        when(testRectShape.getHeight()).thenReturn(heightUpdate);
        when(testRectShape.getCoordinates()).thenReturn(coordinates);
        when(testRectShape.getStrokeColor()).thenReturn(strokeColorUpdate);
        when(testRectShape.getFillColor()).thenReturn(fillColorUpdate);
        Mockito.doCallRealMethod().when(testRectShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testRectShape).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testRectShape,
                lastMouseClickPositionUpdate,
                mousePositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.RECTANGLE, testRectShape.getShapeType()),
                () -> assertEquals(heightUpdate, testRectShape.getHeight()),
                () -> assertEquals(widthUpdate, testRectShape.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testRectShape.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testRectShape.getStrokeColor(), "stroke color"),
                () -> assertEquals(fillColorUpdate, testRectShape.getFillColor(), "fill color")
        );
    }

    @Test
    public void testShapeFactoryUpdateCircleShape() {
        double spacing = 20.0;
        double strokeSize = 6.0;
        double orientation = 1.0;
        Point coordinates = new Point(12.0, 34.0);

        Circle testCircleShape = mock(Circle.class);
        when(testCircleShape.getShapeType()).thenReturn(ShapeType.CIRCLE);
        when(testCircleShape.getStrokeSize()).thenReturn(strokeSize);
        when(testCircleShape.getOrientation()).thenReturn(orientation);
        when(testCircleShape.getWidth()).thenReturn(widthUpdate);
        when(testCircleShape.getHeight()).thenReturn(heightUpdate);
        when(testCircleShape.getCoordinates()).thenReturn(coordinates);
        when(testCircleShape.getStrokeColor()).thenReturn(strokeColorUpdate);
        when(testCircleShape.getFillColor()).thenReturn(fillColorUpdate);
        Mockito.doCallRealMethod().when(testCircleShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testCircleShape).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testCircleShape,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.CIRCLE, testCircleShape.getShapeType()),
                () -> assertEquals(heightUpdate, testCircleShape.getHeight()),
                () -> assertEquals(widthUpdate, testCircleShape.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testCircleShape.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testCircleShape.getStrokeColor(), "stroke color"),
                () -> assertEquals(fillColorUpdate, testCircleShape.getFillColor(), "fill color")
        );
    }

    @Test
    public void testShapeFactoryUpdateLineShape() {
        double spacing = 30.0;
        double strokeSize = 2.0;
        double orientation = 7.0;
        Point coordinates = new Point(34.0, 56.0);

        Line testLineShape = mock(Line.class);
        when(testLineShape.getShapeType()).thenReturn(ShapeType.LINE);
        when(testLineShape.getStrokeSize()).thenReturn(strokeSize);
        when(testLineShape.getOrientation()).thenReturn(orientation);
        when(testLineShape.getWidth()).thenReturn(widthUpdate);
        when(testLineShape.getHeight()).thenReturn(heightUpdate);
        when(testLineShape.getCoordinates()).thenReturn(coordinates);
        when(testLineShape.getStrokeColor()).thenReturn(strokeColorUpdate);
        Mockito.doCallRealMethod().when(testLineShape).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testLineShape,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.LINE, testLineShape.getShapeType()),
                () -> assertEquals(heightUpdate, testLineShape.getHeight()),
                () -> assertEquals(widthUpdate, testLineShape.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testLineShape.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testLineShape.getStrokeColor(), "stroke color")
        );
    }

    @Test
    public void testShapeFactoryUpdateSmileyShape() {
        double spacing = 30.0;
        double strokeSize = 2.0;
        double orientation = 7.0;
        Point coordinates = new Point(34.0, 56.0);

        Smiley testSmileyShape = mock(Smiley.class);
        when(testSmileyShape.getShapeType()).thenReturn(ShapeType.SMILEY);
        when(testSmileyShape.getStrokeSize()).thenReturn(strokeSize);
        when(testSmileyShape.getOrientation()).thenReturn(orientation);
        when(testSmileyShape.getWidth()).thenReturn(widthUpdate);
        when(testSmileyShape.getHeight()).thenReturn(heightUpdate);
        when(testSmileyShape.getCoordinates()).thenReturn(coordinates);
        when(testSmileyShape.getStrokeColor()).thenReturn(strokeColorUpdate);
        when(testSmileyShape.getFillColor()).thenReturn(fillColorUpdate);
        Mockito.doCallRealMethod().when(testSmileyShape).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testSmileyShape).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testSmileyShape,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.SMILEY, testSmileyShape.getShapeType()),
                () -> assertEquals(heightUpdate, testSmileyShape.getHeight()),
                () -> assertEquals(widthUpdate, testSmileyShape.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testSmileyShape.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testSmileyShape.getStrokeColor(), "stroke color"),
                () -> assertEquals(fillColorUpdate, testSmileyShape.getFillColor(), "fill color")
        );
    }

    @Test
    public void testShapeFactoryUpdateTextBoxShape() {
        double spacing = 30.0;
        double strokeSize = 2.0;
        double orientation = 7.0;
        Point coordinates = new Point(34.0, 56.0);

        TextBox testTextBox = mock(TextBox.class);
        when(testTextBox.getShapeType()).thenReturn(ShapeType.TEXT_BOX);
        when(testTextBox.getStrokeSize()).thenReturn(strokeSize);
        when(testTextBox.getOrientation()).thenReturn(orientation);
        when(testTextBox.getWidth()).thenReturn(widthUpdate);
        when(testTextBox.getHeight()).thenReturn(heightUpdate);
        when(testTextBox.getCoordinates()).thenReturn(coordinates);
        when(testTextBox.getStrokeColor()).thenReturn(strokeColorUpdate);
        when(testTextBox.getFillColor()).thenReturn(fillColorUpdate);
        Mockito.doCallRealMethod().when(testTextBox).setFillColor(any(String.class));
        Mockito.doCallRealMethod().when(testTextBox).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testTextBox,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.TEXT_BOX, testTextBox.getShapeType()),
                () -> assertEquals(heightUpdate, testTextBox.getHeight()),
                () -> assertEquals(widthUpdate, testTextBox.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testTextBox.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testTextBox.getStrokeColor(), "stroke color"),
                () -> assertEquals(fillColorUpdate, testTextBox.getFillColor(), "fill color")
        );
    }

    @Test
    public void testShapeFactoryUpdateBoundingBoxShape() {
        double spacing = 30.0;
        double strokeSize = 2.0;
        double orientation = 7.0;
        Point coordinates = new Point(34.0, 56.0);

        BoundingBox testBoundingBox = mock(BoundingBox.class);
        when(testBoundingBox.getShapeType()).thenReturn(ShapeType.BOUNDING_BOX);
        when(testBoundingBox.getStrokeSize()).thenReturn(strokeSize);
        when(testBoundingBox.getOrientation()).thenReturn(orientation);
        when(testBoundingBox.getWidth()).thenReturn(widthUpdate);
        when(testBoundingBox.getHeight()).thenReturn(heightUpdate);
        when(testBoundingBox.getCoordinates()).thenReturn(coordinates);
        when(testBoundingBox.getStrokeColor()).thenReturn(strokeColorUpdate);
        Mockito.doCallRealMethod().when(testBoundingBox).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testBoundingBox,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.BOUNDING_BOX, testBoundingBox.getShapeType()),
                () -> assertEquals(heightUpdate, testBoundingBox.getHeight()),
                () -> assertEquals(widthUpdate, testBoundingBox.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testBoundingBox.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testBoundingBox.getStrokeColor(), "stroke color")
        );
    }

    @Test
    public void testShapeFactoryUpdateSelectionBoxShape() {
        double spacing = 30.0;
        double strokeSize = 2.0;
        double orientation = 7.0;
        Point coordinates = new Point(34.0, 56.0);

        SelectionBox testSelectionBox = mock(SelectionBox.class);
        when(testSelectionBox.getShapeType()).thenReturn(ShapeType.SELECTION_BOX);
        when(testSelectionBox.getStrokeSize()).thenReturn(strokeSize);
        when(testSelectionBox.getOrientation()).thenReturn(orientation);
        when(testSelectionBox.getWidth()).thenReturn(widthUpdate);
        when(testSelectionBox.getHeight()).thenReturn(heightUpdate);
        when(testSelectionBox.getCoordinates()).thenReturn(coordinates);
        when(testSelectionBox.getStrokeColor()).thenReturn(strokeColorUpdate);
        Mockito.doCallRealMethod().when(testSelectionBox).setStrokeColor(any(String.class));

        SnapGrid testSnapGrid = mock(SnapGrid.class);
        when(testSnapGrid.isActive()).thenReturn(false);
        when(testSnapGrid.getGridPoints()).thenReturn(new ArrayList<Point>());
        when(testSnapGrid.getSpacing()).thenReturn(spacing);

        ShapeFactory.updateShape(testSelectionBox,
                mousePositionUpdate,
                lastMouseClickPositionUpdate,
                "Black",
                "Red",
                strokeSizeUpdate,
                testSnapGrid,
                canvasWidthUpdate,
                canvasHeightUpdate);
        assertAll(
                () -> assertEquals(ShapeType.SELECTION_BOX, testSelectionBox.getShapeType()),
                () -> assertEquals(heightUpdate, testSelectionBox.getHeight()),
                () -> assertEquals(widthUpdate, testSelectionBox.getWidth()),
                () -> assertEquals(strokeSizeUpdate, testSelectionBox.getStrokeSize()),
                () -> assertEquals(strokeColorUpdate, testSelectionBox.getStrokeColor(), "stroke color")
        );
    }

    @Test
    public void testShapeFactoryMultipleMouseDirection() {

    }
}
