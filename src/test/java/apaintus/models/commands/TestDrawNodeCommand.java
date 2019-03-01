package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import apaintus.models.nodes.shapes.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestDrawNodeCommand {
	@Test
	void executeThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		SelectCommand selectCommand = mock(SelectCommand.class);
		Node node = mock(Rectangle.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		DrawNodeCommand drawNodeCommand = new DrawNodeCommand(canvasController, node, selectCommand);

		// When
		drawNodeCommand.execute();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void undoThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		SelectCommand selectCommand = mock(SelectCommand.class);
		Node node = mock(Rectangle.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		DrawNodeCommand drawNodeCommand = new DrawNodeCommand(canvasController, node, selectCommand);

		// When
		drawNodeCommand.undo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void redoThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		SelectCommand selectCommand = mock(SelectCommand.class);
		Node node = mock(Rectangle.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		DrawNodeCommand drawNodeCommand = new DrawNodeCommand(canvasController, node, selectCommand);

		// When
		drawNodeCommand.redo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void getDescriptionThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		SelectCommand selectCommand = mock(SelectCommand.class);
		Node node = mock(Rectangle.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		DrawNodeCommand drawNodeCommand = new DrawNodeCommand(canvasController, node, selectCommand);

		String expectedDescription = "Draw " + NodeType.RECTANGLE.toString();

		// When
		String description = drawNodeCommand.getDescription();

		// Then
		assertEquals(expectedDescription, description);
	}
}
