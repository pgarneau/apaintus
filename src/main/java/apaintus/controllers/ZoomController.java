package apaintus.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.Label;
import java.text.DecimalFormat;

public class ZoomController implements ChildController<Controller> {
	private CanvasController canvasController;
	private DecimalFormat decimalFormat = new DecimalFormat("###.##");

	@FXML
	private Slider zoomSlider;
	@FXML
	private Label sliderLabel;

	@Override
	public void injectParentController(Controller controller) {
		canvasController = controller.getCanvasController();
	}

	@Override
	public void initialize() {
		zoomSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
			canvasController.setScale(newValue.doubleValue() / 100);
			sliderLabel.setText(decimalFormat.format(newValue) + "%");
		});
	}

	public void setZoomFactor(double zoomFactor) {
		zoomSlider.setValue(zoomFactor);
	}
	
	public double getZoomFactor() {
		return zoomSlider.getValue();
	}
}
