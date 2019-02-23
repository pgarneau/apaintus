package apaintus.models.nodes;

import apaintus.models.Point;
import apaintus.models.ToolBarAttributes;
import apaintus.models.nodes.shapes.*;
import apaintus.models.nodes.util.FactoryUtil;
import apaintus.models.snapgrid.SnapGrid;
import apaintus.models.toolbar.ActiveTool;

import java.util.List;

public class NodeFactory {
	public static double[] dimensions = new double[]{0, 0};
	private static Point coordinates;

	private NodeFactory() {
	}

	public static <T extends Node> T createNode(ActiveTool activeTool, Point mousePosition, Point lastMouseClickPosition, double[] canvasDimensions) {
		switch (activeTool) {
			case SELECT:
				dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, SelectionBox.STROKE_SIZE);
				coordinates = FactoryUtil.computeShapeCoordinates(mousePosition, lastMouseClickPosition, SelectionBox.STROKE_SIZE);

				return (T) new SelectionBox(
						NodeAttributes
								.builder()
								.withCoordinates(coordinates)
								.withWidth(dimensions[0])
								.withHeight(dimensions[1])
								.withOrientation(0)
								.build()
				);

			default:
				return null;
		}
	}

}
