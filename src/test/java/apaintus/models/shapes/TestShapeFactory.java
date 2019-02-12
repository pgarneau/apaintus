package apaintus.models.shapes;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestShapeFactory {

    @Test
    public void testShapeFactoryCreateShape() {

        Point mousePosition = new Point(65.0, 76.0);
        Point lastMouseClickPosition = new Point(23.0, 45.0);
        String fillColor = "Black";
        String strokeColor = "Red";
        double strokeSize = 5.0;
        double canvasWidth = 600.0;
        double canvasHeight = 800.0;

        assertAll(() -> assertNotNull(ShapeFactory.createShape(ShapeType.RECTANGLE, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.CIRCLE, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.LINE, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.SMILEY, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.TEXT_BOX, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNotNull(ShapeFactory.createShape(ShapeType.SELECTION_BOX, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight)),
                () -> assertNull(ShapeFactory.createShape(ShapeType.BOUNDING_BOX, mousePosition, lastMouseClickPosition, fillColor, strokeColor, strokeSize, canvasWidth, canvasHeight))
        );
    }

    @Test
    public void testShapeFactoryUpdateShape(){
        Shape testShape = mock(Shape.class);
        when(testShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);
        when(testShape.getStrokeSize()).thenReturn(5.0);
        when(testShape.getStrokeColor()).thenReturn("Black");
        when(testShape.getOrientation()).thenReturn(0.0);
        when(testShape.getWidth()).thenReturn(10.0);
        when(testShape.getHeight()).thenReturn(20.0);
        when(testShape.getCoordinates()).thenReturn(new Point(45.0,67.0));

        Point mousePosition = new Point(65.0, 76.0);
        Point lastMouseClickPosition = new Point(23.0, 45.0);
        String fillColor = "Black";
        String strokeColor = "Red";
        double strokeSize = 5.0;
        double canvasWidth = 600.0;
        double canvasHeight = 800.0;
        
    }
}