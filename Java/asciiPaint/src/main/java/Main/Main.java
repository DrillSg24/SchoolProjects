package Main;

import Controller.Application;

/**
 * Point of entry of the AsciiPaint program. Initiates a controller and calls on
 * it to start the game.
 *
 * @author Ihab Tazi 55130.
 */
public class Main {

    public static void main(String[] args) {
        Application controller = new Application();
        controller.start();
    }

}
