package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestSelectCommand {
	@Test
	void executeWhenPreviousNodeExistsThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node previousNode = mock(Node.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, previousNode, node);

		// When
		selectCommand.execute();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
		assertEquals(false, previousNode.isSelected());
	}

	@Test
	void executeWhenNoPreviousNodeThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, null, node);

		// When
		selectCommand.execute();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void undoWhenPreviousNodeExistsThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node previousNode = mock(Node.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, previousNode, node);

		// When
		selectCommand.undo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
		verify(previousNode, times(1)).setSelected(true);
	}

	@Test
	void undoWhenNoPreviousNodeThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, null, node);

		// When
		selectCommand.undo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void redoWhenPreviousNodeExistsThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node previousNode = mock(Node.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, previousNode, node);

		// When
		selectCommand.redo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
		assertEquals(false, previousNode.isSelected());
	}

	@Test
	void redoWhenNoPreviousNodeThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, null, node);

		// When
		selectCommand.redo();

		// Then
		verify(canvasController, times(1)).redrawCanvas();
	}

	@Test
	void getDescriptionThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		SelectCommand selectCommand = new SelectCommand(canvasController, null, node);

		String expectedDescription = "Selected RECTANGLE";

		// When
		String description = selectCommand.getDescription();

		// Then
		assertEquals(expectedDescription, description);
	}
}
