package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestDrawImageCommand {
	@Test
	void executeThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Image image = mock(Image.class);
		DrawImageCommand drawImageCommand = new DrawImageCommand(canvasController, image);

		// When
		drawImageCommand.execute();

		// Then
		verify(canvasController, times(1)).drawImage(image);
	}

	@Test
	void undoWhenNullOldImageThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Image image = mock(Image.class);
		DrawImageCommand drawImageCommand = new DrawImageCommand(canvasController, image);

		// When
		drawImageCommand.execute();
		drawImageCommand.undo();

		// Then
		verify(canvasController, times(1)).setBaseImage(null);
	}

	@Test
	void undoWhenExistingOldImageThenSuccessful() {
		// Given
		Image image = mock(Image.class);
		Image oldImage = mock(Image.class);
		CanvasController canvasController = mock(CanvasController.class);
		when(canvasController.getBaseImage()).thenReturn(oldImage);
		DrawImageCommand drawImageCommand = new DrawImageCommand(canvasController, image);

		// When
		drawImageCommand.undo();

		// Then
		verify(canvasController, times(1)).setBaseImage(oldImage);
	}

	@Test
	void redoThenSuccessful() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Image image = mock(Image.class);
		DrawImageCommand drawImageCommand = new DrawImageCommand(canvasController, image);

		// When
		drawImageCommand.redo();

		// Then
		verify(canvasController, times(1)).setBaseImage(image);
	}

	@Test
	void getDescription() {
		// Given
		CanvasController canvasController = mock(CanvasController.class);
		Image image = mock(Image.class);
		DrawImageCommand drawImageCommand = new DrawImageCommand(canvasController, image);

		String expectedDescription = "Draw Image";

		// When
		String description = drawImageCommand.getDescription();

		// Then
		assertEquals(expectedDescription, description);
	}
}