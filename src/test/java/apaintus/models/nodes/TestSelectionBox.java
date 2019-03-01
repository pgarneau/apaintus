package apaintus.models.nodes;

import apaintus.models.Point;
import apaintus.models.nodes.shapes.Rectangle;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.selectionBox.SelectionBoxDrawService;
import apaintus.services.update.SelectionBoxUpdateService;
import apaintus.services.update.UpdateService;
import apaintus.util.ReflectionUtil;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class TestSelectionBox {
    @Test
    public void updateBoundingBoxSuccessful() {
        // Given
        Point coordinates = new Point(0, 0);
        Point expectedCenter = new Point(1, 1);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;

        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();

        SelectionBox selectionBox = new SelectionBox(nodeAttributes);

        // When
        selectionBox.updateBoundingBox();
        BoundingBox boundingBox = selectionBox.getBoundingBox();
        Point center = ReflectionUtil.get(boundingBox, "center");

        // Then
        assertEquals(expectedCenter.getX(), center.getX(), 0.1);
        assertEquals(expectedCenter.getY(), center.getY(), 0.1);
        assertEquals(Double.valueOf(expectedWidth + BoundingBox.STROKE_SIZE), ReflectionUtil.get(boundingBox, "width"));
        assertEquals(Double.valueOf(expectedHeight + BoundingBox.STROKE_SIZE), ReflectionUtil.get(boundingBox, "height"));
        assertEquals(Double.valueOf(expectedOrientation), ReflectionUtil.get(boundingBox, "orientation"));
    }

    @Test
    public void getDrawService() {
        // Given
        Point coordinates = new Point(0, 0);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;

        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();

        SelectionBox selectionBox = new SelectionBox(nodeAttributes);

        // When
        DrawService drawService = selectionBox.getDrawService();

        // Then
        assertEquals(SelectionBoxDrawService.class, drawService.getClass());
    }

    @Test
    public void getUpdateService() {
        // Given
        Point coordinates = new Point(0, 0);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;

        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();

        SelectionBox selectionBox = new SelectionBox(nodeAttributes);

        // When
        UpdateService updateService = selectionBox.getUpdateService();

        // Then
        assertEquals(SelectionBoxUpdateService.class, updateService.getClass());
    }

    @Test
    public void addNodeThenSuccessful() {
        // Given
        Point coordinates = new Point(0, 0);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;

        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();

        SelectionBox selectionBox = new SelectionBox(nodeAttributes);
        Node rectangle = mock(Rectangle.class);

        // When
        selectionBox.addNode(rectangle);

        // Then
        assertTrue(selectionBox.getNodeList().contains(rectangle));
    }

    @Test
    public void resizeThenSuccessful() {
        // Given
        Point coordinates = new Point(50, 50);
        Point nodeCoordinates = new Point(0, 0);
        double expectedWidth = 2;
        double expectedHeight = 2;
        double expectedOrientation = 0;

        NodeAttributes selectionBoxAttributes = NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();
        NodeAttributes nodeAttributes = NodeAttributes
                .builder()
                .withCoordinates(nodeCoordinates)
                .withWidth(expectedWidth)
                .withHeight(expectedHeight)
                .withOrientation(expectedOrientation)
                .build();

        SelectionBox selectionBox = new SelectionBox(selectionBoxAttributes);
        Node node = new SelectionBox(nodeAttributes);
        node.update(nodeAttributes);

        selectionBox.addNode(node);

        // When
        selectionBox.resize();

        // Then
        assertEquals(nodeCoordinates.getX() - BoundingBox.STROKE_SIZE / 2, selectionBox.getCoordinates().getX(), 0.1);
        assertEquals(nodeCoordinates.getY() - BoundingBox.STROKE_SIZE / 2, selectionBox.getCoordinates().getY(), 0.1);
    }
}
