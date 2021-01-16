package be.esi.g55130.atl.game2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    Game game1, game2, game3, game4;

    @BeforeEach
    public void setUp() {
        game1 = new Game();
        addTileIn(game1, 0, 0, 16);
        game2 = new Game();
        game3 = new Game();
        addTileIn(game3, 0, 0, 1024);
        addTileIn(game3, 0, 1, 1024);
        game4 = new Game();
        int count = 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                game4.getTileAt(i, j).setValue(count);
                count *= 2;
            }
            count /= 4;
        }
    }

    @Test
    public void getTileAt() {
        assertThrows(IllegalArgumentException.class,
                () -> game1.getTileAt(-1, 2));
        assertEquals(16, game1.getTileAt(0, 0).getValue());
    }

    @Test
    public void getGameStatus_START() {
        assertEquals(GameStatus.START, game2.getGameStatus());
    }

    @Test
    public void getGameStatus_RUNNING() {
        assertEquals(GameStatus.RUNNING, game1.getGameStatus());
    }

    @Test
    public void getGameStatus_WON() {
        assertEquals(GameStatus.RUNNING, game3.getGameStatus());
        game3.makeMove(Direction.RIGHT);
        assertEquals(GameStatus.WON, game3.getGameStatus());
    }

    @Test
    public void getGameStatus_LOST() {
        assertEquals(GameStatus.LOST, game4.getGameStatus());
    }

    private void addTileIn(Game game, int i, int j, int value) {
        game.getTileAt(i, j).setValue(value);
    }


}