package apaintus.logger;

import apaintus.logger.console.ConsoleLogger;
import apaintus.logger.file.FileLogger;

public class LoggerFactory {
    private LoggerFactory() {}

    public static Logger createLogger() {
        return new ConsoleLogger()
                .setNext(new FileLogger());
    }
}
