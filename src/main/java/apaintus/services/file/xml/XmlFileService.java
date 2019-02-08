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
import java.util.List;

public class XmlFileService implements FileService<List<DrawableShape>, List<DrawableShape>> {
    public XmlFileService() {}

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
        return getFileChooser().showSaveDialog(null);
    }

    @Override
    public File getLoadFile() {
        return getFileChooser().showOpenDialog(null);
    }

    private FileChooser getFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("xml files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        return fileChooser;
    }
}
