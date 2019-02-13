package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

import apaintus.models.shapes.DrawableShape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FigureLogController implements ChildController<Controller> {
	private Controller controller;
	private CanvasController canvasController;

	public static final ObservableList<DrawableShape> observableListFigures = FXCollections.observableArrayList();

	@FXML
	private ListView<DrawableShape> figureList;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		this.canvasController = this.controller.getCanvasController();
	}

	@Override
	public void initialize() {
		figureList.setOnMouseClicked(Event ->{
			DrawableShape shape = figureList.getSelectionModel().getSelectedItem();
			canvasController.selectShape(shape);
			canvasController.redrawCanvas();
		});
	}

	public void updateFigureList(List<DrawableShape> drawnShapes) {
		observableListFigures.clear();
		
		for (DrawableShape shape : drawnShapes)
			observableListFigures.add(shape);
		
		figureList.setItems(observableListFigures);
		
		// Implement this method to enable the modification of the shown text in the listView
		// Add the CellFactory to the ListView
		//figureList.setCellFactory(new Callback<ListView<DrawableShape>, javafx.scene.control.ListCell<DrawableShape>>());
	}
	
	public void selectFigureListItem(DrawableShape shape) {
		figureList.getSelectionModel().select(shape);
	}
}
