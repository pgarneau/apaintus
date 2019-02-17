package apaintus.models;

public enum Attribute {
    COORDINATE_X,
    COORDINATE_Y,
    WIDTH,
    HEIGHT,
    ORIENTATION,
    FILL_COLOR {
        public String toString() {
            return "fillColor";
        }
    },
    STROKE_COLOR {
        public String toString() {
            return "strokeColor";
        }
    },
    STROKE_SIZE {
        public String toString() {
            return "strokeSize";
        }
    },
    TEXT {
        public String toString() {
            return "text";
        }
    }
}
