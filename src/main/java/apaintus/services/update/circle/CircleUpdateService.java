package apaintus.services.update.circle;

import apaintus.models.Attribute;
import apaintus.models.shapes.Circle;
import apaintus.services.update.UpdateService;

public class CircleUpdateService extends UpdateService {
    public CircleUpdateService(Circle shape) {
        super(shape);
        this.fillColor = shape.getFillColor();
    }

    @Override
    public void update(Attribute attribute, String value) {
        super.update(attribute, value);

        switch (attribute) {
            case FILL_COLOR:
                this.fillColor = value;
                break;
        }

        updateShape();
    }

    @Override
    public void update(Attribute attribute, double value) {
        super.update(attribute, value);

        updateShape();
    }
}
