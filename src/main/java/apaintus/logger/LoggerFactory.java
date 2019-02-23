package apaintus.logger;

import apaintus.logger.console.ConsoleLogger;
import apaintus.logger.file.FileLogger;

public class LoggerFactory {
    private LoggerFactory() {}

    public static Logger createLogger() {
        Logger logger = new ConsoleLogger();
        logger.setNext(new FileLogger());

        return logger;
    }
}
