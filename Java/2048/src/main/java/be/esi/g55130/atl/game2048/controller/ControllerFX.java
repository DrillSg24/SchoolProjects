package be.esi.g55130.atl.game2048.controller;

import be.esi.g55130.atl.game2048.model.Direction;
import be.esi.g55130.atl.game2048.model.GameStatus;
import be.esi.g55130.atl.game2048.model.Model;
import be.esi.g55130.atl.game2048.view.InterfaceView;
import be.esi.g55130.atl.game2048.view.ViewFX;
import javafx.event.ActionEvent;
import javafx.scene.input.KeyEvent;

/**
 * Controller of the game.
 * A controller has a view facade and a model facade to interact with.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class ControllerFX {
    private final InterfaceView view;
    private final Model game;

    /**
     * Constructor for the controller.
     *
     * @param view View facade for the controller to interact with.
     * @param game Model facade for the controller to interact with.
     */
    public ControllerFX(InterfaceView view, Model game) {
        this.view = view;
        this.game = game;
    }

    /**
     * Registers the view as observer to the game and starts
     * the game.
     */
    public void startGame() {
        this.game.registerObserver(this.view);
        try {
            game.start();
        } catch (Exception e) {
            view.displayError(e.getMessage());
        }
    }

    public void undoButtonHandler(ActionEvent event){
        if(game.rollbackMove()){
          view.displayMessage("Undid the last move !");
        }
        ((ViewFX) this.view).requestGridFocus();
    }
    /**
     * Handler of the action of the restart button in the view.
     *
     * @param event event to act on, intended to be the event of a click on
     *              the restart button.
     */
    public void restartButtonHandler(ActionEvent event) {
        ((ViewFX) this.view).clearLog();
        this.view.displayMessage("Game restarted, last game score was : "
                + this.game.getPlayerScore());
        game.resetGame();
        ((ViewFX) this.view).updateScoreLabel(game.getPlayerScore());
        ((ViewFX) this.view).requestGridFocus();
    }

    /**
     * Handles the event of a keypress and binds buttons to the corresponding
     * movements.
     *
     * @param keyEvent Keyboard Event.
     */
    public void keyPressed(KeyEvent keyEvent) {
        if (game.getGameStatus().equals(GameStatus.RUNNING)) {
            switch (keyEvent.getCode()) {
                case LEFT:
                case Q:
                case NUMPAD4:
                    if (!game.makeMove(Direction.LEFT)) {
                        noMovementHandler(Direction.LEFT);
                    } else {
                        endingHandler();
                    }
                    break;
                case RIGHT:
                case D:
                case NUMPAD6:
                    if (!game.makeMove(Direction.RIGHT)) {
                        noMovementHandler(Direction.RIGHT);
                    } else {
                        endingHandler();
                    }
                    break;
                case UP:
                case Z:
                case NUMPAD8:
                    if (!game.makeMove(Direction.UP))
                        noMovementHandler(Direction.UP);
                    else {
                        endingHandler();
                    }
                    break;
                case DOWN:
                case S:
                case NUMPAD2:
                    if (!game.makeMove(Direction.DOWN))
                        noMovementHandler(Direction.DOWN);
                    else {
                        endingHandler();
                    }
                    break;
            }
        }
    }


    /**
     * Handles the ending of the game, shows the final state of the game,
     * and a message with the final score.
     */
    private void endingHandler() {
        if (game.getGameStatus().equals(GameStatus.WON)
                || game.getGameStatus().equals(GameStatus.LOST)) {
            view.displayMessage("You "
                    + game.getGameStatus().toString().toLowerCase()
                    + " ! Your final score was " + game.getPlayerScore());
        }
    }

    /**
     * Handles the case where a movement won't change anything in the board,
     * displays a message on the log.
     */
    private void noMovementHandler(Direction direction) {
        if (game.getGameStatus().equals(GameStatus.RUNNING)) {
            view.displayMessage("Cannot move "
                    + direction.toString().toLowerCase() + " !");
        }
    }

}
