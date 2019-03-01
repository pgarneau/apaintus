package apaintus.models.snapgrid;

import apaintus.models.Point;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestSnapGrid {
    @Test
    public void setGradationThenSuccessful() {
        // Given
        double width = 0;
        double height = 0;
        double size = 1;
        int expectedSnapGridPointsSize = 1;
        SnapGrid snapGrid = new SnapGrid(width, height);

        // When
        snapGrid.setGradation(size);
        List<Point> snapGridPoints = snapGrid.getSnapGridPoints();

        // Then
        assertEquals(expectedSnapGridPointsSize, snapGridPoints.size());
        assertEquals(size, snapGrid.getGradation(), 0.1);
    }

    @Test
    public void toggleThenSuccessful() {
        // Given
        double width = 0;
        double height = 0;
        SnapGrid snapGrid = new SnapGrid(width, height);

        // When
        snapGrid.toggle();

        // Then
        assertTrue(snapGrid.isActive());
    }
}
