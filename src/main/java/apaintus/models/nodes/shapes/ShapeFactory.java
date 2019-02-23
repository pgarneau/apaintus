package apaintus.models.nodes.shapes;

import apaintus.models.Point;
import apaintus.models.ToolBarAttributes;
import apaintus.models.nodes.NodeAttributes;
import apaintus.models.nodes.util.FactoryUtil;
import apaintus.models.toolbar.ActiveTool;

public class ShapeFactory {
	public static double[] dimensions = new double[]{0, 0};
	private static Point coordinates;
	private static double orientation;

	private ShapeFactory() {
	}

	public static Shape createShape(ActiveTool activeTool, Point mousePosition, Point lastMouseClickPosition, ToolBarAttributes toolBarAttributes, double[] canvasDimensions) {
		switch (activeTool) {
			case RECTANGLE:
				dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
				coordinates = FactoryUtil.computeShapeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

				return new Rectangle(
						NodeAttributes
								.builder()
								.withCoordinates(coordinates)
								.withWidth(dimensions[0])
								.withHeight(dimensions[1])
								.withOrientation(0)
								.build(),
						ShapeAttributes
								.builder()
								.withFillColor(toolBarAttributes.getFillColor())
								.withStrokeColor(toolBarAttributes.getStrokeColor())
								.withStrokeSize(toolBarAttributes.getStrokeSize())
								.build()
				);

			case CIRCLE:
				dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
				coordinates = FactoryUtil.computeShapeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

				return new Circle(
						NodeAttributes
								.builder()
								.withCoordinates(coordinates)
								.withWidth(dimensions[0])
								.withHeight(dimensions[1])
								.withOrientation(0)
								.build(),
						ShapeAttributes
								.builder()
								.withFillColor(toolBarAttributes.getFillColor())
								.withStrokeColor(toolBarAttributes.getStrokeColor())
								.withStrokeSize(toolBarAttributes.getStrokeSize())
								.build()
				);

			case LINE:
				orientation = FactoryUtil.computeOrientation(mousePosition, lastMouseClickPosition);
				dimensions[0] = FactoryUtil.computeLineWidth(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

				return new Line(
						NodeAttributes
								.builder()
								.withCoordinates(lastMouseClickPosition)
								.withWidth(dimensions[0])
								.withHeight(toolBarAttributes.getStrokeSize())
								.withOrientation(orientation)
								.build(),
						ShapeAttributes
								.builder()
								.withStrokeColor(toolBarAttributes.getStrokeColor())
								.withStrokeSize(toolBarAttributes.getStrokeSize())
								.build()
				);

			case SMILEY:
				dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
				coordinates = FactoryUtil.computeShapeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

				return new Smiley(
						NodeAttributes
								.builder()
								.withCoordinates(coordinates)
								.withWidth(dimensions[0])
								.withHeight(dimensions[1])
								.withOrientation(0)
								.build(),
						ShapeAttributes
								.builder()
								.withFillColor(toolBarAttributes.getFillColor())
								.withStrokeColor(toolBarAttributes.getStrokeColor())
								.withStrokeSize(toolBarAttributes.getStrokeSize())
								.build()
				);

			case TEXT_BOX:
				dimensions = FactoryUtil.computeDimensions(mousePosition, lastMouseClickPosition, canvasDimensions, toolBarAttributes.getStrokeSize());
				coordinates = FactoryUtil.computeShapeCoordinates(mousePosition, lastMouseClickPosition, toolBarAttributes.getStrokeSize());

				return new TextBox(
						NodeAttributes
								.builder()
								.withCoordinates(coordinates)
								.withWidth(dimensions[0])
								.withHeight(dimensions[1])
								.withOrientation(0)
								.build(),
						ShapeAttributes
								.builder()
								.withFillColor(toolBarAttributes.getFillColor())
								.withStrokeColor(toolBarAttributes.getStrokeColor())
								.withStrokeSize(toolBarAttributes.getStrokeSize())
								.build()
				);

			default:
				return null;
		}
	}
}
