package View;

import Model.ColoredShape;
import Model.Model;
import java.util.List;
import java.util.Scanner;

/**
 * View class, handles the view aspects of the application, and contains
 * different user prompt methods.
 *
 * @author g55130
 */
public class View implements InterfaceView {

    public View() {
    }

    /**
     * Gets a positive integer from the user.
     *
     * @param message Message to display to the user.
     * @return the positive integer given by the user.
     */
    @Override
    public int getPositiveInteger(String message) {

        int choice = getInteger(message);
        while (choice <= 0) {
            System.out.println("Not a positive integer....");
            choice = getInteger(message);
        }
        return choice;
    }

    private int getInteger(String message) {
        Scanner key = new Scanner(System.in);
        displayMessage(message);
        while (!key.hasNextInt()) {
            System.out.println("Not an integer ! ");
            displayMessage(message);
            key.next();
        }
        return key.nextInt();
    }

    /**
     * Presents the user with the current shapes in the gales and prompts him to
     * chose one by its index. Throws an exception if no shapes are currently in
     * the model.
     *
     * @param paint the model
     * @return the chosen index by the user.
     */
    @Override
    public int getChosenShapeIndex(Model paint) {
        if (paint.getNextAvailableShapeIndex() == 0) {
            throw new IllegalStateException("No shapes yet !");
        }
        System.out.println("Which shape would you like to move/zoom/remove "
                + "or change color ? ");
        System.out.println("The current shapes are : ");
        StringBuilder sb = new StringBuilder();
        List<ColoredShape> currentShapes = paint.getCurrentShapes();
        for (int i = 0; i < currentShapes.size(); i++) {
            sb.append(currentShapes.get(i).getShapeId())
                    .append(", at ")
                    .append(i);
        }
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
        return getPositiveInteger("Your choice for the shape ?");
    }

    /**
     * Prompts the user for a double.
     *
     * @param message Message to display to the user.
     * @return the double entered by the user.
     */
    @Override
    public double getDouble(String message) {
        Scanner key = new Scanner(System.in);
        displayMessage(message);
        while (!key.hasNextDouble()) {
            System.out.println("Not a valid double !");
            displayMessage(message);
            key.next();
        }
        return key.nextDouble();
    }

    /**
     * Prompts the user for the next command, splits the words into an array to
     * be processed by the controller later.
     *
     * @param message Message to display to the user.
     * @return array of the different words composing the commands.
     */
    @Override
    public String[] getCommands(String message) {
        Scanner key = new Scanner(System.in);
        displayMessage(message);
        String command = key.nextLine();
        String[] commands = command.split(" ");
        while (commands.length == 0) {
            displayMessage("Wrong entry ! ");
            displayMessage(message);
            command = key.nextLine();
            commands = command.split(" ");
        }
        return commands;
    }

    /**
     * Displays the message in a formatted fashion.
     *
     * @param msg Message to display.
     */
    @Override
    public void displayMessage(String msg) {
        try {
            Thread.sleep(500);
            System.out.println("<<<<<<<<<<<< " + msg + " >>>>>>>>>>>>\n");
        } catch (InterruptedException ex) {
            displayErrorMessage(ex.getMessage());
        }
    }

    /**
     * Displays an error message on the standard error output in a formatted
     * fashion.
     *
     * @param msg Message to display.
     */
    @Override
    public void displayErrorMessage(String msg) {
        System.err.println("<<<<<<<<<<<< Error ! " + msg + " >>>>>>>>>>>>\n");
    }

    /**
     * Displays the current stats of the paint.
     *
     * @param paint the model.
     */
    @Override
    public void displayCurrentGame(Model paint) {
        System.out.println(paint.asAscii());
    }

    /**
     * Prompts the user for a character, keeps asking until a valid one
     * character only is given.
     *
     * @param message Message to display.
     * @return the chosen character.
     */
    @Override
    public char getChar(String message) {
        Scanner key = new Scanner(System.in);
        displayMessage(message);
        String entry = key.nextLine();
        while (entry.length() != 1) {
            System.out.println("Only one character please ! ");
            displayMessage(message);
            entry = key.nextLine();
        }
        return entry.charAt(0);
    }

}
