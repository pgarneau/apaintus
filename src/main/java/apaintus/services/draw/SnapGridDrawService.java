package apaintus.services.draw;

import apaintus.models.snapgrid.SnapGrid;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnapGridDrawService implements DrawService {
    private SnapGrid snapGrid;

    public SnapGridDrawService(SnapGrid snapGrid) {
        this.snapGrid = snapGrid;
    }

    @Override
    public void draw(GraphicsContext context) {
        context.save();
        context.setStroke(Color.BLACK);

        context.beginPath();
        context.moveTo(0, 0);

        for (int i = 0; i < (context.getCanvas().getHeight()); i += snapGrid.getGradation()) {
            context.moveTo(0, i);
            context.lineTo(context.getCanvas().getWidth() + i, i);
        }

        for (int i = 0; i < (context.getCanvas().getWidth()); i += snapGrid.getGradation()) {
            context.moveTo(i, 0);
            context.lineTo(i, context.getCanvas().getHeight() + i);
        }

        context.stroke();
        context.closePath();

        context.restore();
    }
}
