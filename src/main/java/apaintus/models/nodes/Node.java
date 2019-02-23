package apaintus.models.nodes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.update.UpdateService;

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

	protected Node() {
	}

	protected Node(NodeAttributes nodeAttributes) {
		coordinates = nodeAttributes.getCoordinates();
		width = nodeAttributes.getWidth();
		height = nodeAttributes.getHeight();
		orientation = nodeAttributes.getOrientation();
		computeCenter();
		boundingBox = new BoundingBox();
	}

	public abstract DrawService getDrawService();

	public abstract UpdateService getUpdateService();

	public abstract void updateBoundingBox();

	public List<Node> getNodeList() {
		return null;
	}

	public void addNode(Node node) {}

	public void update(NodeAttributes nodeAttributes) {
		coordinates = nodeAttributes.getCoordinates();
		width = nodeAttributes.getWidth();
		height = nodeAttributes.getHeight();
		orientation = nodeAttributes.getOrientation();
		computeCenter();
		updateBoundingBox();
	}

	public void computeCenter() {
		double radianOrientation = Math.toRadians(orientation);
		double tempWidth = width / 2;
		double tempHeight = height / 2;

		center = new Point(
				coordinates.getX() + (tempWidth * Math.cos(radianOrientation) + tempHeight * Math.sin(radianOrientation)),
				coordinates.getY() - (tempWidth * Math.sin(radianOrientation) - tempHeight * Math.cos(radianOrientation))
		);
	}

	public void computeCoordinates() {
		double radianOrientation = Math.toRadians(orientation);
		double tempWidth = -width / 2;
		double tempHeight = -height / 2;

		coordinates = new Point(
				center.getX() + (tempWidth * Math.cos(radianOrientation) + tempHeight * Math.sin(radianOrientation)),
				center.getY() - (tempWidth * Math.sin(radianOrientation) - tempHeight * Math.cos(radianOrientation))
		);
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
