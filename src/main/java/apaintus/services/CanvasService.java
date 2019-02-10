package apaintus.services;

import apaintus.controllers.ToolBarController;
import apaintus.model.snapgrid.Snapgrid;
import apaintus.models.Point;
import apaintus.models.shapes.*;
import apaintus.models.shapes.Shape;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.draw.DrawService;
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

    public DrawableShape createShape(ActiveTool activeTool, MouseEvent mouseEvent, double[] canvasDimension, Snapgrid snapgrid) {
        Point mousePosition = getMousePosition(mouseEvent);
        ShapeType shapeType = null;

        switch (activeTool) {
            case RECTANGLE:
                shapeType = ShapeType.RECTANGLE;
                break;

            case CIRCLE:
                shapeType = ShapeType.CIRCLE;
                break;

            case LINE:
                shapeType = ShapeType.LINE;
                break;

            case SMILEY:
                shapeType = ShapeType.SMILEY;
                break;

            case TEXT_BOX:
                shapeType = ShapeType.TEXT_BOX;
                break;
        }

        return ShapeFactory.createShape(
                shapeType,
                mousePosition,
                mousePosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize(),
                canvasDimension[0],
                canvasDimension[1]);

    }

    public void updateShape(Shape shape, MouseEvent mouseEvent, Point lastMouseClickPosition, double[] canvasDimension, Snapgrid snapgrid) {
        Point mousePosition = getMousePosition(mouseEvent);
        double strokeSize = toolBarController.getStrokeSize() / 2 + BoundingBox.getBoundingboxStrokeSize();

        ShapeFactory.updateShape(
                shape,
                mousePosition,
                lastMouseClickPosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize(),
                snapgrid,
                canvasDimension[0],
                canvasDimension[1]);
    }

    public void drawShape(GraphicsContext context, DrawableShape shape) {
        DrawService drawService = shape.getDrawService();
        drawService.draw(context);
        if (shape.isSelected()) {
            DrawService tempDrawService = shape.getBoundingBox().getDrawService();
            tempDrawService.draw(context);
        }
    }

    public void drawSnapGrid(GraphicsContext context, Snapgrid snapgrid) {
        context.save();
        context.setStroke(Color.BLACK);

//		//this is used for debugging grid points
//		for(Point pt : snapgrid.getGridPoints()) {
//			context.fillOval(pt.getX(), pt.getY(), 5, 5);
//		}

        context.beginPath();
        context.moveTo(0, 0);

        for (int i = 0; i < (context.getCanvas().getHeight()); i += snapgrid.getSpacing()) {
            context.moveTo(0, i);
            context.lineTo(context.getCanvas().getWidth() + i, i);
        }

        for (int i = 0; i < (context.getCanvas().getWidth()); i += snapgrid.getSpacing()) {
            context.moveTo(i, 0);
            context.lineTo(i, context.getCanvas().getHeight() + i);
        }

        context.stroke();
        context.closePath();

        context.restore();
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
        double strokeSize = toolBarController.getStrokeSize() / 2 + BoundingBox.getBoundingboxStrokeSize();

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
