package apaintus.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.service.query.NodeQuery;

@ExtendWith(ApplicationExtension.class)
public class TestCanvasController {

    private Canvas canvas;
    /**
     * @param robot - Will be injected by the test runner.
     */
    @Test
    public void testOnMousePressed(FxRobot robot) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Canvas.fxml"));
        loader.load();
        CanvasController test = loader.getController();
        test.initialize();

        robot.clickOn();
    }
}
