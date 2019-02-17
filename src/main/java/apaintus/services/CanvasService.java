package apaintus.services;

import apaintus.controllers.ToolBarController;
import apaintus.models.ToolBarAttributes;
import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.Point;
import apaintus.models.shapes.*;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.ShapeDrawService;
import apaintus.services.draw.SnapGridDrawService;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasService {
    ToolBarController toolBarController;

    public CanvasService(ToolBarController toolBarController) {
        this.toolBarController = toolBarController;
    }

    public Node createNode(ActiveTool activeTool, MouseEvent mouseEvent, double[] canvasDimensions) {
        Point mousePosition = getMousePosition(mouseEvent);

        return NodeFactory.createNode(
                activeTool,
                mousePosition,
                mousePosition,
                ToolBarAttributes
                        .builder()
                        .withFillColor(toolBarController.getFillColor().toString())
                        .withStrokeColor(toolBarController.getStrokeColor().toString())
                        .withStrokeSize(toolBarController.getStrokeSize())
                        .build(),
                canvasDimensions
        );
    }

    public void updateNode(Node node, MouseEvent mouseEvent, Point lastMouseClickPosition, double[] canvasDimensions, SnapGrid snapGrid) {
        Point mousePosition = getMousePosition(mouseEvent);

        NodeFactory.updateNode(
                node,
                mousePosition,
                lastMouseClickPosition,
                canvasDimensions,
                snapGrid
        );
    }

    public void drawNode(GraphicsContext context, Node node) {
        DrawService drawService = node.getDrawService();
        drawService.draw(context);
    }

    public void drawSnapGrid(GraphicsContext context, SnapGrid snapGrid) {
        DrawService drawService = new SnapGridDrawService(snapGrid);
        drawService.draw(context);
    }

    public void saveState(GraphicsContext canvasContext, Image image) {
        canvasContext.drawImage(image, 0, 0);
    }

    public WritableImage convertCanvasToImage(Canvas canvas) {
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.TRANSPARENT);

        return canvas.snapshot(params, null);
    }

    public void clear(GraphicsContext context) {
        context.clearRect(0, 0, 9999, 9999);
    }

    public Point getMousePosition(MouseEvent event) {
        return new Point(event.getX(), event.getY());
    }

    private Point computeInboundMousePosition(Point mousePosition, double[] dimensions) {
        double strokeSize = toolBarController.getStrokeSize() / 2 + BoundingBox.STROKE_SIZE;

        double xPos = mousePosition.getX() > dimensions[0] ? dimensions[0] : mousePosition.getX();
        double yPos = mousePosition.getY() > dimensions[1] ? dimensions[1] : mousePosition.getY();

        xPos -= strokeSize;
        yPos -= strokeSize;

        if (xPos - strokeSize <= 0)
            xPos = strokeSize;
        if (yPos - strokeSize <= 0)
            yPos = strokeSize;

        return new Point(xPos, yPos);
    }
}
