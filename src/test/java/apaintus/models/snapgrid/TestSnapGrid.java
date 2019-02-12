package apaintus.models.snapgrid;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestSnapGrid {
    double testSpacing = 4.0;
    double testCanvasWidth = 600.0;
    double testCanvasHeight = 800.0;
    double strokeSize = 5.0;
    boolean active = false;

    @Test
    public void testSnapGridAllPoints() {

        testSpacing = 0.0;
        SnapGrid test = null;
        try {
            test = new SnapGrid(testSpacing,
                    testCanvasWidth,
                    testCanvasHeight,
                    strokeSize,
                    active);
        } catch (Exception e) {
            assertNull(test);
        }

        testSpacing = 4.0;
        test = new SnapGrid(testSpacing,
                testCanvasWidth,
                testCanvasHeight,
                strokeSize,
                active);
        ArrayList<Point> goodPoints = new ArrayList();
        for (int y = 0; y <= testCanvasHeight; y += testSpacing * 10) {
            for (int x = 0; x <= testCanvasWidth; x += testSpacing * 10) {
                goodPoints.add(new Point(x, y));
            }
        }

        for (int i = 0; i < goodPoints.size(); i++) {
            assertEquals(goodPoints.get(i).getX(), test.getGridPoints().get(i).getX(),0.1);
            assertEquals(goodPoints.get(i).getX(), test.getGridPoints().get(i).getX(),0.1);
        }
    }

    @Test
    public void testActivateDeactivateSnapGrid(){

        SnapGrid test = new SnapGrid(testSpacing,
                testCanvasWidth,
                testCanvasHeight,
                strokeSize,
                active);

        test.setActive(true);
        assertTrue(test.isActive());

        test.setActive(false);
        assertFalse(test.isActive());
    }

    @Test
    public void testSpacing(){
        SnapGrid test = new SnapGrid(testSpacing,
                testCanvasWidth,
                testCanvasHeight,
                strokeSize,
                active);

        test.setSpacing(2.0);
        assertEquals(20.0,test.getSpacing(),0.0);
    }
}