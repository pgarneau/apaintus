package apaintus.models.commands;

import java.util.List;

public class LoadPngCommand implements Command {
    private List<Command> commandList;

    public LoadPngCommand(Command... commands) {
        commandList = List.of(commands);
    }

    @Override
    public void execute() {
        for (Command command : commandList) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (Command command : commandList) {
            command.undo();
        }
    }

    @Override
    public void redo() {
        for (Command command : commandList) {
            command.redo();
        }
    }

    @Override
    public Command getCommand(int index) {
        return commandList.get(index);
    }
}
