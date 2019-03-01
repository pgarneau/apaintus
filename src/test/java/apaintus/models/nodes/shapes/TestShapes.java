package apaintus.models.nodes.shapes;

import apaintus.models.Point;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeAttributes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TestShapes {
    private static final String BLACK = "Black";

    @Test
    public void testCreateAndUpdateAllShapes() {
        //Creation attributes...
        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(new Point(50, 50))
                .withWidth(2)
                .withHeight(2)
                .withOrientation(90)
                .build();
        ShapeAttributes shapeAttributes = ShapeAttributes
                .builder()
                .withFillColor(BLACK)
                .withStrokeColor(BLACK)
                .withStrokeSize(6)
                .build();

        NodeAttributes updatedNodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(new Point(30, 30))
                .withWidth(6)
                .withHeight(6)
                .withOrientation(20)
                .build();

        //Rectangle
        Rectangle testRect = new Rectangle(nodeAttributes, shapeAttributes);
        Circle testCircle = new Circle(nodeAttributes, shapeAttributes);
        Line testLine = new Line(nodeAttributes, shapeAttributes);
        Smiley testSmiley = new Smiley(nodeAttributes, shapeAttributes);

        //Doing all Assertion without interruptions;
        assertAll(() -> assertAll(() -> assertEquals(2.0, testRect.getWidth(), 0.0),
                () -> assertEquals(2.0, testRect.getHeight(), 0.0),
                () -> assertEquals(50, testRect.getCoordinates().getX(), 0.0),
                () -> assertEquals(50, testRect.getCoordinates().getY(), 0.0),
                () -> assertEquals("test rect",90.0, testRect.getOrientation(), 0.0),
                () -> assertEquals("Black", testRect.getStrokeColor()),
                () -> assertEquals(6.0, testRect.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testCircle.getWidth(), 0.0),
                        () -> assertEquals(2.0, testCircle.getHeight(), 0.0),
                        () -> assertEquals(50, testCircle.getCoordinates().getX(), 0.0),
                        () -> assertEquals(50, testCircle.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test circle",90.0, testCircle.getOrientation(), 0.0),
                        () -> assertEquals("Black", testCircle.getStrokeColor()),
                        () -> assertEquals(6.0, testCircle.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testLine.getWidth(), 0.0),
                        () -> assertEquals(2.0, testLine.getHeight(), 0.0),
                        () -> assertEquals(50, testLine.getCoordinates().getX(), 0.0),
                        () -> assertEquals(50, testLine.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test line",90.0, testLine.getOrientation(), 0.0),
                        () -> assertEquals("Black", testLine.getStrokeColor()),
                        () -> assertEquals(6.0, testLine.getStrokeSize(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(2.0, testSmiley.getWidth(), 0.0),
                        () -> assertEquals(2.0, testSmiley.getHeight(), 0.0),
                        () -> assertEquals(50, testSmiley.getCoordinates().getX(), 0.0),
                        () -> assertEquals(50, testSmiley.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test smiley",90.0, testSmiley.getOrientation(), 0.0),
                        () -> assertEquals("Black", testSmiley.getStrokeColor()),
                        () -> assertEquals(6.0, testSmiley.getStrokeSize(), 0.0)
                ));

        testRect.update(updatedNodeAttributes);
        testCircle.update(updatedNodeAttributes);
        testLine.update(updatedNodeAttributes);
        testSmiley.update(updatedNodeAttributes);

        //Doing all Assertion without interruptions;
        assertAll(() -> assertAll(() -> assertEquals(6.0, testRect.getWidth(), 0.0),
                () -> assertEquals(6.0, testRect.getHeight(), 0.0),
                () -> assertEquals(30, testRect.getCoordinates().getX(), 0.0),
                () -> assertEquals(30, testRect.getCoordinates().getY(), 0.0),
                () -> assertEquals("test rect",20.0, testRect.getOrientation(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testCircle.getWidth(), 0.0),
                        () -> assertEquals(6.0, testCircle.getHeight(), 0.0),
                        () -> assertEquals(30, testCircle.getCoordinates().getX(), 0.0),
                        () -> assertEquals(30, testCircle.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test circle",20.0, testCircle.getOrientation(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testLine.getWidth(), 0.0),
                        () -> assertEquals(6.0, testLine.getHeight(), 0.0),
                        () -> assertEquals(30, testLine.getCoordinates().getX(), 0.0),
                        () -> assertEquals(30, testLine.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test line",20.0, testLine.getOrientation(), 0.0)
                ),
                () -> assertAll(() -> assertEquals(6.0, testSmiley.getWidth(), 0.0),
                        () -> assertEquals(6.0, testSmiley.getHeight(), 0.0),
                        () -> assertEquals(30, testSmiley.getCoordinates().getX(), 0.0),
                        () -> assertEquals(30, testSmiley.getCoordinates().getY(), 0.0),
                        () -> assertEquals("test smiley",20.0, testSmiley.getOrientation(), 0.0)
                ));
    }
}
