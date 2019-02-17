package apaintus.models.shapes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.NodeDrawService;

import java.lang.reflect.Field;

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
        center = computeCenter();
    }

    public abstract DrawService getDrawService();

    public abstract void update(NodeAttributes nodeAttributes);

    public Point computeCenter() {
        return new Point(coordinates.getX() + width / 2, coordinates.getY() + height / 2);
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
        update(NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .build());
    }

    public Point getCenter() {
        return center;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
        update(NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .build());
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
        update(NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .build());
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double orientation) {
        this.orientation = orientation;
        update(NodeAttributes
                .builder()
                .withCoordinates(coordinates)
                .withWidth(width)
                .withHeight(height)
                .withOrientation(orientation)
                .build());
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
