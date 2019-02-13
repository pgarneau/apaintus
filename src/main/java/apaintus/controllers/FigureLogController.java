package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FigureLogController implements ChildController<Controller>{
	private Controller controller;
	private CanvasController canvasController;
	
	@FXML
	private ListView snapGrid;

	@Override
	public void injectParentController(Controller controller) {
        this.controller = controller;
        this.canvasController = this.controller.getCanvasController();
	}

	@Override
	public void initialize() {
		// TODO Auto-generated method stub
		
	}

}
