package apaintus.controllers;

import apaintus.models.Alignment;
import apaintus.models.ApplicationPreferences;
import apaintus.models.Attribute;
import apaintus.models.Preference;
import apaintus.models.shapes.Node;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.ToolBarService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
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
    private Spinner<Double> snapGridSize;
    @FXML
    private Button alignLeft;
    @FXML
    private Button alignTop;
    @FXML
    private Button alignRight;
    @FXML
    private Button alignBottom;

    private CanvasController canvasController;

    private List<ToggleButton> activeToolToggleGroup = new ArrayList<>();
    private ActiveTool activeTool;

    private CanvasController.ColorChangeListener fillColorListener;
    private CanvasController.ColorChangeListener strokeColorListener;
    private CanvasController.ShapeSpinnerChangeListener strokeSizeListener;
    private CanvasController.SnapGridSizeListener snapGridSizeListener;

    @Override
    public void injectParentController(Controller controller) {
        this.canvasController = controller.getCanvasController();

        alignBottom.setOnAction(canvasController.new AlignmentActionEvent(Alignment.BOTTOM));
        alignLeft.setOnAction(canvasController.new AlignmentActionEvent(Alignment.LEFT));
        alignRight.setOnAction(canvasController.new AlignmentActionEvent(Alignment.RIGHT));
        alignTop.setOnAction(canvasController.new AlignmentActionEvent(Alignment.TOP));

        createListeners();
        snapGridSize.valueProperty().addListener(snapGridSizeListener);
    }

    @Override
    public void initialize() {
        ToolBarService toolBarService = new ToolBarService();
        ApplicationPreferences applicationPreferences = ApplicationPreferences.getInstance();

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

        snapGrid.setOnMouseClicked(event -> canvasController.toggleSnapGrid());

        strokeSize.setOnMouseClicked(event ->
                applicationPreferences.setPreference(Preference.STROKE_SIZE, strokeSize.getValue().toString()));

        strokeColor.setOnAction(event ->
                applicationPreferences.setPreference(Preference.STROKE_COLOR, strokeColor.getValue().toString()));

        fillColor.setOnAction(event ->
            applicationPreferences.setPreference(Preference.FILL_COLOR, fillColor.getValue().toString()));


        // DEFAULT SETTINGS
        select.setSelected(true);
        activeTool = ActiveTool.SELECT;

        snapGrid.setSelected(false);
    }

    private void createListeners() {
        fillColorListener = canvasController.new ColorChangeListener(Attribute.FILL_COLOR);
        strokeColorListener = canvasController.new ColorChangeListener(Attribute.STROKE_COLOR);
        strokeSizeListener = canvasController.new ShapeSpinnerChangeListener(Attribute.STROKE_SIZE);
        snapGridSizeListener = canvasController.new SnapGridSizeListener();
    }

    public void setToolBarListeners() {
        fillColor.valueProperty().addListener(fillColorListener);
        strokeColor.valueProperty().addListener(strokeColorListener);
        strokeSize.valueProperty().addListener(strokeSizeListener);
    }

    public void unsetToolBarListeners() {
        fillColor.valueProperty().removeListener(fillColorListener);
        strokeColor.valueProperty().removeListener(strokeColorListener);
        strokeSize.valueProperty().removeListener(strokeSizeListener);
    }

    public void update(Node node) {
        strokeSize.getValueFactory().setValue(Node.get(node, Attribute.STROKE_SIZE.toString()));
        setColorPicker(strokeColor, Node.get(node, Attribute.STROKE_COLOR.toString()));
        setColorPicker(fillColor, Node.get(node, Attribute.FILL_COLOR.toString()));
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

    public Double getSnapGridSize() {
        return snapGridSize.getValue();
    }

    public void setPreferences(ApplicationPreferences applicationPreferences) {
        String preferenceStrokeSize = applicationPreferences.getPreference(Preference.STROKE_SIZE);
        String preferenceStrokeColor = applicationPreferences.getPreference(Preference.STROKE_COLOR);
        String preferenceFillColor = applicationPreferences.getPreference(Preference.FILL_COLOR);

        if (preferenceStrokeSize != null) {
            setStrokeSize(Double.valueOf(preferenceStrokeSize));
        }

        if (preferenceStrokeColor != null) {
            setStrokeColor(preferenceStrokeColor);
        }

        if (preferenceFillColor != null) {
            setFillColor(preferenceFillColor);
        }
    }
}
