package Model;

import java.util.List;

/**
 *
 * @author Ihab Tazi 55130.
 */
public interface Model {

    public void startNewDrawing(int width, int height);

    public void newCircle(double x, double y, double radius, char color);

    public void newRectangle(
            double x, double y, double width, double height, char color);

    public void newSquare(double x, double y, double side, char color);

    public void newLine(double x1, double y1, double x2, double y2, char color);

    public int getNextAvailableShapeIndex();

    public List<ColoredShape> getCurrentShapes();

    public void moveShape(int indexOfShape, double dx, double dy);

    public void zoomShape(int indexOfShape, double factor);

    public void removeShape(int chosenShapeIndex);

    public void changeShapeColor(int indexOfShape, char newColor);

    public String asAscii();

}
