package apaintus.views;

import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ActionListViewCell extends ListCell<String> {
	HBox hBox = new HBox();
	Label action = new Label("(empty)");
	Pane emptyPane = new Pane();
	String lastItem;

	public ActionListViewCell() {
		super();
		hBox.getChildren().addAll(action, emptyPane);
		HBox.setHgrow(emptyPane, Priority.ALWAYS);
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
			action.setText(item != null ? item : "<null>");
			setGraphic(hBox);
		}
	}
}