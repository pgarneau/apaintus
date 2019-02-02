package apaintus.services;

import apaintus.controllers.CanvasController;
import apaintus.controllers.ToolBarController;
import apaintus.models.Point;
import apaintus.models.shapes.*;
import apaintus.models.shapes.Shape;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.draw.DrawService;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasService {
    ToolBarController toolBarController;

    public CanvasService(ToolBarController toolBarController) {
        this.toolBarController = toolBarController;
    }

    public DrawableShape createShape(ActiveTool activeTool, MouseEvent mouseEvent) {
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
        }

        return ShapeFactory.createShape(
                shapeType,
                mousePosition,
                mousePosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize());

    }

    public void updateShape(Shape shape, MouseEvent mouseEvent, Point lastMouseClickPosition, double [] canvasDimension) {
        Point mousePosition = getMousePosition(mouseEvent);
        double strokeSize = toolBarController.getStrokeSize()/2 + BoundingBox.getBoundingboxStrokeSize();

		double xPos = mousePosition.getX() > canvasDimension[0] ? canvasDimension[0] : mousePosition.getX();
		double yPos = mousePosition.getY() > canvasDimension[1] ? canvasDimension[1] : mousePosition.getY(); 
		
        xPos -= strokeSize;
        yPos -= strokeSize;

		if (xPos - strokeSize <= 0)
			xPos = strokeSize;
		if (yPos - strokeSize <= 0)
			yPos = strokeSize;        
		
        Point maxPos = new Point(xPos,yPos);
        ShapeFactory.updateShape(
                shape,
                maxPos,
                lastMouseClickPosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize());
    }

    public void draw(GraphicsContext context, DrawableShape shape) {
        DrawService drawService = shape.getDrawService();
        drawService.draw(context);
        if (shape.isSelected()) {
            DrawService tempDrawService = shape.getBoundingBox().getDrawService();
            tempDrawService.draw(context);
        }
    }

    public void saveState(GraphicsContext canvasContext, WritableImage image) {
            // Dunno what this does
//            if (topLeft[0] < 0)
//                topLeft = new double[] {0, topLeft[1]};
//            if (topLeft[1] < 0)
//                topLeft = new double[] {topLeft[0], 0};

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
}
