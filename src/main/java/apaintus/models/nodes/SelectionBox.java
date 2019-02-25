package apaintus.models.nodes;

import apaintus.models.Point;
import apaintus.services.draw.DrawService;
import apaintus.services.draw.selectionBox.SelectionBoxDrawService;
import apaintus.services.update.SelectionBoxUpdateService;
import apaintus.services.update.UpdateService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectionBox extends Node {
	public static final int STROKE_SIZE = 2;

	private List<Node> nodeList = new ArrayList<>();
	private Map<Node, Point> nodeOriginalCoordinates = new HashMap<>();

	public SelectionBox(NodeAttributes nodeAttributes) {
		super(nodeAttributes);
		nodeType = NodeType.SELECTION_BOX;
	}

	@Override
	public void updateBoundingBox() {
		boundingBox.update(
				center,
				width,
				height,
				orientation
		);
	}

	@Override
	public DrawService getDrawService() {
		return new SelectionBoxDrawService(this);
	}

	@Override
	public UpdateService getUpdateService() {
		return new SelectionBoxUpdateService(this);
	}

	@Override
	public List<Node> getNodeList() {
		return nodeList;
	}

	@Override
	public void addNode(Node node) {
		nodeList.add(node);
	}

	public void resize() {
		double left = coordinates.getX() + width;
		double top = coordinates.getY() + height;
		double right = coordinates.getX();
		double bottom = coordinates.getY();

		for (Node node : nodeList) {
			for (Point vertices : node.getBoundingBox().getVertices()) {
				left = (left > vertices.getX()) ? vertices.getX() : left;
				top = (top > vertices.getY()) ? vertices.getY() : top;
				right = (right < vertices.getX()) ? vertices.getX() : right;
				bottom = (bottom < vertices.getY()) ? vertices.getY() : bottom;
			}
		}

		update(NodeAttributes
				.builder()
				.withCoordinates(new Point(left, top))
				.withWidth(right - left)
				.withHeight(bottom - top)
				.build());
	}

	public boolean isDuplicate(SelectionBox selectionBox) {
		if (!nodeList.contains(selectionBox))
			return false;

		if (nodeList.size() - selectionBox.getNodeList().size() == 1 && nodeList.containsAll(selectionBox.getNodeList()))
			return true;

		return false;
	}

	public boolean contains(Node node) {
		for (Point vertex: node.getBoundingBox().getVertices()) {
			if (!boundingBox.contains(vertex)) {
				return false;
			}
		}

		return true;
	}

	public void optimize() {
		for (int index = 0; index < nodeList.size(); index++) {
			if (nodeList.get(index).getNodeType() == NodeType.SELECTION_BOX) {
				nodeList.removeAll(nodeList.get(index).getNodeList());
			}
		}

		for (Node node : nodeList) {
			nodeOriginalCoordinates.put(node, node.getCoordinates());
		}
	}

	private void updateNodeCoordinates(Node node, double orientation) {
		double radianOrientation = Math.toRadians(orientation);

		Point oldCoordinates = nodeOriginalCoordinates.get(node);
		double x = oldCoordinates.getX() - center.getX();
		double y = oldCoordinates.getY() - center.getY();
		node.setCoordinates(new Point(center.getX() + (x * Math.cos(radianOrientation) + y * Math.sin(radianOrientation)), center.getY() - (x * Math.sin(radianOrientation) - y * Math.cos(radianOrientation))));
	}

	@Override
	public void setCoordinates(Point coordinates) {
		double xDiff = coordinates.getX() - this.coordinates.getX();
		double yDiff = coordinates.getY() - this.coordinates.getY();

		this.coordinates = coordinates;
		computeCenter();
		updateBoundingBox();

		for (Node node : nodeList) {
			node.setCoordinates(new Point(node.getCoordinates().getX() + xDiff, node.getCoordinates().getY() + yDiff));
		}
	}

	@Override
	public void setOrientation(double orientation) {
		for (Node node : nodeList) {
			node.setOrientation(node.getOrientation() + orientation - this.orientation);
			updateNodeCoordinates(node, orientation);
			node.computeCenter();
		}

		this.orientation = orientation;
		computeCoordinates();
		updateBoundingBox();
	}
}
