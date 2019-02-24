package apaintus.models.commands;

import java.util.Stack;

import apaintus.controllers.ActionLogController;
import apaintus.logger.Logger;
import apaintus.logger.LoggerFactory;

public class Invoker {
    private final Stack<Command> undoStack;
    private final Stack<Command> redoStack;
    private final Logger logger;
    
    private ActionLogController actionLogController;

    public Invoker() {
        undoStack = new Stack<>();
        redoStack = new Stack<>();

        logger = LoggerFactory.createLogger();
    }

    public void execute(Command command) {
        undoStack.push(command);
        redoStack.clear();
        command.execute();
        log(command.getDescription());
        actionLogController.updateActionList();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            log("Undo " + command.getDescription());
            actionLogController.updateActionList();
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Command command = redoStack.pop();
            command.redo();
            undoStack.push(command);
            log("Redo" + command.getDescription());
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

    private void log(String message) {
        logger.message(message);
    }
}
