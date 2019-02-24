package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.shapes.Rectangle;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class TestDeselectCommand {
    @Test
    public void executeWhenPreviousNodeExistsThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        Node node = mock(Rectangle.class);
        DeselectCommand deselectCommand = new DeselectCommand(canvasController, node);

        // When
        deselectCommand.execute();

        // Then
        verify(canvasController, times(1)).clearActiveNode();
        assertFalse(node.isSelected());
    }

    @Test
    public void executeWhenNoPreviousNodeThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        DeselectCommand deselectCommand = new DeselectCommand(canvasController, null);

        // When
        deselectCommand.execute();

        // Then
        verify(canvasController, times(1)).clearActiveNode();
    }

    @Test
    public void undoWhenPreviousNodeExistsThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        Node node = mock(Rectangle.class);
        DeselectCommand deselectCommand = new DeselectCommand(canvasController, node);

        // When
        deselectCommand.execute();
        deselectCommand.undo();

        // Then
        verify(canvasController, times(1)).setActiveNode(node);
        verify(node, times(1)).setSelected(true);
    }

    @Test
    public void redoWhenPreviousNodeExistsThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        Node node = mock(Rectangle.class);
        DeselectCommand deselectCommand = new DeselectCommand(canvasController, node);

        // When
        deselectCommand.redo();

        // Then
        verify(canvasController, times(1)).clearActiveNode();
        assertFalse(node.isSelected());
    }

    @Test
    public void getDescriptionThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        Node node = mock(Rectangle.class);
        DeselectCommand deselectCommand = new DeselectCommand(canvasController, node);

        String expectedDescription = "Clear Canvas";

        // When
        String description = clearCommand.getDescription();

        // Then
        assertEquals(expectedDescription, description);
    }
}