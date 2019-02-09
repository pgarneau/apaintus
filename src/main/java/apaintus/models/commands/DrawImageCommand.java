package apaintus.models.commands;

import apaintus.controllers.CanvasController;
import apaintus.models.commands.Command;
import apaintus.models.shapes.DrawableShape;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Optional;

public class DrawImageCommand implements Command {
    private CanvasController canvasController;
    private Optional<Image> oldImage;
    private Image newImage;

    public DrawImageCommand(CanvasController canvasController, Image newImage) {
        this.canvasController = canvasController;
        this.oldImage = Optional.ofNullable(this.canvasController.getBaseImage());
        this.newImage = newImage;
    }

    @Override
    public void execute() {
        canvasController.setBaseImage(newImage);
        canvasController.drawImage(newImage);
    }

    @Override
    public void undo() {
        canvasController.setBaseImage(oldImage.orElse(null));
        canvasController.redrawCanvas();
    }

    @Override
    public void redo() {
        canvasController.setBaseImage(newImage);
        canvasController.redrawCanvas();
    }

    @Override
    public Command getCommand(int index) {
        return null;
    }
}
