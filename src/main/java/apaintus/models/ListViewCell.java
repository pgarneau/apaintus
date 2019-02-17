package apaintus.models;

import apaintus.controllers.FigureLogController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class ListViewCell extends ListCell<String> {
    HBox hBox = new HBox();
    Label label = new Label("(empty)");
    Pane emptyPane = new Pane();
    Button upButton = new Button("^");
    Button downButton = new Button("v");
    String lastItem;

    public ListViewCell(FigureLogController figureLogController) {
        super();
        upButton.setFocusTraversable(false);
    	downButton.setFocusTraversable(false);
        hBox.getChildren().addAll(label, emptyPane, upButton, downButton);
        HBox.setHgrow(emptyPane, Priority.ALWAYS);
        
        upButton.setOnMouseClicked(Event ->{;
            figureLogController.moveShapeUp(lastItem);
        });
        
        downButton.setOnMouseClicked(Event ->{
        	figureLogController.moveShapeDown(lastItem);
        });
    }

    @Override
    protected void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {
            lastItem = null;
            setGraphic(null);
        } else {
            lastItem = item;
            label.setText(item!=null ? item : "<null>");
            setGraphic(hBox);
        }
    }
}