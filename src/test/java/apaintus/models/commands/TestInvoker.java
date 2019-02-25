package apaintus.models.commands;

import apaintus.controllers.ActionLogController;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TestInvoker {
	@Test
	void executeThenSuccessful() {
		// Given
		Invoker invoker = new Invoker();
		ActionLogController actionLogController = mock(ActionLogController.class);
		invoker.setActionLogController(actionLogController);

		SelectCommand selectCommand = mock(SelectCommand.class);
		Stack<Command> expectedUndoStack = new Stack<>();
		expectedUndoStack.push(selectCommand);

		// When
		invoker.execute(selectCommand);

		// Then
		assertEquals(new Stack<>(), invoker.getRedoStack());
		assertEquals(expectedUndoStack, invoker.getUndoStack());
	}

	@Test
	void undoWhenEmptyUndoStackThenDoNothing() {
		// Given
		Invoker invoker = new Invoker();

		SelectCommand selectCommand = mock(SelectCommand.class);

		// When
		invoker.undo();

		// Then
		verify(selectCommand, times(0)).undo();
	}

	@Test
	void undoThenSuccessful() {
		// Given
		Invoker invoker = new Invoker();
		ActionLogController actionLogController = mock(ActionLogController.class);
		invoker.setActionLogController(actionLogController);

		SelectCommand selectCommand = mock(SelectCommand.class);
		Stack<Command> expectedUndoStack = new Stack<>();
		Stack<Command> expectedRedoStack = new Stack<>();
		expectedRedoStack.push(selectCommand);

		// When
		invoker.execute(selectCommand);
		invoker.undo();

		// Then
		assertEquals(expectedRedoStack, invoker.getRedoStack());
		assertEquals(expectedUndoStack, invoker.getUndoStack());
	}

	@Test
	void redoWhenEmptyRedoStackThenDoNothing() {
		// Given
		Invoker invoker = new Invoker();

		SelectCommand selectCommand = mock(SelectCommand.class);

		// When
		invoker.redo();

		// Then
		verify(selectCommand, times(0)).redo();
	}

	@Test
	void redoThenSuccessful() {
		// Given
		Invoker invoker = new Invoker();
		ActionLogController actionLogController = mock(ActionLogController.class);
		invoker.setActionLogController(actionLogController);

		SelectCommand selectCommand = mock(SelectCommand.class);
		Stack<Command> expectedUndoStack = new Stack<>();
		Stack<Command> expectedRedoStack = new Stack<>();
		expectedUndoStack.push(selectCommand);

		// When
		invoker.execute(selectCommand);
		invoker.undo();
		invoker.redo();

		// Then
		assertEquals(expectedRedoStack, invoker.getRedoStack());
		assertEquals(expectedUndoStack, invoker.getUndoStack());
	}

	@Test
	void clearThenSuccessful() {
		// Given
		Invoker invoker = new Invoker();
		ActionLogController actionLogController = mock(ActionLogController.class);
		invoker.setActionLogController(actionLogController);

		SelectCommand selectCommand = mock(SelectCommand.class);
		Stack<Command> expectedUndoStack = new Stack<>();
		Stack<Command> expectedRedoStack = new Stack<>();

		// When
		invoker.execute(selectCommand);
		invoker.undo();
		invoker.redo();
		invoker.clear();

		// Then
		assertEquals(expectedRedoStack, invoker.getRedoStack());
		assertEquals(expectedUndoStack, invoker.getUndoStack());
	}
}
