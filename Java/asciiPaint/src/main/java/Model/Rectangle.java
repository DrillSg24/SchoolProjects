package Model;

/**
 * Class representing a rectangle. A rectangle is defined by its upper left
 * corner, a width and a height.
 *
 * @author Ihab Tazi 55130.
 */
public class Rectangle extends ColoredShape {

    private Point upperLeft;
    private double width, height;

    public Rectangle(Point upperLeft, double width, double height, char color) {
        super(color, width == height ? "Square" : "Rectangle");
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException(
                    "Height and width must be positive ! width "
                    + width + ", height " + height);
        }
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }

    /**
     * Checks if the point is inside the rectangle. i.e the x coordinate and the
     * y coordinate are both inside the boundaries of the rectangle.
     *
     * @param p point to check.
     * @return true if the point is inside the rectangle, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        return p.getX() >= upperLeft.getX()
                && p.getX() <= upperLeft.getX() + height
                && p.getY() >= upperLeft.getY()
                && p.getY() <= upperLeft.getY() + width;
    }

    /**
     * Moves the rectangle by changing its upperLeft coordinates by the given
     * offsets.
     *
     * @param dx x offset
     * @param dy y offset
     */
    @Override
    public void move(double dx, double dy) {
        this.upperLeft
                = new Point(upperLeft.getX() + dx, upperLeft.getY() + dy);
    }

    /**
     * Zooms/un-zooms the rectangle by multiplying both the width and the height
     * by the given factor.
     *
     * @param factor factor to zoom/un-zoom by, throws exception if the factor
     * is not strictly positive.
     */
    @Override
    public void zoomBy(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Factor cannot be negative ! "
                    + factor);
        }
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public boolean equals(Object obj) {
        Rectangle other = (Rectangle) obj;
        return other.upperLeft.equals(this.upperLeft)
                && other.height == this.height
                && other.width == this.width;
    }

}
