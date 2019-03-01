package apaintus.models.nodes;

import apaintus.models.Point;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBoundingBox {
    @Test
    public void updateThenSuccessful() {
        // Given
        BoundingBox boundingBox = new BoundingBox();
        Point expectedCenter = new Point(10, 10);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;
        Point[] expectedVertices = new Point[]{
                new Point(8, 8),
                new Point(12, 8),
                new Point(12, 12),
                new Point(8, 12)
        };

        // When
        boundingBox.update(expectedCenter, expectedWidth, expectedHeight, expectedOrientation);

        // Then
        Point[] vertices = boundingBox.getVertices();
        assertEquals(expectedVertices[0].getX(), vertices[0].getX(), 0.1);
        assertEquals(expectedVertices[0].getY(), vertices[0].getY(), 0.1);
        assertEquals(expectedVertices[1].getX(), vertices[1].getX(), 0.1);
        assertEquals(expectedVertices[1].getX(), vertices[1].getX(), 0.1);
        assertEquals(expectedVertices[2].getX(), vertices[2].getX(), 0.1);
        assertEquals(expectedVertices[2].getY(), vertices[2].getY(), 0.1);
        assertEquals(expectedVertices[3].getY(), vertices[3].getY(), 0.1);
        assertEquals(expectedVertices[3].getY(), vertices[3].getY(), 0.1);
    }

    @Test
    public void containsWhenPointNotInsideThenReturnsFalse() {
        // Given
        BoundingBox boundingBox = new BoundingBox();
        Point expectedCenter = new Point(10, 10);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;
        boundingBox.update(expectedCenter, expectedWidth, expectedHeight, expectedOrientation);

        Point externalPoint = new Point(50, 50);

        // When
        boolean isInside = boundingBox.contains(externalPoint);

        // Then
        assertFalse(isInside);
    }

    @Test
    public void containsWhenPointInsideThenReturnTrue() {
        // Given
        BoundingBox boundingBox = new BoundingBox();
        Point expectedCenter = new Point(10, 10);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;
        boundingBox.update(expectedCenter, expectedWidth, expectedHeight, expectedOrientation);

        Point externalPoint = new Point(10, 10);

        // When
        boolean isInside = boundingBox.contains(externalPoint);

        // Then
        assertTrue(isInside);
    }
}
