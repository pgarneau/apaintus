package apaintus.controllers;

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

//	private Map<Attribute, SpinnerValueFactory<Double>> spinners = new HashMap<>();
	private Controller controller;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void initialize() {
		bar.setStyle("-fx-border-color:lightgrey");

//		createValueFactory();
//		setSpinnerChangeListener();
//		fillSpinnerMap();
//
//		AttributeService.getInstance().setAttributeController(this);
	}

//	private void createValueFactory() {
//		shapeX.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
//		shapeY.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
//		shapeWidth.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
//		shapeHeight.setValueFactory(new DoubleSpinnerValueFactory(0.0, Double.MAX_VALUE, 0.0, 1));
//		shapeOrientation.setValueFactory(new DoubleSpinnerValueFactory(0.0, 360.0, 0.0, 1));
//	}
//
//	private void setSpinnerChangeListener() {
//		shapeX.valueProperty().addListener(new ChangeListener<Double>() {
//			@Override
//			public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
//				if (SelectorService.getInstance().getShapes().contains(SelectorService.getInstance().getSelectedShape())) {
//					AttributeService.getInstance().updateShapeAttribute(Attribute.X, newValue);
//				}
//			}
//		});
//		shapeY.valueProperty().addListener(new ChangeListener<Double>() {
//			@Override
//			public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
//				if (SelectorService.getInstance().getShapes().contains(SelectorService.getInstance().getSelectedShape())) {
//					AttributeService.getInstance().updateShapeAttribute(Attribute.Y, newValue);
//				}
//			}
//		});
//		shapeOrientation.valueProperty().addListener(new ChangeListener<Double>() {
//			@Override
//			public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
//				if (SelectorService.getInstance().getShapes().contains(SelectorService.getInstance().getSelectedShape())) {
//					AttributeService.getInstance().updateShapeAttribute(Attribute.ORIENTATION, newValue);
//				}
//			}
//		});
//		shapeWidth.valueProperty().addListener(new ChangeListener<Double>() {
//			@Override
//			public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
//				if (SelectorService.getInstance().getShapes().contains(SelectorService.getInstance().getSelectedShape())) {
//					AttributeService.getInstance().updateShapeAttribute(Attribute.WIDTH, newValue);
//				}
//			}
//		});
//		shapeHeight.valueProperty().addListener(new ChangeListener<Double>() {
//			@Override
//			public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
//				if (SelectorService.getInstance().getShapes().contains(SelectorService.getInstance().getSelectedShape())) {
//					AttributeService.getInstance().updateShapeAttribute(Attribute.HEIGHT, newValue);
//				}
//			}
//		});
//	}
//
//	private void fillSpinnerMap() {
//		spinners.put(Attribute.X, shapeX.getValueFactory());
//		spinners.put(Attribute.Y, shapeY.getValueFactory());
//		spinners.put(Attribute.ORIENTATION, shapeOrientation.getValueFactory());
//		spinners.put(Attribute.WIDTH, shapeWidth.getValueFactory());
//		spinners.put(Attribute.HEIGHT, shapeHeight.getValueFactory());
//	}
//
//	public void bindTo(AnchorPane mainPane) {
//		bar.prefWidthProperty().bind(mainPane.prefWidthProperty());
//	}
//
//	public synchronized void update() {
//		resetSpinners();
//		Shape shape = SelectorService.getInstance().getSelectedShape();
//		if (shape != null) {
//			updateSpinners(shape);
//		}
//	}
//
//	public void resetSpinners() {
//		for (Map.Entry<Attribute, SpinnerValueFactory<Double>> entry : spinners.entrySet()) {
//			entry.getValue().setValue(0.0);
//		}
//	}
//
//	private void updateSpinners(Shape shape) {
//		spinners.get(Attribute.X).setValue(shape.getCoordinates()[0]);
//		spinners.get(Attribute.Y).setValue(shape.getCoordinates()[1]);
//		spinners.get(Attribute.ORIENTATION).setValue(shape.getOrientation());
//
//		spinners.get(Attribute.WIDTH).setValue(shape.getBoundingBox().getWidth());
//		spinners.get(Attribute.HEIGHT).setValue(shape.getBoundingBox().getHeight());
//	}

	public Controller getController() {
		return controller;
	}
}
