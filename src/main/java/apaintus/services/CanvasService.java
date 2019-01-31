package apaintus.services;

import apaintus.controllers.ToolBarController;
import apaintus.models.Point;
import apaintus.models.shapes.*;
import apaintus.models.shapes.Shape;
import apaintus.models.toolbar.ActiveTool;
import apaintus.services.draw.DrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

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
        }

        return ShapeFactory.createShape(
                shapeType,
                mousePosition,
                mousePosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize());

    }

    public void updateShape(Shape shape, MouseEvent mouseEvent, Point lastMouseClickPosition) {
        Point mousePosition = getMousePosition(mouseEvent);

        ShapeFactory.updateShape(
                shape,
                mousePosition,
                lastMouseClickPosition,
                toolBarController.getFillColor().toString(),
                toolBarController.getStrokeColor().toString(),
                toolBarController.getStrokeSize());
    }

    public void draw(GraphicsContext context, DrawableShape shape) {
        clear(context);
        DrawService drawService = shape.getDrawService();
        DrawService tempDrawService = shape.getBoundingBox().getDrawService();
        drawService.draw(context);
        tempDrawService.draw(context);
    }

    public void clear(GraphicsContext context) {
        context.clearRect(0, 0, 9999, 9999);
    }

    public Point getMousePosition(MouseEvent event) {
        return new Point(event.getX(), event.getY());
    }
}
