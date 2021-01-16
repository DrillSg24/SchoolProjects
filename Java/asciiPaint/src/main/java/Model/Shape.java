package Model;

/**
 * Interface defining the minimum methods a shape should have.
 *
 * @author Ihab Tazi 55130.
 */
public interface Shape {

    boolean isInside(Point p);

    void move(double dx, double dy);

    void zoomBy(double factor);
}
