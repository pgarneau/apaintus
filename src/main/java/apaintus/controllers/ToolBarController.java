package apaintus.controllers;

import apaintus.models.Attribute;
import apaintus.models.shapes.ShapeAttributes;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.ToolBarService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ToolBarController implements ChildController<Controller> {
    @FXML private AnchorPane root;
    @FXML private ToolBar toolBar;

    @FXML private Spinner<Double> strokeSize;
    @FXML private ColorPicker fillColor;
    @FXML private ColorPicker strokeColor;
    @FXML private ToggleButton select;
    @FXML private ToggleButton rectangle;
    @FXML private ToggleButton circle;
    @FXML private ToggleButton line;
    @FXML private ToggleButton smiley;
    @FXML private ToggleButton textBox;

    private Controller controller;
    private CanvasController canvasController;

    private ToolBarService toolBarService;
    
    private Properties properties = new Properties();
    private List<ToggleButton> activeToolToggleGroup = new ArrayList<>();
    private ActiveTool activeTool;

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
        this.canvasController = this.controller.getCanvasController();


        fillColor.valueProperty().addListener(canvasController.new ColorChangeListener(Attribute.FILL_COLOR));

        strokeColor.valueProperty().addListener(canvasController.new ColorChangeListener(Attribute.STROKE_COLOR));

        strokeSize.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.STROKE_SIZE));
    }

    @Override
    public void initialize() {
        toolBarService = new ToolBarService();

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
        
        strokeSize.setOnMouseClicked(event -> {
        	this.properties.setProperty("strokeSize", strokeSize.getValue().toString());
        });
        
        strokeColor.setOnAction(event -> {
        	this.properties.setProperty("strokeColor", strokeColor.getValue().toString());
        });
        
        fillColor.setOnAction(event -> {
        	this.properties.setProperty("fillColor", fillColor.getValue().toString());
        });


        // DEFAULT SETTINGS
        select.setSelected(true);
        activeTool = ActiveTool.SELECT;
    }

    public void update(ShapeAttributes shapeAttributes) {
        strokeSize.getValueFactory().setValue(shapeAttributes.getStrokeSize());
        setColorPicker(strokeColor, shapeAttributes.getStrokeColor());
        setColorPicker(fillColor,  shapeAttributes.getFillColor());
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
        if(!colorValue.isEmpty()) {
            colorPicker.setValue(Color.valueOf(colorValue));
        }
    }
    
    public Properties getPreferences() {
    	return properties;
    }
    
    public void setPreferences(Properties properties) {
    	this.properties = properties;
    }
}
