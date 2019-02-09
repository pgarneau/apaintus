package apaintus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import apaintus.controllers.Controller;
import apaintus.controllers.MenuController;
import apaintus.controllers.ToolBarController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Controller controller;
	private static Main instance;
	private AnchorPane root;

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

		primaryStage.setMinHeight(600);
		primaryStage.setMinWidth(800);

		primaryStage.setTitle("PaintUS");
		primaryStage.setScene(new Scene(root, 800, 600));
		primaryStage.show();
		
		loadPreferences(primaryStage);
	}

	@Override
	public void stop() {
		Stage primaryStage = controller.getPrimaryStage();

		savePreferences(primaryStage);
		
		MenuController menuController = controller.getMenuController();
		if (menuController.hasUnsavedWork()) {
//			menuController.saveDialogBox();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private void savePreferences(Stage primaryStage) {
		ToolBarController toolBarController = controller.getToolBarController();
		Properties toolBarProperties = toolBarController.getPreferences();
		
		Properties properties = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream("config.properties");

			double height = primaryStage.getHeight();
			double width = primaryStage.getWidth();
			// set the properties value
			properties.setProperty("primaryStageHeight", Double.toString(height));
			properties.setProperty("primaryStageWidth", Double.toString(width));
			properties.putAll(toolBarProperties);

			// save properties to project root folder
			properties.store(output, null);
		} catch (IOException io) {
			io.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void loadPreferences(Stage primaryStage) {
		ToolBarController toolBarController = controller.getToolBarController();
		
		Properties properties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");

			// load a properties file
			properties.load(input);

			// get the property value and print it out
			primaryStage.setHeight(Double.valueOf(properties.getProperty("primaryStageHeight")));
			primaryStage.setWidth(Double.valueOf(properties.getProperty("primaryStageWidth")));
			toolBarController.setPreferences(properties);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
