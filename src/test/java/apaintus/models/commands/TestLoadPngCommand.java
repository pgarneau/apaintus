package apaintus.models.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TestLoadPngCommand {
	@Test
	void executeThenSuccessful() {
		// Given
		SelectCommand selectCommand = mock(SelectCommand.class);
		LoadPngCommand loadPngCommand = new LoadPngCommand(selectCommand);

		// When
		loadPngCommand.execute();

		// Then
		verify(selectCommand, times(1)).execute();
	}

	@Test
	void undoThenSuccessful() {
		// Given
		SelectCommand selectCommand = mock(SelectCommand.class);
		LoadPngCommand loadPngCommand = new LoadPngCommand(selectCommand);

		// When
		loadPngCommand.undo();

		// Then
		verify(selectCommand, times(1)).undo();
	}

	@Test
	void redoThenSuccessful() {
		// Given
		SelectCommand selectCommand = mock(SelectCommand.class);
		LoadPngCommand loadPngCommand = new LoadPngCommand(selectCommand);

		// When
		loadPngCommand.redo();

		// Then
		verify(selectCommand, times(1)).redo();
	}

	@Test
	void getDescriptionThenSuccessful() {
		// Given
		LoadPngCommand loadPngCommand = new LoadPngCommand();

		String expectedDescription = "Load Png";

		// When
		String description = loadPngCommand.getDescription();

		// Then
		assertEquals(expectedDescription, description);
	}
}