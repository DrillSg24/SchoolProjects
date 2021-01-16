package Model;

/**
 * Class representing a line. A line is defined by the two points at the bounds.
 *
 * @author Ihab Tazi 55130.
 */
public class Line extends ColoredShape {

    private Point p1, p2;

    public Line(char color, double x1, double y1, double x2, double y2) {
        super(color, "Line");
        this.p1 = new Point(x1, y1);
        this.p2 = new Point(x2, y2);
    }

    /**
     * Checks if the point belongs to the line. i.e that the distance between
     * the point and the continuity of the line (la porteuse ?) is null AND the
     * point is at least closer to centre than the bounds.
     *
     * @param p point to check.
     * @return true if the point belong to the line, false otherwise.
     */
    @Override
    public boolean isInside(Point p) {
        double distanceToP = ((p2.getY() - p1.getY()) * p.getX()
                - (p2.getX() - p1.getX()) * p.getY()
                + p2.getX() * p1.getY()
                - p1.getX() * p2.getY()) / p1.distanceTo(p2);
        double mx = (p1.getX() + p2.getX()) / 2;
        double my = (p1.getY() + p2.getY()) / 2;
        Point m = new Point(mx, my);
        return distanceToP == 0 && p.distanceTo(m) <= p1.distanceTo(m);
    }

    /**
     * Moves the line by moving the two points that defined by the given
     * offsets.
     *
     * @param dx x offset.
     * @param dy y offset.
     */
    @Override
    public void move(double dx, double dy) {
        this.p1 = new Point(p1.getX() + dx, p1.getY() + dy);
        this.p2 = new Point(p2.getX() + dx, p2.getY() + dy);
    }

    /**
     * A line cannot be zoomed.
     *
     * @param factor factor to zoom by, throws exception if factor is not
     * strictly positive.
     */
    @Override
    public void zoomBy(double factor) {
        throw new UnsupportedOperationException("You cannot zoom a line !");
    }

    @Override
    public boolean equals(Object obj) {
        return p1.equals(((Line) obj).p1)
                && p2.equals(((Line) obj).p2);
    }

}
