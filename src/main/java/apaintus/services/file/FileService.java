package apaintus.services.file;

import javafx.stage.FileChooser;

import java.io.File;

public interface FileService<T, E> {
	void save(T object);
	E load();
	File getFile();
}
