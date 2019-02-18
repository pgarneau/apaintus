package apaintus.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(ApplicationExtension.class)
class TestToolBarController {

    @Start
    public void start(Stage stage) throws Exception {
        Parent mainNode = FXMLLoader.load(getClass().getClassLoader().getResource("ToolBar.fxml"));
        stage.setScene(new Scene(mainNode));
        stage.show();
    }

    @Test
    public void testInjectParentController() {
        Controller testController = mock(Controller.class);
        CanvasController testCanvasController = mock(CanvasController.class);
        when(testController.getCanvasController()).thenReturn(testCanvasController);

        ToolBarController test = new ToolBarController();
        test.initialize();
        test.injectParentController(testController);
    }

    @Test
    public void testClickOnSelectButton(FxRobot robot) {
        ToggleButton selectButton = robot.lookup("#select").query();
        selectButton.setSelected(false);
        robot.clickOn("#select");
        assertTrue(selectButton.isSelected());
    }

    @Test
    public void testClickOnRectangleButton(FxRobot robot) {
        ToggleButton rectangleButton = robot.lookup("#rectangle").query();
        rectangleButton.setSelected(false);
        robot.clickOn(rectangleButton);
        assertTrue(rectangleButton.isSelected());
    }

    @Test
    public void testClickOnCircleButton(FxRobot robot) {
        ToggleButton circleButton = robot.lookup("#circle").query();
        circleButton.setSelected(false);
        robot.clickOn(circleButton);
        assertTrue(circleButton.isSelected());
    }

    @Test
    public void testClickOnLineButton(FxRobot robot) {
        ToggleButton lineButton = robot.lookup("#line").query();
        lineButton.setSelected(false);
        robot.clickOn(lineButton);
        assertTrue(lineButton.isSelected());
    }

    @Test
    public void testClickOnSmileyButton(FxRobot robot) {
        ToggleButton smileyButton = robot.lookup("#smiley").query();
        smileyButton.setSelected(false);
        robot.clickOn(smileyButton);
        assertTrue(smileyButton.isSelected());
    }

    @Test
    public void testClickOnTextBoxButton(FxRobot robot) {
        ToggleButton textBoxButton = robot.lookup("#textBox").query();
        textBoxButton.setSelected(false);
        robot.clickOn(textBoxButton);
        assertTrue(textBoxButton.isSelected());
    }

    @Test
    public void testClickOnSnapGridButton(FxRobot robot) {
        ToggleButton snapGridButton = robot.lookup("#snapGrid").query();
        snapGridButton.setSelected(false);
        robot.clickOn(snapGridButton);
        assertTrue(snapGridButton.isSelected());
    }

    @Test
    public void testClickOnStrokeColor(FxRobot robot) {
        ColorPicker colorPicker = robot.lookup("#strokeColor").query();
        colorPicker.setValue(Color.BLACK);
        robot.clickOn(colorPicker).type(KeyCode.TAB).type(KeyCode.ENTER);
        assertTrue(colorPicker.getValue() != Color.BLACK);
    }

    @Test
    public void testClickOnFillColor(FxRobot robot) {
        ColorPicker colorPicker = robot.lookup("#fillColor").query();
        colorPicker.setValue(Color.TRANSPARENT);
        robot.clickOn(colorPicker).type(KeyCode.TAB).type(KeyCode.ENTER);
        assertTrue(colorPicker.getValue() != Color.TRANSPARENT);
    }
}