package apaintus.controllers;

import apaintus.models.ApplicationPreferences;
import apaintus.models.commands.ClearCommand;
import apaintus.models.commands.DrawImageCommand;
import apaintus.models.commands.Invoker;
import apaintus.models.commands.LoadPngCommand;
import apaintus.models.nodes.Node;
import apaintus.services.MenuService;
import apaintus.services.file.FileService;
import apaintus.services.file.png.PngFileService;
import apaintus.services.file.xml.XmlFileService;
import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.util.List;
import java.util.Optional;

public class MenuController implements ChildController<Controller> {
    private CanvasController canvasController;

    private FileService<Image, Image> pngFileService;
    private FileService<List<Node>, List<Node>> xmlFileService;
    private MenuService menuService;

    private Invoker invoker;

    @FXML
    MenuBar menuBar;

    @Override
    public void injectParentController(Controller controller) {
        canvasController = controller.getCanvasController();
        invoker = controller.getInvoker();
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
        if (hasUnsavedWork() && menuService.saveRequested()) {
            savePng();
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
        if (hasUnsavedWork() && menuService.saveRequested()) {
            savePng();
        }

        invoker.execute(
                new LoadPngCommand(
                        new ClearCommand(canvasController),
                        new DrawImageCommand(canvasController, pngFileService.load())
                )
        );
    }

    public void exportXml() {
        xmlFileService.save(canvasController.getNodeList());
        canvasController.setCanvasChanged(false);
    }

    public void importXml() {
        if (hasUnsavedWork() && menuService.saveRequested()) {
            exportXml();
        }

        canvasController.clearCanvas();
        canvasController.setNodeList(xmlFileService.load());
        canvasController.redrawCanvas();
        invoker.clear();
    }

    public void clear() {
        invoker.execute(new ClearCommand(canvasController));
    }

    public void undo() {
        invoker.undo();
    }

    public void redo() {
        invoker.redo();
    }

    public boolean hasUnsavedWork() {
        return canvasController.isCanvasChanged();
    }

    public MenuBar getMenuBar() {
        return menuBar;
    }

    public void setPreferences(ApplicationPreferences applicationPreferences) {
        pngFileService.setPreferences(applicationPreferences);
    }
}
