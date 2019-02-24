package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.shapes.Rectangle;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TestClearCommand {
    @Test
    public void executeThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        ClearCommand clearCommand = new ClearCommand(canvasController);

        // When
        clearCommand.execute();

        // Then
        verify(canvasController, times(1)).clearCanvas();
    }

    @Test
    public void undoWhenExecuteWasDoneThenSuccessful() {
        // Given
        List<Node> expectedNodeList = new ArrayList<>();
        expectedNodeList.add(mock(Rectangle.class));

        CanvasController canvasController = mock(CanvasController.class);
        when(canvasController.getNodeList()).thenReturn(expectedNodeList);

        ClearCommand clearCommand = new ClearCommand(canvasController);

        // When
        clearCommand.execute();
        clearCommand.undo();

        // Then
        assertEquals(expectedNodeList, canvasController.getNodeList());
    }

    @Test
    public void redoThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        ClearCommand clearCommand = new ClearCommand(canvasController);

        // When
        clearCommand.redo();

        // Then
        verify(canvasController, times(1)).clearCanvas();
    }

    @Test
    public void getDescriptionThenSuccessful() {
        // Given
        CanvasController canvasController = mock(CanvasController.class);
        ClearCommand clearCommand = new ClearCommand(canvasController);

        String expectedDescription = "Clear Canvas";

        // When
        String description = clearCommand.getDescription();

        // Then
        assertEquals(expectedDescription, description);
    }
}