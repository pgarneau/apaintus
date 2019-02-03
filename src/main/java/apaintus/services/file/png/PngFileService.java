package apaintus.services.file.png;

import apaintus.services.file.FileService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class PngFileService implements FileService<Image, Image> {
    public PngFileService() {}

    @Override
    public void save(Image image) {
        File file = getSaveFile();

        if (file != null) {
            try {
                ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to save png image: " + e);
            }
        }
    }

    @Override
    public Image load() {
        File file = getLoadFile();

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
    public File getSaveFile() {
        return getFileChooser().showSaveDialog(null);
    }

    @Override
    public File getLoadFile() {
        return getFileChooser().showOpenDialog(null);
    }

    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }
}
