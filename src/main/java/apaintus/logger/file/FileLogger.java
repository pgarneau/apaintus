package apaintus.logger.file;

import apaintus.logger.Logger;
import apaintus.logger.LoggerType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class FileLogger extends Logger {
    private static final String PATH = "events/";

    private Path eventsFile;

    public FileLogger() {
        super(LoggerType.FILE);
        createFile();
    }

    @Override
    protected void writeMessage(String msg) {
        try {
            Files.write(eventsFile, msg.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
        } catch (IOException e) {
        }
    }

    private void createFile() {
        String fileName = "Session_" + ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".txt";
        eventsFile = Paths.get(PATH + fileName);

        try {
            Files.createDirectories(eventsFile.getParent());
        } catch (IOException e) {
        }
    }
}
