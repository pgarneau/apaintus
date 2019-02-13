package apaintus.models.commands;

import java.util.Stack;

public class Invoker {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;

    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void execute(Command command) {
        undoStack.push(command);
        redoStack.clear();
        command.execute();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
        }
    }

    public void clear() {
        undoStack.empty();
        redoStack.empty();
    }
}
