package apaintus.controllers;

import apaintus.models.Attribute;
import apaintus.models.shapes.DrawableShape;
import apaintus.models.shapes.ShapeType;
import apaintus.models.shapes.ShapeAttributes;
import javafx.beans.property.ReadOnlyProperty;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
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
    private Button ungroup;
    @FXML
    private TextArea text;

    @FXML
    private AnchorPane root;
    @FXML
    private ToolBar bar;

    private Map<Attribute, ReadOnlyProperty> attributes = new HashMap<>();
    private Map<Attribute, ChangeListener> listeners = new HashMap<>();
    private CanvasController canvasController;

    private CanvasController.ShapeSpinnerChangeListener xListener;
    private CanvasController.ShapeSpinnerChangeListener yListener;
    private CanvasController.ShapeSpinnerChangeListener orientationListener;
    private CanvasController.ShapeSpinnerChangeListener widthListener;
    private CanvasController.ShapeSpinnerChangeListener heightListener;
    private CanvasController.TextAreaChangeListener textListener;

    @Override
    public void injectParentController(Controller controller) {
        this.canvasController = controller.getCanvasController();

        ungroup.setOnAction(canvasController.new UngroupActionEvent());
        createListeners();
        fillListenerMap();
    }

    @Override
    public void initialize() {
        setValueFactory();
        fillAttributesMap();

        ungroup.setVisible(false);
    }

    private void createListeners() {
        xListener = canvasController.new ShapeSpinnerChangeListener(Attribute.COORDINATE_X);
        yListener = canvasController.new ShapeSpinnerChangeListener(Attribute.COORDINATE_Y);
        orientationListener = canvasController.new ShapeSpinnerChangeListener(Attribute.ORIENTATION);
        widthListener = canvasController.new ShapeSpinnerChangeListener(Attribute.WIDTH);
        heightListener = canvasController.new ShapeSpinnerChangeListener(Attribute.HEIGHT);
        textListener = canvasController.new TextAreaChangeListener(Attribute.TEXT);
    }

    private void fillListenerMap() {
        listeners.put(Attribute.COORDINATE_X, xListener);
        listeners.put(Attribute.COORDINATE_Y, yListener);
        listeners.put(Attribute.ORIENTATION, orientationListener);
        listeners.put(Attribute.WIDTH, widthListener);
        listeners.put(Attribute.HEIGHT, heightListener);
        listeners.put(Attribute.TEXT, textListener);
    }

    private void fillAttributesMap() {
        attributes.put(Attribute.COORDINATE_X, shapeX.valueProperty());
        attributes.put(Attribute.COORDINATE_Y, shapeY.valueProperty());
        attributes.put(Attribute.ORIENTATION, shapeOrientation.valueProperty());
        attributes.put(Attribute.WIDTH, shapeWidth.valueProperty());
        attributes.put(Attribute.HEIGHT, shapeHeight.valueProperty());
        attributes.put(Attribute.TEXT, text.textProperty());
    }

    private void setValueFactory() {
        shapeX.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeY.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeWidth.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeHeight.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
        shapeOrientation.setValueFactory(new DoubleSpinnerValueFactory(0.0, 360.0, 0.0, 1));
        text.setText("");
    }

    public void setAttributeChangeListeners() {
        setListener(Attribute.COORDINATE_X);
        setListener(Attribute.COORDINATE_Y);
        setListener(Attribute.ORIENTATION);
        setListener(Attribute.WIDTH);
        setListener(Attribute.HEIGHT);
        setListener(Attribute.TEXT);
    }

    public void unsetAttributeChangeListerners() {
        unsetListener(Attribute.COORDINATE_X);
        unsetListener(Attribute.COORDINATE_Y);
        unsetListener(Attribute.ORIENTATION);
        unsetListener(Attribute.WIDTH);
        unsetListener(Attribute.HEIGHT);
        unsetListener(Attribute.TEXT);
    }

    private void setListener(Attribute attribute) {
        attributes.get(attribute).addListener(listeners.get(attribute));
    }

    private void unsetListener(Attribute attribute) {
        attributes.get(attribute).removeListener(listeners.get(attribute));
    }

    public double getDoubleAttribute(Attribute attribute) {
        return Double.valueOf(attributes.get(attribute).getValue().toString());
    }

    public String getStringAttribute(Attribute attribute) {
        return attributes.get(attribute).getValue().toString();
    }

    public void setAttributes(ShapeAttributes shapeAttributes) {
        shapeX.getValueFactory().setValue(shapeAttributes.getCoordinates().getX());
        shapeY.getValueFactory().setValue(shapeAttributes.getCoordinates().getY());
        shapeOrientation.getValueFactory().setValue(shapeAttributes.getOrientation());
        shapeWidth.getValueFactory().setValue(shapeAttributes.getWidth());
        shapeHeight.getValueFactory().setValue(shapeAttributes.getHeight());
        text.setText(shapeAttributes.getText() == null ? "" : shapeAttributes.getText());
    }

    public void update(DrawableShape shape) {
        resetAttributes();
        setAttributes(shape.getShapeAttributes());
        ungroup.setVisible(shape.getShapeType() == ShapeType.SELECTION_BOX);
    }

    public void resetAttributes() {
        setValueFactory();
        ungroup.setVisible(false);
    }
}
