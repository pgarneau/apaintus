package apaintus.models.nodes.shapes;

public class ShapeAttributes {
    private double strokeSize;
    private String fillColor;
    private String strokeColor;

    private ShapeAttributes() {}

    public ShapeAttributes(Builder builder) {
        this.strokeSize = builder.strokeSize;
        this.fillColor = builder.fillColor;
        this.strokeColor = builder.strokeColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public double getStrokeSize() {
        return strokeSize;
    }

    public String getFillColor() {
        return fillColor;
    }
    
    public static Builder builder() {
        return new ShapeAttributes.Builder();
    }

    public static class Builder {
        private double strokeSize;
        private String fillColor;
        private String strokeColor;

        private Builder() {}

        public Builder withStrokeSize(double strokeSize) {
            this.strokeSize = strokeSize;
            return this;
        }

        public Builder withFillColor(String fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public Builder withStrokeColor(String strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }
        
        public ShapeAttributes build() {
            return new ShapeAttributes(this);
        }
    }
}
