package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.nodes.shapes.Circle;
import apaintus.models.nodes.shapes.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestClearCommand {
    @Test
    public void testCreateClearCommand() {
        CanvasController testCanvasController = mock(CanvasController.class);
        ClearCommand test = new ClearCommand(testCanvasController);

        assertNotNull(test);
    }

    @Test
    public void testExecuteClearCommand() {
        CanvasController testCanvasController = mock(CanvasController.class);
        List<DrawableShape> testDrawnShapes = new ArrayList<DrawableShape>();

        Rectangle shape1 = mock(Rectangle.class);
        Rectangle shape2 = mock(Rectangle.class);
        Circle shape3 = mock(Circle.class);

        testDrawnShapes.add(shape1);
        testDrawnShapes.add(shape2);
        testDrawnShapes.add(shape3);

        //We are not mocking these since we actually need them to be used as is.
        doCallRealMethod().when(testCanvasController).setDrawnShapes(anyList());
        doCallRealMethod().when(testCanvasController).getDrawnShapes();

        //Mocking the clearCanvas methods since we don't mock the canvasService.
        doAnswer((i) -> {
            testCanvasController.getDrawnShapes().clear();
            return null;
        }).when(testCanvasController).clearCanvas();

        testCanvasController.setDrawnShapes(testDrawnShapes);

        ClearCommand test = new ClearCommand(testCanvasController);
        test.execute();
        assertTrue(testCanvasController.getDrawnShapes().size() == 0);
    }

    @Test
    public void testUndoClearCommand() {

        CanvasController testCanvasController = mock(CanvasController.class);
        List<DrawableShape> testDrawnShapes = new ArrayList<DrawableShape>();

        Rectangle shape1 = mock(Rectangle.class);
        Rectangle shape2 = mock(Rectangle.class);
        Circle shape3 = mock(Circle.class);

        testDrawnShapes.add(shape1);
        testDrawnShapes.add(shape2);
        testDrawnShapes.add(shape3);

        //We are not mocking these since we actually need them to be used as is.
        doCallRealMethod().when(testCanvasController).setDrawnShapes(anyList());
        doCallRealMethod().when(testCanvasController).getDrawnShapes();

        //Mocking the clearCanvas methods since we don't mock the canvasService.
        doAnswer((i) -> {
            testCanvasController.getDrawnShapes().clear();
            return null;
        }).when(testCanvasController).clearCanvas();

        testCanvasController.setDrawnShapes(testDrawnShapes);

        ClearCommand test = new ClearCommand(testCanvasController);
        test.execute();

        test.undo();
        assertArrayEquals(testDrawnShapes.toArray(), testCanvasController.getDrawnShapes().toArray());
    }

    @Test
    public void testRedoClearCommand(){
        CanvasController testCanvasController = mock(CanvasController.class);
        List<DrawableShape> testDrawnShapes = new ArrayList<DrawableShape>();

        Rectangle shape1 = mock(Rectangle.class);
        Rectangle shape2 = mock(Rectangle.class);
        Circle shape3 = mock(Circle.class);

        testDrawnShapes.add(shape1);
        testDrawnShapes.add(shape2);
        testDrawnShapes.add(shape3);

        //We are not mocking these since we actually need them to be used as is.
        doCallRealMethod().when(testCanvasController).setDrawnShapes(anyList());
        doCallRealMethod().when(testCanvasController).getDrawnShapes();

        //Mocking the clearCanvas methods since we don't mock the canvasService.
        doAnswer((i) -> {
            testCanvasController.getDrawnShapes().clear();
            return null;
        }).when(testCanvasController).clearCanvas();

        testCanvasController.setDrawnShapes(testDrawnShapes);

        ClearCommand test = new ClearCommand(testCanvasController);
        test.execute();
        test.undo();
        test.redo();

        assertTrue(testCanvasController.getDrawnShapes().size() == 0);
    }

    @Test
    public void testUndoRedoClearCommand(){

    }
}