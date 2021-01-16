package be.esi.g55130.atl.game2048.util;

import javax.swing.*;

/**
 * Utility class.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Util {
    /**
     * Asks the user for a choice of a version to play, then used in the main
     * method.
     *
     * @return 0 if the user wants the console version ,1 if the user wants
     * the JavaFX version and 2 if the user cancels the game.
     */
    public static int askVersion() {
        String[] options = new String[]{"Console", "JavaFX", "Cancel"};
        return JOptionPane.showOptionDialog(null,
                "Welcome to 2048, please choose your version : ",
                "2048 - 55130 Ihab Tazi",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);
    }
}
