package apaintus.models.commands;

import java.util.Stack;

import apaintus.controllers.ActionLogController;

public class Invoker {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    
    private ActionLogController actionLogController;

    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    public void execute(Command command) {
        undoStack.push(command);
        redoStack.clear();
        command.execute();
        actionLogController.updateActionList();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            actionLogController.updateActionList();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            actionLogController.updateActionList();
        }
    }

    public void clear() {
        undoStack.empty();
        redoStack.empty();
        actionLogController.updateActionList();
    }
    
    public void setActionLogController(ActionLogController actionLogController) {
    	this.actionLogController = actionLogController;
    }
    
    public Stack<Command> getUndoStack(){
    	return undoStack;
    }
    
    public Stack<Command> getRedoStack(){
    	return redoStack;
    }
}
