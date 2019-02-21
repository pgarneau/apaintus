package apaintus.logger.file;

import apaintus.logger.Logger;
import apaintus.logger.LoggerType;

public class FileLogger extends Logger {
    public FileLogger() {
        super(LoggerType.FILE);
    }

    @Override
    protected void writeMessage(String msg) {
        System.out.println(msg);
    }
}
