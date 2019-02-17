package apaintus.controllers;

import apaintus.models.shapes.Node;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.util.List;

import apaintus.models.commands.Invoker;
import apaintus.models.commands.SelectCommand;
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
	private List<Node> nodeList;

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

			invoker.execute(new SelectCommand(canvasController, canvasController.getActiveNode(), nodeList.get(indexOfSelectedItem)));
		});
	}

	public void updateFigureList(List<Node> nodeList) {
		observableListFigures.clear();
		this.nodeList = nodeList;


		for (int index = 0; index < nodeList.size(); index++)
			observableListFigures.add((index + 1) + ". " + nodeToString(nodeList.get(index)));

		figureList.setItems(observableListFigures);
		figureList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
			@Override
			public ListCell<String> call(ListView<String> param) {
				return new FigureListViewCell(controller.getFigureLogController());
			}
		});
	}

	public void selectFigureListItem(Node node) {
		if (node != null) {
			for (int index = 0; index < nodeList.size(); index++)
				if (node == nodeList.get(index))
					figureList.getSelectionModel().select(index);
		}
	}

	public void moveNodeUp(String shapeName) {
		moveNode(UP, shapeName);
	}

	public void moveNodeDown(String shapeName) {
		moveNode(DOWN, shapeName);
	}

	private void moveNode(int direction, String shapeName) {
		int index = figureList.getItems().indexOf(shapeName);
		int newIndex = index + direction;
		Node node = nodeList.get(index);

		if (newIndex < 0 || newIndex >= figureList.getItems().size())
			return;

		nodeList.remove(node);
		nodeList.add(newIndex, node);
		updateFigureList(nodeList);
		selectFigureListItem(node);

		invoker.execute(new SelectCommand(canvasController, canvasController.getActiveNode(), node));
	}

	private String nodeToString(Node node) {
		return node.getNodeType().toString() + " - " + node.hashCode();
	}
}
