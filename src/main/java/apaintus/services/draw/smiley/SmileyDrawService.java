package apaintus.services.draw.smiley;

import apaintus.models.nodes.shapes.Smiley;
import apaintus.services.draw.ShapeDrawService;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.ArcType;

public class SmileyDrawService extends ShapeDrawService {
    private Smiley smiley;

    public SmileyDrawService(Smiley smiley) {
        super(smiley);
        this.smiley = smiley;
    }

    public void draw(GraphicsContext context) {
        double width = smiley.getWidth();
        double height = smiley.getHeight();

        super.draw(context);

        context.setFill(Paint.valueOf(smiley.getFillColor()));
        context.strokeOval(coordinates.getX(), coordinates.getY(), width, height);
        context.fillOval(coordinates.getX() + strokeSize / 2 - 1, coordinates.getY() + strokeSize / 2 - 1, width - strokeSize + 2, height - strokeSize + 2);

        // If StrokeColor is set to transparent, make it match with canvas background
        if (smiley.getStrokeColor().equals("0x00000000")) {
            smiley.setStrokeColor(Color.WHITE.toString());
            context.setStroke(Paint.valueOf(smiley.getStrokeColor()));
        }

        context.strokeArc(coordinates.getX() + width / 4, coordinates.getY() + height / 3, width / 2, height / 2, 200, 140, ArcType.OPEN);
        context.setFill(Paint.valueOf(smiley.getStrokeColor()));
        context.fillOval(coordinates.getX() + width / 4.5, coordinates.getY() + height / 4.5, width / 6 , height / 6);
        context.fillOval(coordinates.getX() + 16.5 / 27 * width, coordinates.getY() + height / 4.5, width / 6, height / 6);
        context.restore();
    }
}
