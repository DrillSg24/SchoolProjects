package Model;

import java.util.List;

/**
 * Method representing the model of the application.
 *
 * @author Ihab Tazi 55130.
 */
public class AsciiPaint implements Model {

    private Drawing drawing;

    public AsciiPaint() {
        drawing = new Drawing();
    }

    public AsciiPaint(int width, int height) {
        drawing = new Drawing(width, height);
    }

    /**
     * Replaces the current drawing with an new one.
     *
     * @param width Width of the new drawing.
     * @param height Height of the new drawing.
     */
    @Override
    public void startNewDrawing(int width, int height) {
        this.drawing = new Drawing(width, height);
    }

    /**
     * Creates a new circle and adds it to the shapes list.
     *
     * @param x x coordinate of the circle.
     * @param y y coordinate of the circle.
     * @param radius radius of the circle.
     * @param color colour if the circle.
     */
    @Override
    public void newCircle(double x, double y, double radius, char color) {
        drawing.addShape(new Circle(new Point(x, y),
                radius, color));
    }

    /**
     * Creates a new rectangle and adds it to the shapes list.
     *
     * @param x x coordinate of the upper left corner
     * @param y y coordinate of the upper left corner
     * @param width width of the rectangle
     * @param height height of the rectangle
     * @param color colour of the rectangle
     */
    @Override
    public void newRectangle(
            double x, double y, double width, double height, char color) {
        drawing.addShape(new Rectangle(new Point(x, y),
                width, height, color));
    }

    /**
     * Creates a new square and adds it to the shapes list.
     *
     * @param x x coordinate of the upper left corner
     * @param y y coordinate of the upper left corner
     * @param side side of the square
     * @param color colour of the square
     */
    @Override
    public void newSquare(double x, double y, double side, char color) {
        drawing.addShape(new Square(new Point(x, y),
                side, color));
    }

    /**
     * Creates a new line and adds it to the shapes list.
     *
     * @param x1 x coordinate of the 1st point
     * @param y1 y coordinate of the 1st point
     * @param x2 x coordinate of the 2nd point
     * @param y2 y coordinate of the 2nd point
     * @param color colour of the line
     */
    @Override
    public void newLine(double x1, double y1, double x2, double y2, char color) {
        drawing.addShape(new Line(color, x1, y1, x2, y2));
    }

    /**
     * Gets the index of the potential next shape.
     *
     * @return index of the last shape + 1
     */
    @Override
    public int getNextAvailableShapeIndex() {
        return drawing.getShapes().size();
    }

    /**
     * Gets the shapes in the current drawing.
     *
     * @return copy of the shapes in the drawing, for read only.
     */
    @Override
    public List<ColoredShape> getCurrentShapes() {
        return drawing.getShapes();
    }

    /**
     * Moves the given shape by the given offsets.
     *
     * @param indexOfShape index of the shape
     * @param dx x offset
     * @param dy y offset
     */
    @Override
    public void moveShape(int indexOfShape, double dx, double dy) {
        drawing.getShapeByIndex(indexOfShape).move(dx, dy);
    }

    /**
     * Zooms / un-zooms the given shape by the given factor.
     *
     * @param indexOfShape index of the shape
     * @param factor factor of the zoom, throws an exception if factor is not
     * strictly positive.
     */
    @Override
    public void zoomShape(int indexOfShape, double factor) {
        drawing.getShapeByIndex(indexOfShape).zoomBy(factor);
    }

    /**
     * Removes the given shape from the drawing.
     *
     * @param chosenShapeIndex index of the shape
     */
    @Override
    public void removeShape(int chosenShapeIndex) {
        drawing.removeShape(chosenShapeIndex);
    }

    /**
     * Changes the colour of the given shape.
     *
     * @param indexOfShape index of the shape
     * @param newColor new colour char
     */
    @Override
    public void changeShapeColor(int indexOfShape, char newColor) {
        drawing.getShapeByIndex(indexOfShape).changeColor(newColor);
    }

    /**
     * Formats the current drawing as a string to be displayed by the View when
     * called upon by the controller.
     *
     * @return the current Drawing as a single string.
     */
    @Override
    public String asAscii() {
        StringBuilder stringBuilder = new StringBuilder();
        Point currentPoint;
        ColoredShape currentShape;
        stringBuilder.append(" -".repeat(drawing.getWidth())).append(" -\n");
        for (int i = 0; i < drawing.getHeight(); i++) {
            stringBuilder.append("| ");
            for (int j = 0; j < drawing.getWidth(); j++) {
                currentPoint = new Point(j, i);
                currentShape = drawing.getShapeAt(currentPoint);
                stringBuilder.append(currentShape == null
                        ? "  " : currentShape.getColor()
                        + " ");
            }
            stringBuilder.append("|\n");
        }
        stringBuilder.append(" -".repeat(drawing.getWidth())).append(" -\n");
        return stringBuilder.toString();
    }

}
