package apaintus.models.shapes;

import apaintus.models.Point;

public class NodeAttributes {
    private Point coordinates;
    private double width;
    private double height;
    private double orientation;

    private NodeAttributes() {}

    public NodeAttributes(Builder builder) {
        this.coordinates = builder.coordinates;
        this.width = builder.width;
        this.height = builder.height;
        this.orientation = builder.orientation;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public double getOrientation() {
        return orientation;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public static Builder builder() {
        return new NodeAttributes.Builder();
    }

    public static class Builder {
        private Point coordinates;
        private double width;
        private double height;
        private double orientation;

        private Builder() {}

        public Builder withCoordinates(Point coordinates) {
            this.coordinates = coordinates;
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

        public NodeAttributes build() {
            return new NodeAttributes(this);
        }
    }
}

