package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.NodeDrawService;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public abstract class Node {
    protected NodeType nodeType;
    protected BoundingBox boundingBox;
    protected Point coordinates;
    protected Point center;
    protected double width;
    protected double height;
    protected double orientation;
    protected boolean selected = true;

    protected Node() {}

    protected Node(NodeAttributes nodeAttributes) {
        coordinates = nodeAttributes.getCoordinates();
        width = nodeAttributes.getWidth();
        height = nodeAttributes.getHeight();
        orientation = nodeAttributes.getOrientation();
        computeCenter();
        boundingBox = new BoundingBox();
    }

    public abstract DrawService getDrawService();

    public abstract void updateBoundingBox();

    public abstract List<Node> getNodeList();

    public void update(NodeAttributes nodeAttributes) {
        coordinates = nodeAttributes.getCoordinates();
        width = nodeAttributes.getWidth();
        height = nodeAttributes.getHeight();
        orientation = nodeAttributes.getOrientation();
        computeCenter();
        updateBoundingBox();
    }

    public void computeCenter() {
//        center = new Point(coordinates.getX() + width / 2, coordinates.getY() + height / 2);
        double radianOrientation = Math.toRadians(orientation);
        double tempWidth = width / 2;
        double tempHeight = height / 2;

        center =  new Point(
                coordinates.getX() + (tempWidth * Math.cos(radianOrientation) + tempHeight * Math.sin(radianOrientation)),
                coordinates.getY() - (tempWidth * Math.sin(radianOrientation) - tempHeight * Math.cos(radianOrientation))
        );
    }

    public void computeCoordinates() {
        double radianOrientation = Math.toRadians(orientation);
        double tempWidth = -width / 2;
        double tempHeight = -height / 2;

        coordinates =  new Point(
                center.getX() + (tempWidth * Math.cos(radianOrientation) + tempHeight * Math.sin(radianOrientation)),
                center.getY() - (tempWidth * Math.sin(radianOrientation) - tempHeight * Math.cos(radianOrientation))
        );
    }

    public static <V> V get(Object object, String fieldName) {
        Class<?> objectClass = object.getClass();
        while (objectClass != null) {
            try {
                Field field = objectClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                return (V) field.get(object);
            } catch (NoSuchFieldException e) {
                objectClass = objectClass.getSuperclass();
            } catch (Exception e) {
                System.out.println("allo");
            }
        }

        return null;
    }

    public static void set(Object object, String fieldName, Object fieldValue) {
        Class<?> objectClass = object.getClass();
        while (objectClass != null) {
            try {
                Field field = objectClass.getDeclaredField(fieldName);
                field.setAccessible(true);
                field.set(object, fieldValue);
                return;
            } catch (NoSuchFieldException e) {
                objectClass = objectClass.getSuperclass();
            } catch (Exception e) {
                System.out.println("sucks");
            }
        }
    }

    public NodeType getNodeType() {
        return nodeType;
    }

    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    public Point getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Point coordinates) {
        this.coordinates = coordinates;
        computeCenter();
        updateBoundingBox();
    }

    public Point getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        computeCenter();
        updateBoundingBox();
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        computeCenter();
        updateBoundingBox();
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
        computeCoordinates();
        updateBoundingBox();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
