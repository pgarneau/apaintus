package apaintus.controllers;

import apaintus.models.shapes.DrawableShape;
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
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MenuController implements ChildController<Controller> {
	private Controller controller;
	private CanvasController canvasController;

	private FileService<Image, Image> pngFileService;
	private FileService<List<DrawableShape>, List<DrawableShape>> xmlFileService;
	private MenuService menuService;

	@FXML MenuBar menuBar;

	@Override
	public void injectParentController(Controller controller) {
		this.controller = controller;
		this.canvasController = this.controller.getCanvasController();
	}

	@Override
	public void initialize() {
		this.pngFileService = new PngFileService();
		this.xmlFileService = new XmlFileService();
		this.menuService = new MenuService();
	}

	// Binds the Menubar's size to the main anchorpane
	public void bindTo(AnchorPane anchorPane) {
		menuBar.prefWidthProperty().bind(anchorPane.widthProperty());
	}

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
//			controller.getCanvasController().newCanvas(dims);
		});

	}

	public void savePng() {
		pngFileService.save(canvasController.getCanvasImage());
		canvasController.setCanvasChanged(false);
	}

	public void loadPng() {
		if (hasUnsavedWork()) {
			if (menuService.saveRequested()) {
				savePng();
			}
		}

		canvasController.clearCanvas();
		canvasController.drawImage(pngFileService.load());
	}

	public void exportXml() {
		xmlFileService.save(canvasController.getDrawnShapes());
		canvasController.setCanvasChanged(false);
	}

	public void importXml() {
	    if (hasUnsavedWork()) {
	        if (menuService.saveRequested()) {
	        	exportXml();
			}
		}

		canvasController.clearCanvas();
	    canvasController.setDrawnShapes(xmlFileService.load());
	    canvasController.redrawCanvas();
	}

	public void clearButtonClicked() {
		canvasController.clearCanvas();
	}

	public boolean hasUnsavedWork() {
	    return canvasController.isCanvasChanged();
	}

	public MenuBar getMenuBar() {
		return menuBar;
	}

}