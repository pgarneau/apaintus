package apaintus.services.update.Line;

import apaintus.models.Attribute;
import apaintus.models.shapes.Line;
import apaintus.services.update.UpdateService;

public class LineUpdateService extends UpdateService {
    public LineUpdateService(Line shape) {
        super(shape);
    }

    @Override
    public void update(Attribute attribute, String value) {
        super.update(attribute, value);

        updateShape();
    }

    @Override
    public void update(Attribute attribute, double value) {
        super.update(attribute, value);

        switch (attribute) {
            case STROKE_SIZE:
                this.height = value;
                break;
            case HEIGHT:
                this.strokeSize = value;
                break;
        }

        updateShape();
    }
}
