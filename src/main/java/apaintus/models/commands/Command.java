package apaintus.models.commands;

public interface Command {
    void execute();
    void undo();
    void redo();
    String getDescription();
}
