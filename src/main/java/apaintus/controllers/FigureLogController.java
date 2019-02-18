package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

import apaintus.models.commands.Invoker;
import apaintus.models.commands.SelectCommand;
import apaintus.models.shapes.DrawableShape;
import apaintus.views.FigureListViewCell;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FigureLogController implements ChildController<Controller> {
	public static final ObservableList<String> observableListFigures = FXCollections.observableArrayList();
	private static final int UP = -1;
	private static final int DOWN = 1;
	
	@FXML
	private ListView<String> figureList;
	
	private Controller controller;
	private CanvasController canvasController;
	private Invoker invoker;
	private List<DrawableShape> shapeList;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		canvasController = controller.getCanvasController();
		invoker = controller.getInvoker();
	}

	@Override
	public void initialize() {
		figureList.setOnMouseClicked(event -> {
			int indexOfSelectedItem = figureList.getSelectionModel().getSelectedIndex();

			if (indexOfSelectedItem < 0)
				return;

			invoker.execute(new SelectCommand(canvasController, canvasController.getActiveShape(), shapeList.get(indexOfSelectedItem)));
		});
	}

	public void updateFigureList(List<DrawableShape> drawnShapes) {
		observableListFigures.clear();
		shapeList = drawnShapes;

		for (int index = 0; index < shapeList.size(); index++)
			observableListFigures.add((index + 1) + ". " + shapeToString(shapeList.get(index)));

		figureList.setItems(observableListFigures);
		figureList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new FigureListViewCell(controller.getFigureLogController());
			}
		});
	}

	public void selectFigureListItem(DrawableShape shape) {
		int index = 0;

		if (shape != null) {
			for (index = 0; index < shapeList.size(); index++)
				if (shape == shapeList.get(index))
					figureList.getSelectionModel().select(index);
		}
	}

	public void moveShapeUp(String shapeName) {
		moveShape(UP, shapeName);
	}

	public void moveShapeDown(String shapeName) {
		moveShape(DOWN, shapeName);
	}

	private void moveShape(int direction, String shapeName) {
		int index = figureList.getItems().indexOf(shapeName);
		int newIndex = index + direction;
		DrawableShape shape = shapeList.get(index);

		if (newIndex < 0 || newIndex >= figureList.getItems().size())
			return;

		shapeList.remove(shape);
		shapeList.add(newIndex, shape);
		updateFigureList(shapeList);
		selectFigureListItem(shape);

		invoker.execute(new SelectCommand(canvasController, canvasController.getActiveShape(), shape));
	}

	private String shapeToString(DrawableShape shape) {
		return shape.getShapeType().toString() + " - " + shape.hashCode();
	}
}
