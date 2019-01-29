package apaintus.models.shapes;

public class ShapeAttributes {
    private ShapeType shapeType;
    private double[] coordinates;
    private double[] destinationCoordinates;
    private double width;
    private double height;
    private double orientation;
    private int strokeSize;
    private String fillColor;
    private String strokeColor;

    private ShapeAttributes() {}

    public ShapeAttributes(Builder builder) {
        this.shapeType = builder.shapeType;
        this.coordinates = builder.coordinates;
        this.destinationCoordinates = builder.destinationCoordinates;
        this.width = builder.width;
        this.height = builder.height;
        this.orientation = builder.orientation;
        this.strokeSize = builder.strokeSize;
        this.fillColor = builder.fillColor;
        this.strokeColor = builder.strokeColor;
    }

    public ShapeType getShapeType() { return shapeType; }

    public double[] getCoordinates() {
        return coordinates;
    }

    public double[] getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public double getOrientation() {
        return orientation;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public int getStrokeSize() {
        return strokeSize;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public String getFillColor() {
        return fillColor;
    }

    public static Builder builder() {
        return new ShapeAttributes.Builder();
    }

    public static class Builder {
        private ShapeType shapeType;
        private double[] coordinates;
        private double[] destinationCoordinates;
        private double width;
        private double height;
        private double orientation;
        private int strokeSize;
        private String fillColor;
        private String strokeColor;

        private Builder() {}

        public Builder withShapeType(ShapeType shapeType) {
            this.shapeType = shapeType;
            return this;
        }

        public Builder withCoordinates(double[] coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder withDestinationCoordinates(double[] destinationCoordinates) {
            this.destinationCoordinates = destinationCoordinates;
            return this;
        }

        public Builder withWidth(double width) {
            this.width = width;
            return this;
        }

        public Builder withHeight(double height) {
            this.height = height;
            return this;
        }

        public Builder withOrientation(double orientation) {
            this.orientation = orientation;
            return this;
        }

        public Builder withStrokeSize(int strokeSize) {
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
