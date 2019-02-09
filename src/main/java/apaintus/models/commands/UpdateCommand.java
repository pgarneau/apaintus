package apaintus.models.commands;

public class UpdateCommand implements Command {
    public UpdateCommand() {}

    @Override
    public void execute() {}

    @Override
    public void undo() {}

    @Override
    public void redo() {}

    @Override
    public Command getCommand(int index) {
        return null;
    }
}
