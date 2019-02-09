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
    @FXML private ToggleButton snapGrid;
    @FXML private Spinner<Double> spacingSize;

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
        strokeSize.valueProperty().addListener(canvasController.new ShapeSpinnerChangeListener(Attribute.STROKE_SIZE));
        spacingSize.valueProperty().addListener(canvasController.new GridSpinnerChangeListener());
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
        
        snapGrid.setOnMouseClicked(event ->{
        	canvasController.activateSnapGrid();
        });
        
        strokeSize.setOnMouseClicked(event -> {
            setPreferences();
        });
        
        fillColor.setOnAction(event -> {
            setPreferences();
        });
        
        strokeColor.setOnMouseClicked(event -> {
            setPreferences();
        });


        // DEFAULT SETTINGS
        select.setSelected(true);
        activeTool = ActiveTool.SELECT;
        
        snapGrid.setSelected(false);
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

    public Color getFillColor() {
        return fillColor.getValue();
    }

    public Color getStrokeColor() {
        return strokeColor.getValue();
    }

    public ActiveTool getActiveTool() {
        return activeTool;
    }

    private void setColorPicker(ColorPicker colorPicker, String colorValue) {
        if(colorValue != null &&  !colorValue.isEmpty()) {
            colorPicker.setValue(Color.valueOf(colorValue));
        }
    }

	public Double getGridSpacing() {
		return spacingSize.getValue();
	}
    
    public Properties getPreferences() {
    	return properties;
    }
    
    public void setPreferences(Properties properties) {
    	this.properties = properties;
    	strokeSize.getValueFactory().setValue(Double.valueOf(properties.getProperty("strokeSize")));
    	setColorPicker(fillColor, properties.getProperty("fillColor"));
    	setColorPicker(strokeColor, properties.getProperty("strokeColor"));
    }
    
    private void setPreferences() {
    	this.properties.setProperty("strokeColor", strokeColor.getValue().toString());
    	this.properties.setProperty("fillColor", fillColor.getValue().toString());
    	this.properties.setProperty("strokeSize", strokeSize.getValue().toString());
    }
}
