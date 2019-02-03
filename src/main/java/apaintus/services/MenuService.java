package apaintus.services;

import apaintus.services.file.FileService;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class MenuService {
    public MenuService() {}

    public boolean saveRequested() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().add(ButtonType.YES);
        alert.setTitle("Save");
        alert.setHeaderText("Save");
        alert.setContentText(
                "Are you sure that you want to close this file without saving ?\nUnsaved changes will be lost.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES) {
            return false;
        } else {
            return true;
        }
    }
}
