package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;

public class ZoomController implements ChildController<Controller> {
	private CanvasController canvasController;

	@FXML
	private Slider zoomSlider;

	@Override
	public void injectParentController(Controller controller) {
		canvasController = controller.getCanvasController();
	}

	@Override
	public void initialize() {
		zoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			canvasController.setScale(newValue.doubleValue() / 100);
		});
	}

	public void setZoomFactor(double zoomFactor) {
		zoomSlider.setValue(zoomFactor * 100);
	}
}
