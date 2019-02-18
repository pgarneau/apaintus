package apaintus.views;

import apaintus.controllers.FigureLogController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class FigureListViewCell extends ListCell<String> {
	HBox hBox = new HBox();
	Label shapeName = new Label("(empty)");
	Pane emptyPane = new Pane();
	Button upButton = new Button("^");
	Button downButton = new Button("v");
	String lastItem;

	public FigureListViewCell(FigureLogController figureLogController) {
		super();
		upButton.setFocusTraversable(false);
		downButton.setFocusTraversable(false);
		hBox.getChildren().addAll(shapeName, emptyPane, upButton, downButton);
		HBox.setHgrow(emptyPane, Priority.ALWAYS);

		upButton.setOnMouseClicked(event -> {
			figureLogController.moveShapeUp(lastItem);
		});

		downButton.setOnMouseClicked(event -> {
			figureLogController.moveShapeDown(lastItem);
		});
	}

	@Override
	protected void updateItem(String item, boolean empty) {
		super.updateItem(item, empty);
		setText(null); // No text in label of super class
		if (empty) {
			lastItem = null;
			setGraphic(null);
		} else {
			lastItem = item;
			shapeName.setText(item != null ? item : "<null>");
			setGraphic(hBox);
		}
	}
}