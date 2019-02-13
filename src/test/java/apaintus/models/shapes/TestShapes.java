package apaintus.models.shapes;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TestShapes {
    private static final String BLACK = "Black";
    private static final String WHITE = "White";

    @Test
    public void testCreateAndUpdateAllShapes() {
        //Creation attributes...
        ShapeAttributes mockedShapeAttributes = mock(ShapeAttributes.class);
        Point testPoint = new Point(50.0, 50.0);
        when(mockedShapeAttributes.getHeight()).thenReturn(2.0);
        when(mockedShapeAttributes.getWidth()).thenReturn(2.0);
        when(mockedShapeAttributes.getCoordinates()).thenReturn(testPoint);
        when(mockedShapeAttributes.getOrientation()).thenReturn(90.0);
        when(mockedShapeAttributes.getStrokeColor()).thenReturn(BLACK);
        when(mockedShapeAttributes.getStrokeSize()).thenReturn(6.0);

        //Update attributes...
        ShapeAttributes mockedNewShapeAttributes = mock(ShapeAttributes.class);
        Point testNewPoint = new Point(30.0, 30.0);
        when(mockedNewShapeAttributes.getHeight()).thenReturn(6.0);
        when(mockedNewShapeAttributes.getWidth()).thenReturn(6.0);
        when(mockedNewShapeAttributes.getCoordinates()).thenReturn(testNewPoint);
        when(mockedNewShapeAttributes.getOrientation()).thenReturn(20.0);
        when(mockedNewShapeAttributes.getStrokeColor()).thenReturn(WHITE);
        when(mockedNewShapeAttributes.getStrokeSize()).thenReturn(2.0);

        //Rectangle
        Rectangle testRect = new Rectangle(mockedShapeAttributes);
        Circle testCircle = new Circle(mockedShapeAttributes);
        Line testLine = new Line(mockedShapeAttributes);
        Smiley testSmiley = new Smiley(mockedShapeAttributes);

        //Doing all Assertion without interruptions;
        assertAll(() -> assertAll(() -> assertEquals(2.0, testRect.getWidth(), 0.0),
                () -> assertEquals(2.0, testRect.getHeight(), 0.0),
                () -> assertEquals(testPoint.getX(), testRect.getCoordinates().getX(), 0.0),
                () -> assertEquals(testPoint.getY(), testRect.getCoordinates().getY(), 0.0),
                () -> assertEquals(90.0, testRect.getOrientation(), 0.0),
                () -> assertEquals(BLACK, testRect.getStrokeColor()),
                () -> assertEquals(6.0, testRect.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testCircle.getWidth(), 0.0),
                        () -> assertEquals(2.0, testCircle.getHeight(), 0.0),
                        () -> assertEquals(testPoint.getX(), testCircle.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testPoint.getY(), testCircle.getCoordinates().getY(), 0.0),
                        () -> assertEquals(90.0, testCircle.getOrientation(), 0.0),
                        () -> assertEquals(BLACK, testCircle.getStrokeColor()),
                        () -> assertEquals(6.0, testCircle.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testLine.getWidth(), 0.0),
                        () -> assertEquals(2.0, testLine.getHeight(), 0.0),
                        () -> assertEquals(testPoint.getX(), testLine.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testPoint.getY(), testLine.getCoordinates().getY(), 0.0),
                        () -> assertEquals(90.0, testLine.getOrientation(), 0.0),
                        () -> assertEquals(BLACK, testLine.getStrokeColor()),
                        () -> assertEquals(6.0, testLine.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testSmiley.getWidth(), 0.0),
                        () -> assertEquals(2.0, testSmiley.getHeight(), 0.0),
                        () -> assertEquals(testPoint.getX(), testSmiley.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testPoint.getY(), testSmiley.getCoordinates().getY(), 0.0),
                        () -> assertEquals(90.0, testSmiley.getOrientation(), 0.0),
                        () -> assertEquals(BLACK, testSmiley.getStrokeColor()),
                        () -> assertEquals(6.0, testSmiley.getStrokeSize(), 0.0)
                ));

        testRect.update(mockedNewShapeAttributes);
        testCircle.update(mockedNewShapeAttributes);
        testLine.update(mockedNewShapeAttributes);
        testSmiley.update(mockedNewShapeAttributes);

        //Doing all Assertion without interruptions;
        assertAll(() -> assertAll(() -> assertEquals(6.0, testRect.getWidth(), 0.0),
                () -> assertEquals(6.0, testRect.getHeight(), 0.0),
                () -> assertEquals(testNewPoint.getX(), testRect.getCoordinates().getX(), 0.0),
                () -> assertEquals(testNewPoint.getY(), testRect.getCoordinates().getY(), 0.0),
                () -> assertEquals(20.0, testRect.getOrientation(), 0.0),
                () -> assertEquals(WHITE, testRect.getStrokeColor()),
                () -> assertEquals(2.0, testRect.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testCircle.getWidth(), 0.0),
                        () -> assertEquals(6.0, testCircle.getHeight(), 0.0),
                        () -> assertEquals(testNewPoint.getX(), testCircle.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testNewPoint.getY(), testCircle.getCoordinates().getY(), 0.0),
                        () -> assertEquals(20.0, testCircle.getOrientation(), 0.0),
                        () -> assertEquals(WHITE, testCircle.getStrokeColor()),
                        () -> assertEquals(2.0, testCircle.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testLine.getWidth(), 0.0),
                        () -> assertEquals(6.0, testLine.getHeight(), 0.0),
                        () -> assertEquals(testNewPoint.getX(), testLine.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testNewPoint.getY(), testLine.getCoordinates().getY(), 0.0),
                        () -> assertEquals(20.0, testLine.getOrientation(), 0.0),
                        () -> assertEquals(WHITE, testLine.getStrokeColor()),
                        () -> assertEquals(2.0, testLine.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testSmiley.getWidth(), 0.0),
                        () -> assertEquals(6.0, testSmiley.getHeight(), 0.0),
                        () -> assertEquals(testNewPoint.getX(), testSmiley.getCoordinates().getX(), 0.0),
                        () -> assertEquals(testNewPoint.getY(), testSmiley.getCoordinates().getY(), 0.0),
                        () -> assertEquals(20.0, testSmiley.getOrientation(), 0.0),
                        () -> assertEquals(WHITE, testSmiley.getStrokeColor()),
                        () -> assertEquals(2.0, testSmiley.getStrokeSize(), 0.0)
                ));
    }

    @Test
    public void testDrawRect() {
        //Mocking Shape attributes...
        ShapeAttributes mockedShapeAttributes = mock(ShapeAttributes.class);
        when(mockedShapeAttributes.getHeight()).thenReturn(2.0);
        when(mockedShapeAttributes.getWidth()).thenReturn(2.0);
        Point testPoint = new Point(50.0, 50.0);
        when(mockedShapeAttributes.getCoordinates()).thenReturn(testPoint);
        when(mockedShapeAttributes.getOrientation()).thenReturn(90.0, 0.0);
        when(mockedShapeAttributes.getStrokeColor()).thenReturn(BLACK);
        when(mockedShapeAttributes.getStrokeSize()).thenReturn(6.0);

        Rectangle testRect = new Rectangle(mockedShapeAttributes);
        assertNotNull("Shapes should always have a DrawService", testRect.getDrawService());
    }
}
