package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

public class CanvasController implements ChildController<Controller> {
    @FXML private AnchorPane root;
    @FXML private Canvas drawLayer;
    @FXML private Canvas canvas;

    private Controller controller;

    private boolean newImage = true;

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void initialize() {}
}
