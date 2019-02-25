package apaintus.models.commands;

import apaintus.controllers.AttributeController;
import apaintus.controllers.CanvasController;
import apaintus.controllers.ToolBarController;
import apaintus.models.Attribute;
import apaintus.models.Point;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import apaintus.models.nodes.shapes.Rectangle;
import apaintus.util.ReflectionUtil;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestUpdateCommand {
	@Test
	void executeWhenNewXCoordinateThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		when(node.getCoordinates()).thenReturn(new Point(0, 0));

		double value = 10;
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.COORDINATE_X, null, value);

		// When
		updateCommand.execute();

		// Then
		verify(node, times(1)).setCoordinates(any(Point.class));
	}

	@Test
	void executeWhenNewYCoordinateThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		when(node.getCoordinates()).thenReturn(new Point(0, 0));

		double value = 10;
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.COORDINATE_Y, null, value);

		// When
		updateCommand.execute();

		// Then
		verify(node, times(1)).setCoordinates(any(Point.class));
	}

	@Test
	void executeWhenNewWidthThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double value = 10;
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.WIDTH, null, value);

		// When
		updateCommand.execute();

		// Then
		verify(node, times(1)).setWidth(value);
	}

	@Test
	void executeWhenNewHeightThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double value = 10;
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.HEIGHT, null, value);

		// When
		updateCommand.execute();

		// Then
		verify(node, times(1)).setHeight(value);
	}

	@Test
	void executeWhenNewOrientationThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double value = 10;
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.ORIENTATION, null, value);

		// When
		updateCommand.execute();

		// Then
		verify(node, times(1)).setOrientation(value);
	}

	@Test
	void executeWhenNewFillColorThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Rectangle node = mock(Rectangle.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		String value = "#0000000";
		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.FILL_COLOR, null, value);

		// When
		updateCommand.execute();

		// Then
		assertEquals(value, ReflectionUtil.get(node, "fillColor"));
	}

	@Test
	void undoThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		ToolBarController toolBarController = mock(ToolBarController.class);
		AttributeController attributeController = mock(AttributeController.class);
		when(canvasController.getToolBarController()).thenReturn(toolBarController);
		when(canvasController.getAttributeController()).thenReturn(attributeController);

		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double oldValue = 10;
		double value = 12;

		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.ORIENTATION, oldValue, value);

		// When
		updateCommand.undo();

		// Then
		verify(node, times(1)).setOrientation(oldValue);
	}

	@Test
	void redoThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		ToolBarController toolBarController = mock(ToolBarController.class);
		AttributeController attributeController = mock(AttributeController.class);
		when(canvasController.getToolBarController()).thenReturn(toolBarController);
		when(canvasController.getAttributeController()).thenReturn(attributeController);

		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double oldValue = 10;
		double value = 12;

		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.ORIENTATION, oldValue, value);

		// When
		updateCommand.redo();

		// Then
		verify(node, times(1)).setOrientation(value);
	}

	@Test
	void getDescriptionThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);

		Node node = mock(Node.class);
		when(node.getNodeType()).thenReturn(NodeType.RECTANGLE);
		double oldValue = 10;
		double value = 12;

		String expectedDescription = "Updated " + NodeType.RECTANGLE.toString() + " " + Attribute.ORIENTATION.toString();

		UpdateCommand updateCommand = new UpdateCommand(canvasController, node, Attribute.ORIENTATION, oldValue, value);

		// When
		String description = updateCommand.getDescription();

		// Then
		assertEquals(expectedDescription, description);
	}
}
