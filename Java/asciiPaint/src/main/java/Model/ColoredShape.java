package Model;

/**
 * Class defining a shape that has colour. Same behaviour as a shape if not for
 * the colour attribute.
 *
 * @author Ihab Tazi 55130.
 */
public abstract class ColoredShape implements Shape {

    private char color;
    private String shapeId;

    public ColoredShape(char color, String shapeId) {
        this.color = color;
        this.shapeId = shapeId;
    }

    public char getColor() {
        return this.color;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void changeColor(char newColor) {
        this.setColor(newColor);
    }

    public String getShapeId() {
        return shapeId;
    }

}
