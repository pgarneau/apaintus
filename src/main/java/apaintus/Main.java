package apaintus;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import apaintus.controllers.Controller;
import apaintus.controllers.MenuController;
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
		loadPreferences();

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
	}

	@Override
	public void stop() {
		savePreferences();
		MenuController menuController = controller.getMenuController();
		if (menuController.hasUnsavedWork()) {
//			menuController.saveDialogBox();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

	private static void savePreferences() {
		Properties prop = new Properties();
		OutputStream output = null;

		try {

			output = new FileOutputStream("config.properties");

			// set the properties value
			prop.setProperty("database", "localhost");
			prop.setProperty("dbuser", "mkyong");
			prop.setProperty("dbpassword", "password");

			// save properties to project root folder
			prop.store(output, null);

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

	private static void loadPreferences() {
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("database"));
			System.out.println(prop.getProperty("dbuser"));
			System.out.println(prop.getProperty("dbpassword"));

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
