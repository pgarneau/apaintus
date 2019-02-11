package apaintus.controllers;

import apaintus.models.ApplicationPreferences;
import apaintus.models.Attribute;
import apaintus.models.Preference;
import apaintus.models.shapes.ShapeAttributes;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.ToolBarService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ToolBarController implements ChildController<Controller> {
	@FXML
	private ToolBar toolBar;

	@FXML
	private Spinner<Double> strokeSize;
	@FXML
	private ColorPicker fillColor;
	@FXML
	private ColorPicker strokeColor;
	@FXML
	private ToggleButton select;
	@FXML
	private ToggleButton rectangle;
	@FXML
	private ToggleButton circle;
	@FXML
	private ToggleButton line;
	@FXML
	private ToggleButton smiley;
	@FXML
	private ToggleButton textBox;
	@FXML
	private ToggleButton snapGrid;
	@FXML
	private Spinner<Double> spacingSize;

	private Controller controller;
	private CanvasController canvasController;
	private ToolBarService toolBarService;
	private ApplicationPreferences applicationPreferences;

	private List<ToggleButton> activeToolToggleGroup = new ArrayList<>();
	private ActiveTool activeTool;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		this.canvasController = this.controller.getCanvasController();

		fillColor.valueProperty().addListener(canvasController.new ColorChangeListener(Attribute.FILL_COLOR));
		strokeColor.valueProperty().addListener(canvasController.new ColorChangeListener(Attribute.STROKE_COLOR));
		strokeSize.valueProperty().addListener(canvasController.new ShapeSpinnerChangeListener(Attribute.STROKE_SIZE));
		spacingSize.valueProperty().addListener(canvasController.new GridSpinnerChangeListener());
	}

	@Override
	public void initialize() {
		toolBarService = new ToolBarService();
		applicationPreferences = ApplicationPreferences.getInstance();

		activeToolToggleGroup.add(select);
		activeToolToggleGroup.add(rectangle);
		activeToolToggleGroup.add(circle);
		activeToolToggleGroup.add(line);
		activeToolToggleGroup.add(smiley);
		activeToolToggleGroup.add(textBox);

		select.setOnMouseClicked(event -> {
			toolBarService.toggle(select, activeToolToggleGroup);
			activeTool = ActiveTool.SELECT;
		});

		rectangle.setOnMouseClicked(event -> {
			toolBarService.toggle(rectangle, activeToolToggleGroup);
			activeTool = ActiveTool.RECTANGLE;
		});

		circle.setOnMouseClicked(event -> {
			toolBarService.toggle(circle, activeToolToggleGroup);
			activeTool = ActiveTool.CIRCLE;
		});

		line.setOnMouseClicked(event -> {
			toolBarService.toggle(line, activeToolToggleGroup);
			activeTool = ActiveTool.LINE;
		});

		smiley.setOnMouseClicked(event -> {
			toolBarService.toggle(smiley, activeToolToggleGroup);
			activeTool = ActiveTool.SMILEY;
		});

		textBox.setOnMouseClicked(event -> {
			toolBarService.toggle(textBox, activeToolToggleGroup);
			activeTool = ActiveTool.TEXT_BOX;
		});

		snapGrid.setOnMouseClicked(event -> {
			canvasController.activateSnapGrid();
		});

		strokeSize.setOnMouseClicked(event -> {
			applicationPreferences.setPreference(Preference.STROKE_SIZE, strokeSize.getValue().toString());
		});

		strokeColor.setOnAction(event -> {
			applicationPreferences.setPreference(Preference.STROKE_COLOR, strokeColor.getValue().toString());
		});

		fillColor.setOnAction(event -> {
			applicationPreferences.setPreference(Preference.FILL_COLOR, fillColor.getValue().toString());
		});

		// DEFAULT SETTINGS
		select.setSelected(true);
		activeTool = ActiveTool.SELECT;

		snapGrid.setSelected(false);
	}

	public void update(ShapeAttributes shapeAttributes) {
		strokeSize.getValueFactory().setValue(shapeAttributes.getStrokeSize());
		setColorPicker(strokeColor, shapeAttributes.getStrokeColor());
		setColorPicker(fillColor, shapeAttributes.getFillColor());
	}

	// Binds the Toolbar's size to the main anchorpane
	public void bindTo(AnchorPane anchorPane) {
		toolBar.prefWidthProperty().bind(anchorPane.widthProperty());
	}

	public double getStrokeSize() {
		return strokeSize.getValue();
	}

	public void setStrokeSize(Double strokeSize) {
		this.strokeSize.getValueFactory().setValue(strokeSize);
	}

	public Color getStrokeColor() {
		return strokeColor.getValue();
	}

	public void setStrokeColor(String color) {
		strokeColor.setValue(Color.valueOf(color));
	}

	public Color getFillColor() {
		return fillColor.getValue();
	}

	public void setFillColor(String color) {
		fillColor.setValue(Color.valueOf(color));
	}

	public ActiveTool getActiveTool() {
		return activeTool;
	}

	private void setColorPicker(ColorPicker colorPicker, String colorValue) {
		if (colorValue != null && !colorValue.isEmpty()) {
			colorPicker.setValue(Color.valueOf(colorValue));
		}
	}

	public Double getGridSpacing() {
		return spacingSize.getValue();
	}

	public void setPreferences(ApplicationPreferences applicationPreferences) {
		String strokeSize = applicationPreferences.getPreference(Preference.STROKE_SIZE);
		String strokeColor = applicationPreferences.getPreference(Preference.STROKE_COLOR);
		String fillColor = applicationPreferences.getPreference(Preference.FILL_COLOR);

		if (strokeSize != null) {
			setStrokeSize(Double.valueOf(strokeSize));
			applicationPreferences.setPreference(Preference.STROKE_SIZE, strokeSize);
		}
		if (strokeColor != null) {
			setStrokeColor(strokeColor);
			applicationPreferences.setPreference(Preference.STROKE_COLOR, strokeColor);
		}
		if (fillColor != null) {
			setFillColor(fillColor);
			applicationPreferences.setPreference(Preference.FILL_COLOR, fillColor);
		}
	}
}
