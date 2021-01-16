package Controller;

/**
 * The controller of the application.
 *
 * @author Ihab Tazi 55130.
 */
import Model.AsciiPaint;
import Model.Model;
import View.InterfaceView;
import View.View;

public class Application {

    private Model paint;
    private InterfaceView view;

    /**
     * Main method of the application, asks the user for the drawing's
     * dimensions and starts a canvas with them. Keeps asking the user for
     * commands and executes them until exit is given as command.
     *
     */
    public void start() {
        this.view = new View();
        this.paint = new AsciiPaint(
                view.getPositiveInteger("Choose the drawing width :"),
                view.getPositiveInteger("Choose the drawing height :"));
        view.displayCurrentGame(paint);
        try {
            String[] commands = view.getCommands(
                    "Please enter a valid command : add <shape>, show, move, "
                    + "zoom, remove, color, clear or exit to quit the program");
            while (!isExitCalled(commands)) {
                execute(commands);
                commands = view.getCommands(
                        "Please enter a valid command : add <shape>, show, move"
                        + ", zoom, remove, color, clear or exit to quit the "
                        + "program");
            }
            view.displayMessage("End of the program");
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    /**
     * Check if exit was asked by the user.
     *
     * @param commands Array of the different words of the user-given command
     * @return true if the user asked to exit, false otherwise.
     */
    private boolean isExitCalled(String[] commands) {
        return commands[0].toLowerCase().equals("exit");
    }

    /**
     * Method that executes the commands given by the user. Depending on the
     * first word of the command, calls different handlers to execute them, or
     * throw an error if the command array is null.
     *
     * @param commands Array of words from the user-given command.
     */
    private void execute(String[] commands) {
        if (commands == null) {
            throw new IllegalArgumentException("Invalid commands array");
        }
        switch (commands[0].toLowerCase()) {
            case "add":
                addHandler(commands);
                break;
            case "zoom":
                zoomHandler();
                break;
            case "move":
                moveHandler();
                break;
            case "remove":
                removeHandler();
                break;
            case "color":
                changeColorHandler();
                break;
            case "show":
                view.displayCurrentGame(paint);
                break;
            case "clear":
                clearHandler();
                view.displayCurrentGame(paint);
                break;
            case " exit":
            default:
                break;
        }
    }

    /**
     * Creates a new circle using the format add circle x y r c, where x y are
     * the coordinates of the centre, r is the radius and c is the colour.
     * Throws an error if the format is not respected.
     *
     * @param commands Array of Strings from the user-given command
     */
    private void paintCircle(String[] commands) {
        if (commands.length == 6) {
            int x = Integer.parseInt(commands[2]);
            int y = Integer.parseInt(commands[3]);
            double radius = Double.parseDouble(commands[4]);
            char color = commands[5].charAt(0);
            paint.newCircle(x, y, radius, color);
        } else {
            throw new IllegalArgumentException(
                    "Format must be : add circle x y radius color");
        }
    }

    /**
     * Creates a new square using the format add square x y s c, where x y are
     * the coordinates of the upperLeft corner, s is the side and c is the
     * colour. Throws an error if the format is not respected.
     *
     * @param commands Array of Strings from the user-given command
     */
    private void paintSquare(String[] commands) {
        if (commands.length == 6) {
            int x = Integer.parseInt(commands[2]);
            int y = Integer.parseInt(commands[3]);
            double size = Double.parseDouble(commands[4]);
            char color = commands[5].charAt(0);
            paint.newSquare(x, y, size, color);
        } else {
            throw new IllegalArgumentException(
                    "Format must be : add square x y side color");
        }
    }

    /**
     * Creates a new square using the format add square x y w h c, where x y are
     * the coordinates of the upperLeft corner, w is the width , h is the height
     * and c is the colour. Throws an error if the format is not respected.
     *
     * @param commands Array of Strings from the user-given command
     */
    private void paintRectangle(String[] commands) {

        if (commands.length == 7) {
            int x = Integer.parseInt(commands[2]);
            int y = Integer.parseInt(commands[3]);
            double width = Double.parseDouble(commands[4]);
            double height = Double.parseDouble(commands[5]);
            char color = commands[6].charAt(0);
            paint.newRectangle(x, y, width, height, color);
        } else {
            throw new IllegalArgumentException("format must be : "
                    + "add rectangle x y width height color");
        }
    }

    /**
     * Creates a new square using the format add line x1 y1 x2 x2, where x1 y1
     * are the coordinates of the 1st point, x2 y2 are the coordinates of the
     * 2nd point and c is the colour. Throws an error if the format is not
     * respected.
     *
     * @param commands Array of Strings from the user-given command
     */
    private void paintLine(String[] commands) {

        if (commands.length == 7) {
            double x1 = Integer.parseInt(commands[2]);
            double y1 = Integer.parseInt(commands[3]);
            double x2 = Double.parseDouble(commands[4]);
            double y2 = Double.parseDouble(commands[5]);
            char color = commands[6].charAt(0);
            paint.newLine(x1, y1, x2, y2, color);
        } else {
            throw new IllegalArgumentException("format must be : "
                    + "add rectangle x y width height color");
        }
    }

    /**
     * Handles the change colour command, asks the user to chose a shape and the
     * new colour, and proceeds to change the colour.
     */
    private void changeColorHandler() {
        paint.changeShapeColor(
                view.getChosenShapeIndex(paint),
                view.getChar("new color ? (must be a single character) "));
        view.displayMessage("The color of the chosen shape was changed !");
    }

    /**
     * Handles the move command, asks the user to chose a shape and the dx and
     * dy offsets, and proceeds to move the shape accordingly.
     */
    private void moveHandler() {
        paint.moveShape(view.getChosenShapeIndex(paint), view.getDouble("dx ? "),
                view.getDouble("dy ? "));
        view.displayMessage("THe chosen shape was moved !");
    }

    /**
     * Handles the zoom command, asks the user to chose a shape and a
     * zoom/un-zoom factor, and proceeds to deform the shape accordingly.
     */
    private void zoomHandler() {
        paint.zoomShape(view.getChosenShapeIndex(paint),
                view.getDouble("zoom factor ? (positive only !) "));
        view.displayMessage("The chosen shape was zoomed !");
    }

    /**
     * Handles the remove command, asks the user to chose a shape and proceeds
     * to remove the shape accordingly.
     */
    private void removeHandler() {
        paint.removeShape(view.getChosenShapeIndex(paint));
        view.displayMessage("The chosen shape was removed !");
    }

    /**
     * Handles the clear command, asks the user for new dimensions, and proceeds
     * to clear and initialise a new drawing with those dimensions.
     */
    private void clearHandler() {
        paint.startNewDrawing(
                view.getPositiveInteger("Choose the new drawing width :"),
                view.getPositiveInteger("Choose the new drawing height :")
        );
        view.displayMessage("A new drawing was created !");
    }

    /**
     * Handles the add command, checks the second word for the shape to add and
     * calls on the different paint methods accordingly.
     */
    private void addHandler(String[] commands) {
        if (commands.length == 1) {
            throw new IllegalArgumentException("No arguments to add !");
        } else {
            switch (commands[1].trim().toLowerCase()) {
                case "rectangle":
                    view.displayMessage(
                            "You chose to draw a rectangle. "
                            + "It will be Shape number "
                            + paint.getNextAvailableShapeIndex());
                    paintRectangle(commands);
                    break;
                case "square":
                    view.displayMessage(
                            "You chose to draw a square. "
                            + "It will be Shape number "
                            + paint.getNextAvailableShapeIndex());
                    paintSquare(commands);
                    break;
                case "circle":
                    view.displayMessage(
                            "You chose to draw a circle. "
                            + "It will be Shape number "
                            + paint.getNextAvailableShapeIndex());
                    paintCircle(commands);
                    break;
                case "line":
                    view.displayMessage(
                            "You chose to draw a line. "
                            + "It will be Shape number "
                            + paint.getNextAvailableShapeIndex());
                    paintLine(commands);
                    break;
                default:
                    throw new IllegalArgumentException(
                            "Unrecognised command !"
                            + commands[1].trim().toLowerCase());
            }
        }
    }

}
