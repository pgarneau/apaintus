package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.nodes.Node;
import apaintus.models.nodes.NodeType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class TestDrawNodeCommand {
	@Test
	void bonjour() {
		assertEquals(true, true);
	}
//	void testCreateDrawShapeCommand() {
//		CanvasController testCanvasController = mock(CanvasController.class);
//		Node testShape = mock(Node.class);
//		SelectCommand testSelectCommand = mock(SelectCommand.class);
//
//		doCallRealMethod().when(testShape).getNodeType();
//
//		DrawNodeCommand test = new DrawNodeCommand(testCanvasController, testShape, testSelectCommand);
//
//		assertNotNull(test);
//	}
//
//	@Test
//	void testExecute() {
//		CanvasController testCanvasController = mock(CanvasController.class);
//		Node testShape = mock(Node.class);
//		SelectCommand testSelectCommand = mock(SelectCommand.class);
//
//		doCallRealMethod().when(testCanvasController).saveDrawLayer();
//
//		when(testShape.getNodeType()).thenReturn(NodeType.RECTANGLE);
//
//		doAnswer((i) -> {
//			return null;
//		}).when(testSelectCommand).execute();
//
//		DrawNodeCommand test = new DrawNodeCommand(testCanvasController, testShape, testSelectCommand);
//		test.execute();
//
//		//Need to check later what assertion we do want to do.
//		//assertFalse(testCanvasController.getDrawnShapes().isEmpty());
//	}
//
//	@Test
//	void testUndo() {
//		CanvasController testCanvasController = mock(CanvasController.class);
//		Node testShape = mock(Node.class);
//		SelectCommand testSelectCommand = mock(SelectCommand.class);
//
//		doCallRealMethod().when(testCanvasController).saveDrawLayer();
//
//		when(testShape.getNodeType()).thenReturn(NodeType.RECTANGLE);
//
//		doAnswer((i) -> {
//			return null;
//		}).when(testSelectCommand).execute();
//
//		DrawNodeCommand test = new DrawNodeCommand(testCanvasController, testShape, testSelectCommand);
//		test.execute();
//		test.undo();
//	}
//
//	@Test
//	void testRedo() {
//		CanvasController testCanvasController = mock(CanvasController.class);
//		Node testShape = mock(Node.class);
//		SelectCommand testSelectCommand = mock(SelectCommand.class);
//
//		doCallRealMethod().when(testCanvasController).saveDrawLayer();
//
//		when(testShape.getNodeType()).thenReturn(NodeType.RECTANGLE);
//
//		doAnswer((i) -> {
//			return null;
//		}).when(testSelectCommand).execute();
//
//		DrawNodeCommand test = new DrawNodeCommand(testCanvasController, testShape, testSelectCommand);
//		test.execute();
//		test.undo();
//		test.redo();
//	}
}
