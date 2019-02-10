package apaintus.services.file;

import java.io.File;
import java.nio.file.Path;

public interface FileService<T, E> {
	void save(T object);
	E load();
	File getSaveFile();
	File getLoadFile();
	Path getLastSaveRepository();
	void setLastSaveRepository(Path LastSaveRepository);
	Path getLastLoadRepository();
	void setLastLoadRepository(Path LastLoadRepository);
}
