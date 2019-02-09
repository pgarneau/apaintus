package apaintus.controllers;

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
//	private SelectorController selectorController;

	public void initialize() {

		canvasController.injectParentController(this);
		menuController.injectParentController(this);
		toolBarController.injectParentController(this);
		attributeController.injectParentController(this);
//
//		selectorController = new SelectorController();
//		selectorController.injectParentController(this);
//		selectorController.initialize();

		menuController.bindTo(mainPane);
		toolBarController.bindTo(mainPane);

//		SelectorObserver selectorObserver = new SelectorObserver();
//		CanvasObserver canvasObserver = new CanvasObserver();
//		ToolBarObserver toolBarObserver = new ToolBarObserver();
//
//		SelectorService.getInstance().attach(canvasObserver);
//		SelectorService.getInstance().attach(toolBarObserver);
//
//		ToolBarService.getInstance().attach(selectorObserver);
//		ToolBarService.getInstance().attach(canvasObserver);
//
//		CanvasService.getInstance().attach(toolBarObserver);
//		CanvasService.getInstance().attach(selectorObserver);
	}

	public void injectPrimaryStage(Stage stage) {
		primaryStage = stage;
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
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
//
//	public SelectorController getSelectorController() {
//		return selectorController;
//	}
}

