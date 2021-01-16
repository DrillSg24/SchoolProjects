package Model;

/**
 * Class representing a square. A square is a rectangle where the width and the
 * height are of equal value, called side.
 *
 * @author Ihab Tazi 55130.
 */
public class Square extends Rectangle {

    public Square(Point upperLeft, double side, char color) {
        super(upperLeft, side, side, color);
    }
}
