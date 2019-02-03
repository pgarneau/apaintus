package apaintus.services.update.smiley;

import apaintus.models.Attribute;
import apaintus.models.shapes.Smiley;
import apaintus.services.update.UpdateService;

public class SmileyUpdateService extends UpdateService {
    public SmileyUpdateService(Smiley shape) {
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
