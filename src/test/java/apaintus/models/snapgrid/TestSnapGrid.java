package apaintus.models.snapgrid;

import apaintus.models.Point;
import apaintus.models.shapes.Rectangle;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class TestSnapGrid {
    @Test
    public void testSnapGridAllPoints() {
        double testSpacing = 0.0;
        double testCanvasWidth = 600.0;
        double testCanvasHeight = 800.0;
        double strokeSize = 5.0;
        boolean active = false;

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
}
