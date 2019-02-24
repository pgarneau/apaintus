package apaintus.models.commands;

import apaintus.controllers.AttributeController;
import apaintus.controllers.CanvasController;
import apaintus.controllers.ToolBarController;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.nodes.shapes.Shape;
import apaintus.models.nodes.shapes.ShapeAttributes;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestUpdateShapeCommand {
//    private static CanvasController testCanvasController;
//    private static DrawableShape testDrawableShape;
//    private static ShapeAttributes testShapeAttributes;
//    private static Attribute testAttributes;
//    private static AttributeController testAttributesController;
//    private static ToolBarController testToolBarController;
//    private static UpdateShapeCommand test;
//    @BeforeAll
//    public static void setup() {
//        testCanvasController = mock(CanvasController.class);
//        testDrawableShape = mock(DrawableShape.class);
//        testAttributes = mock(Attribute.class);
//        testAttributesController = mock(AttributeController.class);
//        testToolBarController = mock(ToolBarController.class);
//        testShapeAttributes = ShapeAttributes.builder()
//                .withCoordinates(new Point(12, 34))
//                .withHeight(10.0)
//                .withWidth(20.0)
//                .withOrientation(0.0)
//                .withStrokeColor("Red")
//                .withStrokeSize(5.0)
//                .build();
//
//        doCallRealMethod().when(testDrawableShape).setCoordinates(any(Point.class));
//        doCallRealMethod().when(testDrawableShape).setHeight(anyDouble());
//        doCallRealMethod().when(testDrawableShape).setWidth(anyDouble());
//        doCallRealMethod().when(testDrawableShape).setOrientation(anyDouble());
//        doCallRealMethod().when(testDrawableShape).setStrokeColor(anyString());
//        doCallRealMethod().when(testDrawableShape).setStrokeSize(anyDouble());
//        doCallRealMethod().when(testDrawableShape).setShapeType(any(ShapeType.class));
//        doCallRealMethod().when(testDrawableShape).getShapeAttributes();
//        doCallRealMethod().when(testDrawableShape).getShapeType();
//        doCallRealMethod().when(testDrawableShape).update(any(ShapeAttributes.class));
//        doAnswer((i) -> {
//            ShapeAttributes shapeAttributes = i.getArgument(0);
//            testDrawableShape.setCoordinates(shapeAttributes.getCoordinates());
//            testDrawableShape.setOrientation(shapeAttributes.getOrientation());
//            testDrawableShape.setStrokeColor(shapeAttributes.getStrokeColor());
//            testDrawableShape.setStrokeSize(shapeAttributes.getStrokeSize());
//            testDrawableShape.setWidth(shapeAttributes.getWidth());
//            testDrawableShape.setHeight(shapeAttributes.getHeight());
//            return null;
//        }).when((Shape) testDrawableShape).update(any(ShapeAttributes.class));
//
//        testDrawableShape.setCoordinates(new Point(23, 45));
//        testDrawableShape.setStrokeSize(2.0);
//        testDrawableShape.setWidth(1.0);
//        testDrawableShape.setHeight(2.0);
//        testDrawableShape.setStrokeColor("Blue");
//        testDrawableShape.setShapeType(ShapeType.RECTANGLE);
//        testDrawableShape.setOrientation(90.0);
//
//        when(testCanvasController.getAttributeController()).thenReturn(testAttributesController);
//        when(testCanvasController.getToolBarController()).thenReturn(testToolBarController);
//
//        doCallRealMethod().when(testAttributes).toString();
//
//        test = new UpdateShapeCommand(testCanvasController, testDrawableShape, testShapeAttributes, testAttributes);
//    }
//
//    @Test
//    public void testCreateUpdateShapeCommand() {
//        assertNotNull(test);
//    }
//
//    @Test
//    public void testExecute() {
//        UpdateShapeCommand test = new UpdateShapeCommand(testCanvasController, testDrawableShape, testShapeAttributes, testAttributes);
//        test.execute();
//
//        assertAll(
//                () -> assertEquals(testShapeAttributes.getCoordinates(), testDrawableShape.getShapeAttributes().getCoordinates()),
//                () -> assertEquals(testShapeAttributes.getHeight(), testDrawableShape.getShapeAttributes().getHeight()),
//                () -> assertEquals(testShapeAttributes.getWidth(), testDrawableShape.getShapeAttributes().getWidth()),
//                () -> assertEquals(testShapeAttributes.getOrientation(), testDrawableShape.getShapeAttributes().getOrientation()),
//                () -> assertEquals(testShapeAttributes.getStrokeColor(), testDrawableShape.getShapeAttributes().getStrokeColor()),
//                () -> assertEquals(testShapeAttributes.getStrokeSize(), testDrawableShape.getShapeAttributes().getStrokeSize())
//        );
//    }
//
//    @Test
//    public void testUndo() {
//        test.undo();
//
//        assertAll(
//                () -> assertNotEquals(testShapeAttributes.getCoordinates(), testDrawableShape.getShapeAttributes().getCoordinates()),
//                () -> assertNotEquals(testShapeAttributes.getHeight(), testDrawableShape.getShapeAttributes().getHeight()),
//                () -> assertNotEquals(testShapeAttributes.getWidth(), testDrawableShape.getShapeAttributes().getWidth()),
//                () -> assertNotEquals(testShapeAttributes.getOrientation(), testDrawableShape.getShapeAttributes().getOrientation()),
//                () -> assertNotEquals(testShapeAttributes.getStrokeColor(), testDrawableShape.getShapeAttributes().getStrokeColor()),
//                () -> assertNotEquals(testShapeAttributes.getStrokeSize(), testDrawableShape.getShapeAttributes().getStrokeSize())
//        );
//    }
//
//    @Test
//    public void testRedo() {
//        test.redo();
//
//        assertAll(
//                () -> assertEquals(testShapeAttributes.getCoordinates(), testDrawableShape.getShapeAttributes().getCoordinates()),
//                () -> assertEquals(testShapeAttributes.getHeight(), testDrawableShape.getShapeAttributes().getHeight()),
//                () -> assertEquals(testShapeAttributes.getWidth(), testDrawableShape.getShapeAttributes().getWidth()),
//                () -> assertEquals(testShapeAttributes.getOrientation(), testDrawableShape.getShapeAttributes().getOrientation()),
//                () -> assertEquals(testShapeAttributes.getStrokeColor(), testDrawableShape.getShapeAttributes().getStrokeColor()),
//                () -> assertEquals(testShapeAttributes.getStrokeSize(), testDrawableShape.getShapeAttributes().getStrokeSize())
//        );
//    }
}