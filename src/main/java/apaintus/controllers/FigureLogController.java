package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

import apaintus.models.ListViewCell;
import apaintus.models.commands.Invoker;
import apaintus.models.commands.SelectCommand;
import apaintus.models.shapes.DrawableShape;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class FigureLogController implements ChildController<Controller> {
	private Controller controller;
	private CanvasController canvasController;
	private Invoker invoker;

	public static final ObservableList<String> observableListFigures = FXCollections.observableArrayList();

	@FXML
	private ListView<String> figureList;
	private List<DrawableShape> shapeList;

	private static final int UP = -1;
	private static final int DOWN = 1;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		this.canvasController = this.controller.getCanvasController();
		invoker = controller.getInvoker();
	}

	@Override
	public void initialize() {
		figureList.setOnMouseClicked(Event -> {
			int indexOfSelectedItem = figureList.getSelectionModel().getSelectedIndex();
			if (indexOfSelectedItem < 0)
				return;
			DrawableShape newActiveShape = shapeList.get(indexOfSelectedItem);
			DrawableShape previousActiveShape = canvasController.getActiveShape();

			invoker.execute(new SelectCommand(canvasController, previousActiveShape, newActiveShape));
		});
	}

	public void updateFigureList(List<DrawableShape> drawnShapes) {
		observableListFigures.clear();
		shapeList = drawnShapes;

		for (DrawableShape shape : shapeList)
			observableListFigures.add(shapeToString(shape));

		figureList.setItems(observableListFigures);

		figureList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new ListViewCell(controller.getFigureLogController());
			}
		});
	}

	public void selectFigureListItem(DrawableShape shape) {
		if(shape != null)
			figureList.getSelectionModel().select(shapeToString(shape));
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

		DrawableShape previousActiveShape = canvasController.getActiveShape();
		invoker.execute(new SelectCommand(canvasController, previousActiveShape, shape));
	}

	private String shapeToString(DrawableShape shape) {
		return shape.getShapeType().toString() + " - " + shape.hashCode();
	}
}
