package Blackjack;

import Controller.Controller;
import Model.Deck;
import Model.Game;
import View.View;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

/**
 * Main method for the Blackjack game.
 *
 * @author Ihab Tazi 55130.
 */
public class Blackjack {

    public static void main(String[] args) throws InterruptedException, UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out, true, "UTF8"));
        Controller controller = new Controller(new View(), new Game());
        controller.startGame(5000, 5000, new Deck());
    }
}
