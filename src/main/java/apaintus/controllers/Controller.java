package apaintus.controllers;

import apaintus.models.commands.Invoker;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
	@FXML private CanvasController canvasController;
	@FXML private MenuController menuController;
	@FXML private ToolBarController toolBarController;
	@FXML private AttributeController attributeController;

	@FXML private AnchorPane mainPane;
	@FXML private ScrollPane scrollPane;

	private Stage primaryStage;
	private Invoker invoker = new Invoker();

	public void initialize() {
		canvasController.injectParentController(this);
		menuController.injectParentController(this);
		toolBarController.injectParentController(this);
		attributeController.injectParentController(this);

		menuController.bindTo(mainPane);
		toolBarController.bindTo(mainPane);
	}

	public void injectPrimaryStage(Stage stage) {
		primaryStage = stage;
	}

	public CanvasController getCanvasController() {
		return canvasController;
	}

	public MenuController getMenuController() {
		return menuController;
	}

	public ToolBarController getToolBarController() {
		return toolBarController;
	}

	public AttributeController getAttributeController() {
		return attributeController;
	}

	public Invoker getInvoker() {
		return invoker;
	}
}

