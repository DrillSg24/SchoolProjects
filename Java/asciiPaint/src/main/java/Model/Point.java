package Model;

/**
 * Class representing a point. A point is defined by its x and y coordinates.
 *
 * @author Ihab Tazi 55130.
 */
public class Point {

    private double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this(p.x, p.y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Moves the point by the given offsets.
     *
     * @param dx x offset
     * @param dy y offset
     */
    public void move(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Calculates the distance between this point and the point given as
     * parameter.
     *
     * @param other point to calculate distance to.
     * @return the distance to the other point as a double.
     */
    public double distanceTo(Point other) {
        return Math.sqrt(
                Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        return this.x == ((Point) obj).x
                && this.y == ((Point) obj).y;
    }

}
