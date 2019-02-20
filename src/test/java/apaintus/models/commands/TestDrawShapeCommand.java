package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.Shape;
import apaintus.models.shapes.ShapeType;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDrawShapeCommand {
    @Test
    public void testCreateDrawShapeCommand() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        SelectCommand testSelectCommand = mock(SelectCommand.class);

        doCallRealMethod().when(testShape).getShapeType();
        doCallRealMethod().when(testShape).setShapeType(any(ShapeType.class));
        testShape.setShapeType(ShapeType.RECTANGLE);

        DrawShapeCommand test = new DrawShapeCommand(testCanvasController, testShape, testSelectCommand);

        assertNotNull(test);
    }

    @Test
    void testExecute() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        SelectCommand testSelectCommand = mock(SelectCommand.class);

        doCallRealMethod().when(testCanvasController).saveDrawLayer();

        when(testShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        doAnswer((i) -> {
            return null;
        }).when(testSelectCommand).execute();

        DrawShapeCommand test = new DrawShapeCommand(testCanvasController, testShape, testSelectCommand);
        test.execute();

        //Need to check later what assertion we do want to do.
        //assertFalse(testCanvasController.getDrawnShapes().isEmpty());
    }

    @Test
    void testUndo() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        SelectCommand testSelectCommand = mock(SelectCommand.class);

        doCallRealMethod().when(testCanvasController).saveDrawLayer();

        when(testShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        doAnswer((i) -> {
            return null;
        }).when(testSelectCommand).execute();

        DrawShapeCommand test = new DrawShapeCommand(testCanvasController, testShape, testSelectCommand);
        test.execute();
        test.undo();
    }

    @Test
    void testRedo() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        SelectCommand testSelectCommand = mock(SelectCommand.class);

        doCallRealMethod().when(testCanvasController).saveDrawLayer();

        when(testShape.getShapeType()).thenReturn(ShapeType.RECTANGLE);

        doAnswer((i) -> {
            return null;
        }).when(testSelectCommand).execute();

        DrawShapeCommand test = new DrawShapeCommand(testCanvasController, testShape, testSelectCommand);
        test.execute();
        test.undo();
        test.redo();
    }
}