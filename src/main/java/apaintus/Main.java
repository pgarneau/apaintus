package apaintus;

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
    public void start(Stage primaryStage) throws Exception{
    	setInstance(this);
    	
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
		loader.load();
		controller = loader.getController();
    	controller.injectPrimaryStage(primaryStage);
    	this.root = loader.getRoot();
        
        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
        
        primaryStage.setTitle("PaintUS");
        primaryStage.setScene(new Scene(root,800,600));
        primaryStage.show();
    }

    @Override
    public void stop() {
		MenuController menuController = controller.getMenuController();
		if (menuController.hasUnsavedWork()) {
//			menuController.saveDialogBox();
		}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
