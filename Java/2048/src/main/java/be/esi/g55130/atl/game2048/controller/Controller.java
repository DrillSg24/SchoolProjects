package be.esi.g55130.atl.game2048.controller;

import be.esi.g55130.atl.game2048.model.Direction;
import be.esi.g55130.atl.game2048.model.GameStatus;
import be.esi.g55130.atl.game2048.model.Model;
import be.esi.g55130.atl.game2048.view.InterfaceView;

/**
 * Controller of the game.
 * A controller has a view facade and a model facade to interact with.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Controller {
    private final InterfaceView view;
    private final Model game;

    /**
     * Constructor for the controller.
     *
     * @param view View facade for the controller to interact with.
     * @param game Model facade for the controller to interact with.
     */
    public Controller(InterfaceView view, Model game) {
        this.view = view;
        this.game = game;
    }

    /**
     * Main loop of the game.<br>
     * 1) Displays a welcome message.<br>
     * 2) Starts the game
     * 3)Registers the view as observer to the game
     * 4) While the game's still running :<br>
     * - asks for the direction and moves accordingly. If the board didn't
     * change, informs that the board hasn't changed<br>
     * 5) Displays the message of winning/losing as well as the final score.
     */
    public void startGame() {
        view.displayMessage("Welcome to 2048 "
                + game.getPlayerName() + "!");
        view.displayHint();
        try {
            this.game.registerObserver(this.view);
            game.start();
            while (game.getGameStatus() == GameStatus.RUNNING) {
                Direction chosenDirection = view.askDirection();
                if (!game.makeMove(chosenDirection)) {
                    view.displayMessage("You cannot move "
                            + chosenDirection.toString().toLowerCase() + " !");
                }
            }
            handleEnding();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
        view.displayMessage("Thank you for playing 2048 !");
    }

    /**
     * Handles the ending of the game, shows the final state of the game, and
     * a message with the final score.
     */
    public void handleEnding() {
        view.displayGame(game);
        view.displayMessage("You " +
                game.getGameStatus().toString().toLowerCase()
                + " ! Your final score was " + game.getPlayerScore());
    }

}
