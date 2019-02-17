package apaintus.models;

public class ToolBarAttributes {
    private String fillColor;
    private String strokeColor;
    private double strokeSize;

    private ToolBarAttributes() {}

    public ToolBarAttributes(Builder builder) {
        this.fillColor = builder.fillColor;
        this.strokeColor = builder.strokeColor;
        this.strokeSize = builder.strokeSize;
    }

    public String getFillColor() {
        return fillColor;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public double getStrokeSize() {
        return strokeSize;
    }

    public static Builder builder() {
        return new ToolBarAttributes.Builder();
    }

    public static class Builder {
        private String fillColor;
        private String strokeColor;
        private double strokeSize;

        private Builder() {}

        public Builder withFillColor(String fillColor) {
            this.fillColor = fillColor;
            return this;
        }

        public Builder withStrokeColor(String strokeColor) {
            this.strokeColor = strokeColor;
            return this;
        }

        public Builder withStrokeSize(double strokeSize) {
            this.strokeSize = strokeSize;
            return this;
        }

        public ToolBarAttributes build() {
            return new ToolBarAttributes(this);
        }
    }
}

