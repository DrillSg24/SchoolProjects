package be.esi.g55130.atl.game2048.model;

import be.esi.g55130.atl.game2048.view.Observer;

import java.util.ArrayList;

/**
 * Class representing the game, only the start method is different between
 * the game in its console version and its JavaFX version.
 * <p>
 * A game has a board, a player and can be observed for changes.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Game implements Model {

    protected final Player player;
    protected Board board;
    protected ArrayList<Observer> observers;

    public Game(String playerName) {
        this.board = new Board();
        this.player = new Player(playerName);
        this.observers = new ArrayList<>();
    }

    /**
     * Constructor for the game, initialises a new empty board, a new player,
     * and then adds a random tile to the board.
     */
    public Game() {
        this("Nemo");
    }


    /**
     * Launches the game by adding a random tile to the board and notifying
     * the view.
     */
    @Override
    public void start() {
        this.board.addRandomTile();
        this.board.copyFirstBoard();
        notifyObservers();
    }

    /**
     * Return the tile at the given coords.
     *
     * @param i the x coordinate to get tile at.
     * @param j the y coordinate to get tile at.
     * @return Tile at the given coords.
     * @throws IllegalArgumentException if coords are outside the board.
     *                                  (from board.getTileAt(i,j)).
     */
    @Override
    public Tile getTileAt(int i, int j) {
        return this.board.getTileAt(i, j);
    }

    /**
     * Resets the game by resetting the board and the score.
     */
    @Override
    public void resetGame() {
        this.player.resetScore();
        this.board = new Board();
        this.start();
    }

    /**
     * Gets the score of the player.
     *
     * @return the current score of the player, a positive integer.
     */
    @Override
    public int getPlayerScore() {
        return this.player.getScore();
    }

    /**
     * Calls the board to make a move in the specified direction.
     * If the board has changed after the movement, add a random tile to the
     * board.
     *
     * @param direction the Direction to which to move.
     * @return true if the board changed because of the move, false otherwise.
     */
    @Override
    public boolean makeMove(Direction direction) {
        if (this.board.moveBoard(direction, this.player)) {
            this.board.addRandomTile();
            notifyObservers();
            return true;
        }
        notifyObservers();
        return false;
    }

    /**
     * Gets the current status of the game.
     * A game is at the starting point if all tiles are empty.
     * A game is running if the play is still possible, meaning there are
     * still empty tiles, or if full, there are still neighbours of equal
     * values.
     * A game is lost if further play is not possible meaning the board is
     * full and no neighbours are of equal value.
     * A game is won if the target 2048 is reached by a tile in the board.
     *
     * @return the current status of the game.
     */
    @Override
    public GameStatus getGameStatus() {
        if (board.allTilesAreEmpty()) {
            return GameStatus.START;
        } else if (board.getHighestValue() == 2048) {
            return GameStatus.WON;
        } else if (!board.playStillPossible()) {
            return GameStatus.LOST;
        } else {
            return GameStatus.RUNNING;
        }
    }

    /**
     * Register an observer to this observable.
     *
     * @param observer Observer to register to this observable.
     */
    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) observers.add(observer);
    }

    /**
     * Unregisters the observer from this observable.
     *
     * @param observer Observer to unregister.
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifies the observers that they need to adapt to a change in this
     * observable.
     */
    @Override
    public void notifyObservers() {
        for (Observer observer :
                this.observers) {
            observer.update(this);
        }
    }

    /**
     * Gets the name of the player in the game.
     *
     * @return The player's name, a String.
     */
    @Override
    public String getPlayerName() {
        return this.player.toString();
    }

    /**
     * Rollbacks the game by rolling back the Board and the score to their
     * previous values.
     *
     * @return True if the board has changed with the undo, false otherwise.
     */
    @Override
    public boolean rollbackMove() {
        if (this.board.rollBackTiles()) {
            this.player.rollBackScore();
            notifyObservers();
            return true;
        }
        return false;
    }
}
