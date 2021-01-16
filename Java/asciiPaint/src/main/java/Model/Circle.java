package Model;

import java.util.Objects;

/**
 * Class representing a circle. A circle is defined by its centre and its
 * radius.
 *
 * @author Ihab Tazi 55130.
 */
public class Circle extends ColoredShape {

    private Point center;
    private double radius;

    public Circle(Point center, double radius, char color) {
        super(color, "Circle");
        this.center = center;
        if (radius <= 0) {
            throw new IllegalArgumentException(
                    "Radius must be positive ! " + radius);
        }
        this.radius = radius;
    }

    /**
     * Check if the point is inside the circle, i.e if the distance to the
     * centre is at most equal to the radius.
     *
     * @param p the point to check.
     * @return true if the point is inside the circle, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        return p.distanceTo(center) <= radius;
    }

    /**
     * Moves the circle (by changing its centre to a new one defined by the
     * offsets).
     *
     * @param dx x offset.
     * @param dy y offset.
     */
    @Override
    public void move(double dx, double dy) {
        this.center = new Point(center.getX() + dx, center.getY() + dy);
    }

    /**
     * Zooms/Un-zooms the circle by the given factor. i.e multiplying the radius
     * by the factor.
     *
     * @param factor zoom factor. Throws an exception if factor is not strictly
     * positive.
     */
    @Override
    public void zoomBy(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException(
                    "Factor must be strictly positive ! " + factor);
        }
        this.radius *= factor;
    }

    @Override
    public boolean equals(Object obj) {
        return this.center.equals(((Circle) obj).center) && this.radius == ((Circle) obj).radius;
    }

    @Override
    public String toString() {
        return "C :" + this.center + " r " + this.radius;
    }
}
