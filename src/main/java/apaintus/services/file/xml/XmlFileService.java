package apaintus.services.file.xml;

import apaintus.models.ApplicationPreferences;
import apaintus.models.Preference;
import apaintus.models.nodes.Node;
import apaintus.models.xml.XmlContract;
import apaintus.models.xml.XmlContractTranslator;
import apaintus.services.file.FileService;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class XmlFileService implements FileService<List<Node>, List<Node>> {
	private Path savePath;
	private Path loadPath;

	@Override
	public void save(List<Node> nodeList) {
		File file = getSaveFile();

		if (file != null) {
			try {
				JAXBContext context = JAXBContext.newInstance(XmlContract.class);
				Marshaller m = context.createMarshaller();
				m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				// Wrapping our person data.
				XmlContract xmlContract = XmlContractTranslator.translateTo(nodeList);

				// Marshalling and saving XML to the file.
				m.marshal(xmlContract, file);
			} catch (Exception e) { // catches ANY exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Could not save data");
				alert.setContentText("Could not save data to file:\n" + file.getPath());

				alert.showAndWait();
			}
		}
	}

	@Override
	public List<Node> load() {
		File file = getLoadFile();

		if (file != null) {
			try {
				JAXBContext context = JAXBContext.newInstance(XmlContract.class);
				Unmarshaller um = context.createUnmarshaller();

				// Reading XML from the file and unmarshalling.
				XmlContract xmlContract = (XmlContract) um.unmarshal(file);

				return XmlContractTranslator.translateFrom(xmlContract);
			} catch (Exception e) { // catches ANY exception
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText("Could not load data");
				alert.setContentText("Could not load data from file:\n" + file.getPath());

				alert.showAndWait();
			}
		}

		return new ArrayList<>();
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
		FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
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
