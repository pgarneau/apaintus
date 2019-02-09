package apaintus.services.file;

import java.io.File;

public interface FileService<T, E> {
	void save(T object);
	E load();
	File getSaveFile();
	File getLoadFile();
}
