package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TestDrawImageCommand {
	@Test
	public void testCreateCommand() {
		CanvasController testCanvasController = mock(CanvasController.class);
		Image testImage = mock(Image.class);
		DrawImageCommand test = new DrawImageCommand(testCanvasController, testImage);
		assertNotNull(test);
	}

	@Test
	void testExecute() {
		CanvasController testCanvasController = mock(CanvasController.class);
		Image testImage = mock(Image.class);

		doCallRealMethod().when(testCanvasController).getBaseImage();
		doCallRealMethod().when(testCanvasController).setBaseImage(nullable(Image.class));
		DrawImageCommand test = new DrawImageCommand(testCanvasController, testImage);

		test.execute();

		assertNotNull(testCanvasController.getBaseImage());
	}

	@Test
	void testUndo() {
		CanvasController testCanvasController = mock(CanvasController.class);
		Image testImage = mock(Image.class);

		doCallRealMethod().when(testCanvasController).getBaseImage();
		doCallRealMethod().when(testCanvasController).setBaseImage(nullable(Image.class));
		DrawImageCommand test = new DrawImageCommand(testCanvasController, testImage);

		test.execute();
		test.undo();

		assertNull(testCanvasController.getBaseImage());
	}

	@Test
	void testRedo() {
		CanvasController testCanvasController = mock(CanvasController.class);
		Image testImage = mock(Image.class);

		doCallRealMethod().when(testCanvasController).getBaseImage();
		doCallRealMethod().when(testCanvasController).setBaseImage(nullable(Image.class));
		DrawImageCommand test = new DrawImageCommand(testCanvasController, testImage);

		test.execute();
		test.undo();
		test.redo();

		assertEquals(testCanvasController.getBaseImage(), testImage);
	}
}