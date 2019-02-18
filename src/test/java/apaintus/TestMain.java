package apaintus;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestMain {
    @Test
    public void testMain() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            private JFXPanel jfxPanel;
            @Override
            public void run() {
                jfxPanel = new JFXPanel();
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Main main = new Main();
                            main.start(new Stage());
                            assertNotNull(main.getRoot());
                            assertNotNull(main.getInstance());
                            main.stop();
                        } catch (Exception e) {}
                    }
                });
            }
        });
        thread.run();
        Thread.sleep(1000);
    }
}
