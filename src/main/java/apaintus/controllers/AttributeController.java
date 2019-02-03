package apaintus.controllers;

import apaintus.models.Attribute;
import apaintus.models.shapes.DrawableShape;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.DoubleSpinnerValueFactory;
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

        setSpinnerChangeListener();
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

    private void setSpinnerChangeListener() {
        shapeX.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.COORDINATE_X));
        shapeY.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.COORDINATE_Y));
        shapeOrientation.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.ORIENTATION));
        shapeWidth.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.WIDTH));
        shapeHeight.valueProperty().addListener(canvasController.new SpinnerChangeListener(Attribute.HEIGHT));
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

    public double getAttributeValue(Attribute attribute) {
        return spinners.get(attribute).getValue();
    }

    public void update(DrawableShape shape) {
        resetSpinners();
        updateSpinners(shape);
    }

    public void resetSpinners() {
        for (Map.Entry<Attribute, SpinnerValueFactory<Double>> entry : spinners.entrySet()) {
            entry.getValue().setValue(0.0);
        }
    }

    private void updateSpinners(DrawableShape shape) {
        spinners.get(Attribute.COORDINATE_X).setValue(shape.getCoordinates().getX());
        spinners.get(Attribute.COORDINATE_Y).setValue(shape.getCoordinates().getY());
        spinners.get(Attribute.ORIENTATION).setValue(shape.getOrientation());
        spinners.get(Attribute.WIDTH).setValue(shape.getWidth());
        spinners.get(Attribute.HEIGHT).setValue(shape.getHeight());
    }
}
