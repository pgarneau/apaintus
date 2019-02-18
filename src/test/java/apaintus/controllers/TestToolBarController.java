package apaintus.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
class TestToolBarController {
    private ToggleButton selectButton;
    private ToggleButton rectangleButton;

    @Start
    public void start(Stage stage) throws Exception{
        Parent mainNode = FXMLLoader.load(getClass().getClassLoader().getResource("ToolBar.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
    }

    @Test
    public void testClickOnSelectButton(FxRobot robot) {
        selectButton = robot.lookup("#select").query();
        robot.clickOn("#select");
        assertTrue(selectButton.isSelected());
    }

    @Test
    public void testClickOnRectangleButton(FxRobot robot){
        rectangleButton = robot.lookup("#rectangle").query();
        robot.clickOn(rectangleButton);
        assertTrue(rectangleButton.isSelected());
    }
}