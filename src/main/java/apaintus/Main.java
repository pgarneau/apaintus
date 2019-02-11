package apaintus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.Properties;

import apaintus.controllers.Controller;
import apaintus.controllers.MenuController;
import apaintus.controllers.ToolBarController;
import apaintus.services.PreferencesService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Controller controller;
	private static Main instance;
	private AnchorPane root;
	static private double MIN_WIDTH = 800;
	static private double  MIN_HEIGHT = 600;
	private PreferencesService preferencesService;

	public static Main getInstance() {
		return instance;
	}

	public static void setInstance(Main instance) {
		Main.instance = instance;
	}

	public AnchorPane getRoot() {
		return root;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		setInstance(this);

		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
		loader.load();
		controller = loader.getController();
		controller.injectPrimaryStage(primaryStage);
		this.root = loader.getRoot();

		primaryStage.setMinWidth(MIN_WIDTH);
		primaryStage.setMinHeight(MIN_HEIGHT);

		primaryStage.setTitle("PaintUS");
		primaryStage.setScene(new Scene(root, MIN_WIDTH, MIN_HEIGHT));
		primaryStage.show();

		preferencesService = new PreferencesService(controller);
		preferencesService.loadPreferences();
	}

	@Override
	public void stop() {
		preferencesService.savePreferences();
		
		MenuController menuController = controller.getMenuController();
		if (menuController.hasUnsavedWork()) {
//			menuController.saveDialogBox();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
