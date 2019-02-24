package apaintus.logger.console;

import apaintus.logger.Logger;
import apaintus.logger.LoggerType;

public class ConsoleLogger extends Logger {
    public ConsoleLogger() {
        super(LoggerType.CONSOLE);
    }

    @Override
    protected void writeMessage(String msg) {
        System.out.println(msg);
    }
}
