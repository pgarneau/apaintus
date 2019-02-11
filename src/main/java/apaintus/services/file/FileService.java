package apaintus.services.file;

import java.io.File;

import apaintus.models.ApplicationPreferences;

public interface FileService<T, E> {
	void save(T object);
	E load();
	File getSaveFile();
	File getLoadFile();
	void setPreferences(ApplicationPreferences applicationPreferences);
}
