package apaintus.services.file.png;

import apaintus.models.ApplicationPreferences;
import apaintus.models.Preference;
import apaintus.services.file.FileService;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PngFileService implements FileService<Image, Image> {
	private static final Logger LOGGER = LogManager.getLogger(PngFileService.class);
	private Path savePath;
	private Path loadPath;

	@Override
	public void save(Image image) {
		File file = getSaveFile();

		if (file != null) {
			try {
				ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
			} catch (IOException e) {
			    LOGGER.error(e.getMessage());
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
			    LOGGER.error(e.getMessage());
			}
		}

		return null;
	}

	@Override
	public File getSaveFile() {
		File file = getFileChooser("save").showSaveDialog(null);
		savePath = file.getParentFile().toPath();
		ApplicationPreferences.getInstance().setPreference(Preference.SAVE_PATH, savePath.toString());
		return file;
	}

	@Override
	public File getLoadFile() {
		File file = getFileChooser("load").showOpenDialog(null);
		loadPath = file.getParentFile().toPath();
		ApplicationPreferences.getInstance().setPreference(Preference.LOAD_PATH, loadPath.toString());
		return file;
	}

	private FileChooser getFileChooser(String action) {
		FileChooser fileChooser = new FileChooser();

		if (action == "save" && savePath != null) {
			fileChooser.setInitialDirectory(savePath.toFile());
		} else if (action == "load" && loadPath != null) {
			fileChooser.setInitialDirectory(loadPath.toFile());
		}

		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
		fileChooser.getExtensionFilters().add(extensionFilter);

		return fileChooser;
	}

	@Override
	public void setPreferences(ApplicationPreferences applicationPreferences) {
		String savePath = applicationPreferences.getPreference(Preference.SAVE_PATH);
		String loadPath = applicationPreferences.getPreference(Preference.LOAD_PATH);

		if (savePath != null) {
			this.savePath = Paths.get(savePath);
			applicationPreferences.setPreference(Preference.SAVE_PATH, savePath);
		}
		if (loadPath != null) {
			this.loadPath = Paths.get(loadPath);
			applicationPreferences.setPreference(Preference.LOAD_PATH, loadPath);
		}
	}
}
