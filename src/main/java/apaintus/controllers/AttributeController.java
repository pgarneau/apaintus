package apaintus.controllers;

import apaintus.models.Attribute;
import apaintus.models.shapes.ShapeAttributes;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.AnchorPane;

import java.util.HashMap;
import java.util.Map;

public class AttributeController implements ChildController<Controller> {
    @FXML
    private Spinner<Double> shapeX;
    @FXML
    private Spinner<Double> shapeY;
    @FXML
    private Spinner<Double> shapeOrientation;
    @FXML
    private Spinner<Double> shapeWidth;
    @FXML
    private Spinner<Double> shapeHeight;
    @FXML
    private TextArea shapeText;

    @FXML
    private AnchorPane root;
    @FXML
    private ToolBar bar;

    private Map<Attribute, SpinnerValueFactory<Double>> spinners = new HashMap<>();
    private Controller controller;
    private CanvasController canvasController;

    @Override
    public void injectParentController(Controller controller) {
        this.controller = controller;
        this.canvasController = this.controller.getCanvasController();

        setAttributeChangeListener();
    }

    @Override
    public void initialize() {
        createValueFactory();
        fillSpinnerMap();
    }

    private void createValueFactory() {
        shapeX.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeY.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeWidth.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeHeight.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeOrientation.setValueFactory(new DoubleSpinnerValueFactory(0.0, 360.0, 0.0, 1));
    }

    private void setAttributeChangeListener() {
        shapeX.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.COORDINATE_X));
        shapeY.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.COORDINATE_Y));
        shapeOrientation.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.ORIENTATION));
        shapeWidth.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.WIDTH));
        shapeHeight.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.HEIGHT));
        shapeText.textProperty().addListener(canvasController.new TextAreaChangeListener(Attribute.TEXT));
    }

    private void fillSpinnerMap() {
        spinners.put(Attribute.COORDINATE_X, shapeX.getValueFactory());
        spinners.put(Attribute.COORDINATE_Y, shapeY.getValueFactory());
        spinners.put(Attribute.ORIENTATION, shapeOrientation.getValueFactory());
        spinners.put(Attribute.WIDTH, shapeWidth.getValueFactory());
        spinners.put(Attribute.HEIGHT, shapeHeight.getValueFactory());
    }

//	public void bindTo(AnchorPane mainPane) {
//		bar.prefWidthProperty().bind(mainPane.prefWidthProperty());
//	}

    public double getSpinnerValue(Attribute attribute) {
        return spinners.get(attribute).getValue();
    }
    
    public String getTextAreaValue() {
        return shapeText.getText();
    }

    public void update(ShapeAttributes shapeAttributes) {
        resetSpinners();
        updateSpinners(shapeAttributes);
        updateTextArea(shapeAttributes.getText());
    }

    public void resetSpinners() {
        for (Map.Entry<Attribute, SpinnerValueFactory<Double>> entry : spinners.entrySet()) {
            entry.getValue().setValue(0.0);
        }
    }

    private void updateSpinners(ShapeAttributes shapeAttributes) {
        spinners.get(Attribute.COORDINATE_X).setValue(shapeAttributes.getCoordinates().getX());
        spinners.get(Attribute.COORDINATE_Y).setValue(shapeAttributes.getCoordinates().getY());
        spinners.get(Attribute.ORIENTATION).setValue(shapeAttributes.getOrientation());
        spinners.get(Attribute.WIDTH).setValue(shapeAttributes.getWidth());
        spinners.get(Attribute.HEIGHT).setValue(shapeAttributes.getHeight());
    }
    
    private void updateTextArea(String text) {
    	shapeText.setText(text);;
    }
}
