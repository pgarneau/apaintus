package apaintus.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Point {
    private double x;
    private double y;

    public Point() {
        this.x = 0;
        this.y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Point pt = (Point) obj;
        if (pt == null)
            return false;
        return (pt.x == this.x && pt.y == this.y);
    }
}
