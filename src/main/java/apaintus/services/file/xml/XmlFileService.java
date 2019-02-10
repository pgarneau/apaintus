package apaintus.services.file.xml;

import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.Shape;
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
import java.util.List;

public class XmlFileService implements FileService<List<DrawableShape>, List<DrawableShape>> {
    public XmlFileService() {}
    private Path lastSaveRepository;
    private Path lastLoadRepository;

    @Override
    public void save(List<DrawableShape> shapeList) {
        File file = getSaveFile();

        if (file != null) {
            try {
                JAXBContext context = JAXBContext.newInstance(XmlContract.class);
                Marshaller m = context.createMarshaller();
                m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                // Wrapping our person data.
                XmlContract xmlContract = XmlContractTranslator.translateTo(shapeList);

                // Marshalling and saving XML to the file.
                m.marshal(xmlContract, file);
            } catch (Exception e) { // catches ANY exception
                System.out.println(e);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Could not save data");
                alert.setContentText("Could not save data to file:\n" + file.getPath());

                alert.showAndWait();
            }
        }
    }

    @Override
    public List<DrawableShape> load() {
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
        if(action == "save") {
        	fileChooser.setInitialDirectory(lastSaveRepository.toFile());
        }
        else if(action == "load") {
        	fileChooser.setInitialDirectory(lastLoadRepository.toFile());
        }
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
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
