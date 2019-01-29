package apaintus.controllers;

import apaintus.models.shapes.Shape;
import apaintus.services.MenuService;
import apaintus.services.file.FileService;
import apaintus.services.file.png.PngFileService;
import apaintus.services.file.xml.XmlFileService;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Optional;

public class MenuController implements ChildController<Controller> {
	private Controller controller;
	private FileService<Canvas, Image> pngFileService;
	private FileService<List<Shape>, List<Shape>> xmlFileService;
	private MenuService menuService;

	@FXML MenuBar menuBar;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void initialize() {
		this.pngFileService = new PngFileService();
		this.xmlFileService = new XmlFileService();
		this.menuService = new MenuService();
	}

//	public void bindTo(AnchorPane mainPane) {
//		menuBar.prefWidthProperty().bind(mainPane.widthProperty());
//	}

	public void newCanvas() {
	    if (hasUnsavedWork()) {
	    	if (menuService.saveRequested()) {
	    		savePng();
			}
		}

		TextInputDialog dialog = new TextInputDialog("800x600");
		dialog.setTitle("Select width and height");
		dialog.setHeaderText("Canvas Width-Height");
		dialog.setContentText("Width x Height:");

		Optional<String> result = dialog.showAndWait();
		result.ifPresent(w -> {
			String[] dims = w.split("x");
			controller.getCanvasController().newCanvas(dims);
		});

	}

	public void savePng() {
		pngFileService.save(controller.getCanvasController().getCanvas());
	}

	public Image loadPng() {
		if (hasUnsavedWork()) {
			if (menuService.saveRequested()) {
				savePng();
			}
		}
		return pngFileService.load();
	}

	public void exportXml() {
		xmlFileService.save(controller.getCanvasController().getShapes());
	}

	public List<Shape> importXml() {
	    if (hasUnsavedWork()) {
	        if (menuService.saveRequested()) {
	        	exportXml();
			}
		}
	    return xmlFileService.load();
	}

	public void clearButtonClicked() {
		controller.getCanvasController().clear();
		controller.getCanvasController().clearDrawnShapeHistory();
	}

	public boolean hasUnsavedWork() {
		if (controller.getCanvasController().getShapes().isEmpty())
			return false;
		return true;
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

}