package apaintus;

import apaintus.controllers.Controller;
import apaintus.controllers.MenuController;
import apaintus.models.ApplicationPreferences;
import apaintus.models.Preference;
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
	static private double MIN_HEIGHT = 600;
	private ApplicationPreferences applicationPreferences;
	private Stage primaryStage;

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
		this.primaryStage = primaryStage;

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

		applicationPreferences = ApplicationPreferences.getInstance();
		applicationPreferences.loadPreferences();
		controller.getToolBarController().setPreferences(applicationPreferences);
		controller.getMenuController().setPreferences(applicationPreferences);
		primaryStage.setWidth(Double.valueOf(applicationPreferences.getPreference(Preference.WIDTH)));
		primaryStage.setHeight(Double.valueOf(applicationPreferences.getPreference(Preference.HEIGHT)));

	}

	@Override
	public void stop() {
		applicationPreferences.setPreference(Preference.WIDTH, Double.toString(primaryStage.getWidth()));
		applicationPreferences.setPreference(Preference.HEIGHT, Double.toString(primaryStage.getHeight()));
		applicationPreferences.savePreferences();

		MenuController menuController = controller.getMenuController();
		if (menuController.hasUnsavedWork()) {
//			menuController.saveDialogBox();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
