package apaintus.controllers;

import java.util.List;

import apaintus.models.commands.Command;
import apaintus.models.commands.Invoker;
import apaintus.models.shapes.DrawableShape;
import apaintus.views.ListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

public class ActionLogController  implements ChildController<Controller>{
	private Controller controller;
	private CanvasController canvasController;
	private Invoker invoker;
	
	public static final ObservableList<String> observableListActions = FXCollections.observableArrayList();
	
	@FXML
	private ListView<String> actionList;
	private List<Command> commandList;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		canvasController = controller.getCanvasController();
		invoker = controller.getInvoker();
	}

	@Override
	public void initialize() {
		
	}
	
	public void updateFigureList(List<Command> undoCommand) {
		observableListActions.clear();
		commandList = undoCommand;

		//for (int index = 0; index < commandList.size(); index++)
		//	observableListActions.add((index + 1) + ". " + shapeToString(commandList.get(index)));

		actionList.setItems(observableListActions);

		//actionList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			//@Override
			//public ListCell<String> call(ListView<String> param) {
			//	return new ListViewCell(controller.getActionLogController());
			//}
		//});
	}
	
}
