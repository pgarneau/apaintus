package apaintus.models.shapes;

import apaintus.models.Point;

public class ShapeAttributes {
    private Point coordinates;
    private Point destinationCoordinates;
    private double width;
    private double height;
    private double orientation;
    private double strokeSize;
    private String fillColor;
    private String strokeColor;
    private String text;

    private ShapeAttributes() {}

    public ShapeAttributes(Builder builder) {
        this.coordinates = builder.coordinates;
        this.destinationCoordinates = builder.destinationCoordinates;
        this.width = builder.width;
        this.height = builder.height;
        this.orientation = builder.orientation;
        this.strokeSize = builder.strokeSize;
        this.fillColor = builder.fillColor;
        this.strokeColor = builder.strokeColor;
        this.text = builder.text;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public Point getDestinationCoordinates() {
        return destinationCoordinates;
    }

    public double getOrientation() {
        return orientation;
    }

    public String getStrokeColor() {
        return strokeColor;
    }

    public double getStrokeSize() {
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
    
    public String getText() {
        return text;
    }

    public static Builder builder() {
        return new ShapeAttributes.Builder();
    }

    public static class Builder {
        private Point coordinates;
        private Point destinationCoordinates;
        private double width;
        private double height;
        private double orientation;
        private double strokeSize;
        private String fillColor;
        private String strokeColor;
        private String text;

        private Builder() {}

        public Builder withCoordinates(Point coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public Builder withDestinationCoordinates(Point destinationCoordinates) {
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
        
        public Builder withText(String text) {
            this.text = text;
            return this;
        }

        public ShapeAttributes build() {
            return new ShapeAttributes(this);
        }
    }
}
