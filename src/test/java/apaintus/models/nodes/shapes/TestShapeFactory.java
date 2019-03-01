package apaintus.models.nodes.shapes;

import apaintus.models.Point;
import apaintus.models.ToolBarAttributes;
import apaintus.models.toolbar.ActiveTool;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TestShapeFactory {
    Point mousePositionCreate = new Point(65.0, 76.0);
    Point lastMouseClickPositionCreate = new Point(23.0, 45.0);
    ToolBarAttributes toolBarAttributes = ToolBarAttributes
            .builder()
            .withStrokeSize(5)
            .withStrokeColor("Red")
            .withFillColor("Black")
            .build();
    double[] canvasDimensions = new double[] {600, 800};

    @Test
    public void testShapeFactoryCreateRectangleShape() {
        assertNotNull(ShapeFactory.createShape(ActiveTool.RECTANGLE, mousePositionCreate, lastMouseClickPositionCreate, toolBarAttributes, canvasDimensions));
    }

    @Test
    public void testShapeFactoryCreateCircleShape() {
        assertNotNull(ShapeFactory.createShape(ActiveTool.CIRCLE, mousePositionCreate, lastMouseClickPositionCreate, toolBarAttributes, canvasDimensions));
    }

    @Test
    public void testShapeFactoryCreateLineShape() {
        assertNotNull(ShapeFactory.createShape(ActiveTool.LINE, mousePositionCreate, lastMouseClickPositionCreate, toolBarAttributes, canvasDimensions));
    }

    @Test
    public void testShapeFactoryCreateSmiley() {
        assertNotNull(ShapeFactory.createShape(ActiveTool.SMILEY, mousePositionCreate, lastMouseClickPositionCreate, toolBarAttributes, canvasDimensions));
    }

    @Test
    public void testShapeFactoryCreateTextBoxShape() {
        assertNotNull(ShapeFactory.createShape(ActiveTool.TEXT_BOX, mousePositionCreate, lastMouseClickPositionCreate, toolBarAttributes, canvasDimensions));
    }
}
