package apaintus.services.file.png;

import apaintus.services.file.FileService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PngFileService implements FileService<Canvas, Image> {
    public PngFileService() {}

    @Override
    public void save(Canvas canvas) {
        File file = getFile();

        if (file != null) {
            try {
                // Setup snapshot
                SnapshotParameters snapshotParameters = new SnapshotParameters();
                snapshotParameters.setFill(Color.TRANSPARENT);

                Image snapshot = canvas.snapshot(snapshotParameters, null);

                ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save png image: " + e);
            }
        }
    }

    @Override
    public Image load() {
        File file = getFile();

        if (file != null) {
            try {
                String imagePath = "file:" + file;

                return new Image(imagePath);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Failed to open png image: " + e);
            }
        }

        return null;
    }

    @Override
    public File getFile() {
        // Set extension filter
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(null);

        return file;
    }
}
