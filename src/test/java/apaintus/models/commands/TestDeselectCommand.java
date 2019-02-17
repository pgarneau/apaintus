package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.shapes.DrawableShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestDeselectCommand {
    @Test
    public void testCreate() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        DeselectCommand test = new DeselectCommand(testCanvasController,testShape);
        assertNotNull(test);
    }

    @Test
    void testExecute() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        testShape.setSelected(true);

        doCallRealMethod().when(testShape).isSelected();

        DeselectCommand test = new DeselectCommand(testCanvasController,testShape);

        test.execute();
        assertFalse(testShape.isSelected());
    }

    @Test
    void testUndo() {
        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        testShape.setSelected(true);

        doCallRealMethod().when(testShape).isSelected();
        doCallRealMethod().when(testShape).setSelected(anyBoolean());

        DeselectCommand test = new DeselectCommand(testCanvasController,testShape);

        test.execute();
        test.undo();
        assertTrue(testShape.isSelected());
    }

    @Test
    void testRedo() {

        CanvasController testCanvasController = mock(CanvasController.class);
        DrawableShape testShape = mock(DrawableShape.class);
        testShape.setSelected(true);

        doCallRealMethod().when(testShape).isSelected();
        doCallRealMethod().when(testShape).setSelected(anyBoolean());

        DeselectCommand test = new DeselectCommand(testCanvasController,testShape);

        test.execute();
        test.undo();
        test.redo();
        assertFalse(testShape.isSelected());
    }
}