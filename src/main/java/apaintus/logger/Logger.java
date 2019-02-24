package apaintus.logger;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Logger {
    private static int verboseLevel;

    private LoggerType loggerType;
    private Logger next;

    protected Logger(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public void message(String msg) {
        if ((verboseLevel & loggerType.getLoggerType()) != 0) {
            writeMessage(ZonedDateTime.now().format(DateTimeFormatter.RFC_1123_DATE_TIME) + " " + msg + "\n");
        }

        if (next != null) {
            next.message(msg);
        }
    }

    public Logger setNext(Logger next) {
        this.next = next;
        return next;
    }

    public static void setVerboseLevel(int level) {
        verboseLevel = level;
    }

    abstract protected void writeMessage(String msg);
}
