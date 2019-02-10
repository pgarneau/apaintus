package apaintus.services.file.png;

import apaintus.services.file.FileService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class PngFileService implements FileService<Image, Image> {
    public PngFileService() {}
    private Path lastSaveRepository;
    private Path lastLoadRepository;

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
    	File file = getFileChooser("save").showSaveDialog(null);
    	lastSaveRepository = file.getParentFile().toPath();
        return file;
    }

    @Override
    public File getLoadFile() {
    	File file = getFileChooser("load").showOpenDialog(null);
    	lastLoadRepository = file.getParentFile().toPath();
        return file;
    }

    private FileChooser getFileChooser(String action) {
        FileChooser fileChooser = new FileChooser();
        if(action == "save" && lastSaveRepository != null) {
        	fileChooser.setInitialDirectory(lastSaveRepository.toFile());
        }
        else if(action == "load" && lastLoadRepository != null) {
        	fileChooser.setInitialDirectory(lastLoadRepository.toFile());
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }
    
    @Override
    public Path getLastSaveRepository() {
    	return lastSaveRepository;
    }
    
    @Override
    public Path getLastLoadRepository() {
    	return lastLoadRepository;
    }
    
    @Override
    public void setLastSaveRepository(Path lastSaveRepository) {
    	this.lastSaveRepository = lastSaveRepository;
    }
    
    @Override
    public void setLastLoadRepository(Path lastLoadRepository) {
    	this.lastLoadRepository = lastLoadRepository;
    }
}
