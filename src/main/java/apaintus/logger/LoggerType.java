package apaintus.logger;

public enum LoggerType {
    NONE (0),
    CONSOLE (1),
    FILE (2);

    private final int loggerType;

    LoggerType (int loggerType) {
        this.loggerType = loggerType;
    }

    public int getLoggerType() {
        return loggerType;
    }
}
