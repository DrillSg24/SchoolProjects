package Model;

/**
 * Class representing the drawing. A drawing has specific dimensions, and a list
 * of shapes inside it.
 *
 * @author Ihab Tazi 55130.
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drawing {

    private final List<ColoredShape> shapes;
    private final int width;
    private final int height;

    public Drawing() {
        shapes = new ArrayList<>();
        this.height = 10;
        this.width = 10;
    }

    public Drawing(int width, int height) {
        shapes = new ArrayList<>();
        this.width = width;
        this.height = height;
    }

    public List<ColoredShape> getShapes() {
        return Collections.unmodifiableList(shapes);
    }

    int getWidth() {
        return this.width;
    }

    int getHeight() {
        return this.height;
    }

    /**
     * Adds the given shape to the drawing.
     *
     * @param shape Shape to add.
     */
    public void addShape(ColoredShape shape) {
        shapes.add(shape);
    }

    /**
     * Removes the given shape from the drawing.
     *
     * @param index index of the shape, throws exception if index is out of
     * bounds
     */
    public void removeShape(int index) {
        if (shapes.isEmpty() || index < 0) {
            throw new IllegalArgumentException(
                    "Invalid index for the removal ! " + index);
        }
        shapes.remove(index);
    }

    /**
     * Gets the shape at the given point. If the point is inside multiples
     * shapes, returns the oldest one.
     *
     * @param p point to get the shape at.
     * @return the oldest shape to which the point belongs, null otherwise.
     */
    public ColoredShape getShapeAt(Point p) {
        for (ColoredShape shape : shapes) {
            if (shape.isInside(p)) {
                return shape;
            }
        }
        return null;
    }

    /**
     * Gets the shape by the index in the shapes list.
     *
     * @param index the chosen index, throws exception if index is out of bounds
     * @return the shape at the given shape.
     */
    public ColoredShape getShapeByIndex(int index) {
        if (index < 0 || index >= shapes.size()) {
            throw new IllegalArgumentException("Index is incorrect ! " + index);
        }
        return Collections.unmodifiableList(shapes).get(index);
    }
}
