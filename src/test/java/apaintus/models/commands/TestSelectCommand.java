package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

class TestSelectCommand {
    @Test
    public void testCreateSelectCommandWithPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testPreviousActiveShape = mock(Node.class);
        Node testShape = mock(Node.class);
        doCallRealMethod().when(testShape).getNodeType();
        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);

        assertNotNull(test);
    }

    @Test
    void testExecuteWithPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testPreviousActiveShape = mock(Node.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();
        doCallRealMethod().when(testPreviousActiveShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);

        test.execute();

        assertFalse(testPreviousActiveShape.isSelected());
        assertTrue(testShape.isSelected());
    }

    @Test
    void testUndoWithPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testPreviousActiveShape = mock(Node.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();
        doCallRealMethod().when(testPreviousActiveShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);

        test.execute();
        test.undo();

        assertFalse(testShape.isSelected());
        assertTrue(testPreviousActiveShape.isSelected());
    }

    @Test
    void testRedoWithPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testPreviousActiveShape = mock(Node.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testPreviousActiveShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();
        doCallRealMethod().when(testPreviousActiveShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,testPreviousActiveShape,testShape);

        test.execute();
        test.undo();
        test.redo();

        assertTrue(testShape.isSelected());
        assertFalse(testPreviousActiveShape.isSelected());
    }

    @Test
    public void testCreateSelectCommandWithNullPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);

        assertNotNull(test);
    }

    @Test
    void testExecuteWithNullPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();
        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
        test.execute();

        assertTrue(testShape.isSelected());
    }

    @Test
    void testUndoWithNullPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);
        test.execute();
        test.undo();

        assertFalse(testShape.isSelected());
    }

    @Test
    void testRedoWithNullPreviousShape() {
        CanvasController testCanvasController = mock(CanvasController.class);
        Node testShape = mock(Node.class);

        doCallRealMethod().when(testShape).getNodeType();

        doCallRealMethod().when(testShape).setSelected(anyBoolean());
        doCallRealMethod().when(testShape).isSelected();

        SelectCommand test = new SelectCommand(testCanvasController,null,testShape);

        test.execute();
        test.undo();
        test.redo();

        assertTrue(testShape.isSelected());
    }
}