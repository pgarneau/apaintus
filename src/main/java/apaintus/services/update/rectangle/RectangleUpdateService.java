package apaintus.services.update.rectangle;

import apaintus.models.Attribute;
import apaintus.models.shapes.Rectangle;
import apaintus.services.update.UpdateService;

public class RectangleUpdateService extends UpdateService {
    public RectangleUpdateService(Rectangle shape){
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
