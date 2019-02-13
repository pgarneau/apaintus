package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import javafx.scene.image.Image;

import java.util.Optional;

public class DrawImageCommand implements Command {
    private static final String DESCRIPTION = "Draw Image";

    private CanvasController canvasController;
    private Optional<Image> oldImage;
    private Image image;

    public DrawImageCommand(CanvasController canvasController, Image image) {
        this.canvasController = canvasController;
        this.image = image;

        oldImage = Optional.ofNullable(this.canvasController.getBaseImage());
    }

    @Override
    public void execute() {
        canvasController.setBaseImage(image);
        canvasController.drawImage(image);
    }

    @Override
    public void undo() {
        canvasController.setBaseImage(oldImage.orElse(null));
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        canvasController.setBaseImage(image);
        canvasController.redrawCanvas();
    }

    @Override
    public String getDescription() {
        return DESCRIPTION;
    }

}
