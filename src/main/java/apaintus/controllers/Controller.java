package apaintus.controllers;

import apaintus.models.commands.Invoker;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller {
	@FXML
	private CanvasController canvasController;
	@FXML
	private MenuController menuController;
	@FXML
	private ToolBarController toolBarController;
	@FXML
	private AttributeController attributeController;
	@FXML
	private FigureLogController figureLogController;
	@FXML
	private ActionLogController actionLogController;
	@FXML
	private ZoomController zoomController;

	@FXML
	private AnchorPane mainPane;
	@FXML
	private ScrollPane scrollPane;

	private Stage primaryStage;
	private Invoker invoker = new Invoker();

    public void initialize() {
        canvasController.injectParentController(this);
        menuController.injectParentController(this);
        toolBarController.injectParentController(this);
        attributeController.injectParentController(this);
        figureLogController.injectParentController(this);
        actionLogController.injectParentController(this);

		zoomController.injectParentController(this);

		invoker.setActionLogController(actionLogController);

		menuController.bindTo(mainPane);
		toolBarController.bindTo(mainPane);

		scrollPane.addEventFilter(ScrollEvent.SCROLL, new EventHandler<ScrollEvent>() {
			@Override
			public void handle(ScrollEvent event) {
				double zoomFactor = Math.round(zoomController.getZoomFactor()); 
				if (event.isControlDown() && event.getDeltaY() > 0 && zoomFactor < 150) {
					zoomFactor = zoomFactor + 10; 
				} else if (event.isControlDown() && event.getDeltaY() < 0 && zoomFactor > 50) {
					zoomFactor = zoomFactor - 10;
				}
				zoomController.setZoomFactor(zoomFactor);
			}
		});
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

    public Invoker getInvoker() {
        return invoker;
    }

    public FigureLogController getFigureLogController() {
        return figureLogController;
    }

    public ActionLogController getActionLogController() {
        return actionLogController;
    }

	public ZoomController getZoomController() {
		return zoomController;
	}
}
