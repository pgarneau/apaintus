package apaintus.models.commands;

import apaintus.controllers.AttributeController;
import apaintus.controllers.CanvasController;
import apaintus.controllers.ToolBarController;
import apaintus.models.Alignment;
import apaintus.models.Point;
import apaintus.models.nodes.BoundingBox;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.NodeType;
import apaintus.models.nodes.SelectionBox;
import apaintus.models.nodes.shapes.Line;
import apaintus.models.nodes.shapes.Rectangle;
import apaintus.util.ReflectionUtil;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TestAlignCommand {
    private static CanvasController canvasController;
    private SelectionBox selectionBox;
    private Rectangle rectangle;
    private Line line;

    @BeforeAll
    public static void setUp() {
        canvasController = mock(CanvasController.class);
        ToolBarController toolBarController = mock(ToolBarController.class);
        AttributeController attributeController = mock(AttributeController.class);
        when(canvasController.getToolBarController()).thenReturn(toolBarController);
        when(canvasController.getAttributeController()).thenReturn(attributeController);
    }

    @BeforeEach
    public void beforeTest() {
        selectionBox = new SelectionBox(NodeAttributes.builder()
                .withCoordinates(new Point(0.0, 0.0))
                .withHeight(100.0)
                .withWidth(100.0)
                .withOrientation(0.0)
                .build());

        rectangle = mock(Rectangle.class);
        when(rectangle.getCoordinates()).thenReturn(mock(Point.class));
        when(rectangle.getNodeType()).thenReturn(NodeType.RECTANGLE);

        line = mock(Line.class);
        when(line.getCoordinates()).thenReturn(mock(Point.class));
        when(line.getCenter()).thenReturn(mock(Point.class));
        when(line.getNodeType()).thenReturn(NodeType.LINE);

        BoundingBox boundingBox = mock(BoundingBox.class);
        Point[] vertices = new Point[]{new Point(0.0, 0.0),
                new Point(2.0, 0.0),
                new Point(0.0, 2.0),
                new Point(2.0, 2.0)
        };
        when(boundingBox.getVertices()).thenReturn(vertices);

        when(line.getBoundingBox()).thenReturn(boundingBox);
        when(rectangle.getBoundingBox()).thenReturn(boundingBox);

        selectionBox.addNode(rectangle);
        selectionBox.addNode(line);
    }

    @Test
    public void givenSelectionBoxAndAlignmentLEFTWhenExecuteThenSuccessful() {
        // Given
        AlignCommand alignCommand = new AlignCommand(canvasController, selectionBox, Alignment.LEFT);

        // When
        alignCommand.execute();

        // Then
        verify(canvasController, atLeast(2)).redrawCanvas();
    }

    @Test
    public void givenSelectionBoxAndAlignmentTOPWhenExecuteThenSuccessful() {
        // Given
        AlignCommand alignCommand = new AlignCommand(canvasController, selectionBox, Alignment.TOP);

        // When
        alignCommand.execute();

        // Then
        verify(canvasController, atLeast(2)).redrawCanvas();
    }

    @Test
    public void givenSelectionBoxAndAlignmentRIGHTUndoWhenExecuteWasDoneThenSuccessful() {
        // Given
        AlignCommand alignCommand = new AlignCommand(canvasController, selectionBox, Alignment.RIGHT);

        // When
        alignCommand.execute();
        alignCommand.undo();

        // Then
        Stack<Command> redoStack = ReflectionUtil.get(alignCommand, "redoStack");
        assertEquals(2, redoStack.size());
    }

    @Test
    public void givenSelectionBoxAndAlignmentBOTTOMRedoWhenUndoWasDoneThenSuccessful() {
        // Given
        AlignCommand alignCommand = new AlignCommand(canvasController, selectionBox, Alignment.BOTTOM);

        // When
        alignCommand.execute();
        alignCommand.undo();
        alignCommand.redo();

        // Then
        Stack<Command> redoStack = ReflectionUtil.get(alignCommand, "undoStack");
        assertEquals(2, redoStack.size());
    }

    @Test
    public void whenAlignmentTOPThenGetDescription() {
        // Given
        String expectedDescription = "TOP alignment";

        // When
        AlignCommand alignCommand = new AlignCommand(canvasController, selectionBox, Alignment.TOP);

        // Then
        assertEquals(expectedDescription, alignCommand.getDescription());
    }
}
