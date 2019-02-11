package apaintus.services;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import apaintus.controllers.Controller;
import apaintus.controllers.MenuController;
import apaintus.controllers.ToolBarController;
import javafx.stage.Stage;

public class PreferencesService {
	Stage primaryStage;
	ToolBarController toolBarController;
	MenuController menuController;

	public PreferencesService(Controller controller) {
		primaryStage = controller.getPrimaryStage();
		toolBarController = controller.getToolBarController();
		menuController = controller.getMenuController();
	}

	public void loadPreferences() {
		Properties properties = new Properties();
		Properties toolBarProperties = new Properties();
		Properties menuProperties = new Properties();
		InputStream input = null;

		try {
			input = new FileInputStream("config.properties");

			// load a properties file
			properties.load(input);

			// load the properties value
			String heightProperty = properties.getProperty("primaryStageHeight");
			String widthProperty = properties.getProperty("primaryStageWidth");
			String strokeSize = properties.getProperty("strokeSize");
			String strokeColor = properties.getProperty("strokeColor");
			String fillColor = properties.getProperty("fillColor");
			String savePath = properties.getProperty("savePath");
			String loadPath = properties.getProperty("loadPath");

			// Set the values in the different controllers
			if (heightProperty != null) {
				primaryStage.setHeight(Double.valueOf(heightProperty));
			}
			if (widthProperty != null) {
				primaryStage.setWidth(Double.valueOf(widthProperty));
			}
			if (strokeSize != null) {
				toolBarController.setStrokeSize(Double.valueOf(strokeSize));
				toolBarProperties.setProperty("strokeSize", strokeSize);
			}
			if (strokeColor != null) {
				toolBarController.setStrokeColor(strokeColor);
				toolBarProperties.setProperty("strokeColor", strokeColor);
			}
			if (fillColor != null) {
				toolBarController.setFillColor(fillColor);
				toolBarProperties.setProperty("fillColor", fillColor);
			}
			if (savePath != null) {
				menuController.setSavePath(savePath);
				menuProperties.setProperty("savePath", savePath);
			}
			if (loadPath != null) {
				menuController.setLoadPath(loadPath);
				menuProperties.setProperty("loadPath", loadPath);
			}

			toolBarController.setPreferences(toolBarProperties);
			menuController.setPreferences(menuProperties);

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

	public void savePreferences() {
		Properties properties = new Properties();
		OutputStream output = null;

		try {
			output = new FileOutputStream("config.properties");

			// set the properties value
			properties.setProperty("primaryStageHeight", Double.toString(primaryStage.getHeight()));
			properties.setProperty("primaryStageWidth", Double.toString(primaryStage.getWidth()));
			properties.putAll(toolBarController.getPreferences());
			properties.putAll(menuController.getPreferences());

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
}
