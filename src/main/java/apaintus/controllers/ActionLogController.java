package apaintus.controllers;

import java.util.List;

import apaintus.models.commands.Command;
import apaintus.models.commands.Invoker;
import apaintus.views.ActionListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ActionLogController implements ChildController<Controller> {
	private Controller controller;
	private CanvasController canvasController;
	private Invoker invoker;

	public static final ObservableList<String> observableListActions = FXCollections.observableArrayList();

	@FXML
	private ListView<String> actionList;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		canvasController = controller.getCanvasController();
		invoker = controller.getInvoker();
	}

	@Override
	public void initialize() {
		actionList.setOnMouseClicked(event -> {
			int indexOfSelectedItem = actionList.getSelectionModel().getSelectedIndex();

			if (indexOfSelectedItem < 0)
				return;

			int numberOfRedo = indexOfSelectedItem + 1 - invoker.getUndoStack().size();
			int numberOfUndo = invoker.getUndoStack().size() - 1 - indexOfSelectedItem;

			if (numberOfRedo > 0) {
				for (int index = 0; index < numberOfRedo; index++) {
					invoker.redo();
					System.out.println("Index of redo: " + index);
				}
			}

			if (numberOfUndo > 0) {
				for (int index = 0; index < numberOfUndo; index++) {
					invoker.undo();
					System.out.println("Index of undo: " + index);
				}
			}
		});
	}

	public void updateActionList() {
		observableListActions.clear();
		List<Command> undoStack = invoker.getUndoStack();
		List<Command> redoStack = invoker.getRedoStack();

		for (int index = 0; index < undoStack.size(); index++)
			observableListActions.add((index + 1) + ". " + undoStack.get(index).getDescription());

		for (int index = 0; index < (redoStack.size()); index++)
			observableListActions.add((index + 1 + undoStack.size()) + ". UNDONE "
					+ redoStack.get(redoStack.size() - 1 - index).getDescription());

		actionList.setItems(observableListActions);

		actionList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ActionListViewCell();
			}
		});
	}
}
