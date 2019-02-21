package apaintus.logger;

public abstract class Logger {
    private static int verboseLevel;

    private LoggerType loggerType;
    private Logger next;

    protected Logger(LoggerType loggerType) {
        this.loggerType = loggerType;
    }

    public void message(String msg) {
        if ((verboseLevel & loggerType.getLoggerType()) != 0) {
            writeMessage(msg);
        }
        if (next != null) {
            next.message(msg);
        }
    }

    public Logger setNext(Logger next) {
        this.next = next;
        return this;
    }

    public static void setVerboseLevel(int level) {
        verboseLevel = level;
    }

    abstract protected void writeMessage(String msg);
}
