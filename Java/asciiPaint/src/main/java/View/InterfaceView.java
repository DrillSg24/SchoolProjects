package View;

import Model.Model;

/**
 *
 * @author Ihab Tazi 55130.
 */
public interface InterfaceView {

    public int getPositiveInteger(String message);

    public int getChosenShapeIndex(Model paint);

    public double getDouble(String message);

    public String[] getCommands(String message);

    public void displayMessage(String msg);

    public void displayErrorMessage(String msg);

    public void displayCurrentGame(Model paint);

    public char getChar(String message);
}
