package be.esi.g55130.atl.game2048.model;

/**
 * Interface representing what methods the game model should at least have.
 * Used by the controller using the implementation in Game class.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public interface Model extends Observable {

    void start();

    int getPlayerScore();

    boolean makeMove(Direction d);

    GameStatus getGameStatus();

    Tile getTileAt(int i, int j);

    void resetGame();

    String getPlayerName();

    boolean rollbackMove();
}
