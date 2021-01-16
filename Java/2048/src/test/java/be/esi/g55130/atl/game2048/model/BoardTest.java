package be.esi.g55130.atl.game2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board emptyBoard;
    private Board board1;
    private Board board2;
    private Board board3;
    private Player player;

    @BeforeEach
    public void setUp() {
        player = new Player("Test");
        emptyBoard = new Board();
        board1 = new Board();
        for (int i = 0; i < 16; i++) {
            board1.addRandomTile();
        }
        board2 = new Board();
        board2.getTileAt(0, 0).setValue(16);
        board3 = new Board();
        int count = 2;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board3.getTileAt(i, j).setValue(count);
                count *= 2;
            }
        }
    }

    @Test
    public void getTileAt() {
        System.out.println("Test getTileAt()");
        Tile expected = new Tile();
        expected.setValue(16);
        assertEquals(expected.getValue(),
                board2.getTileAt(0, 0).getValue());
        assertThrows(Exception.class, () -> {
            board2.getTileAt(-1, 2);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board2.getTileAt(1, 4);
        });
    }

    @Test
    public void addRandomTile() {
        System.out.println("Test addRandomTile");
        Board expected = board1;
        board1.addRandomTile();
        assertEquals(expected, board1);
        emptyBoard.addRandomTile();
        assertFalse(emptyBoard.allTilesAreEmpty());
        emptyBoard = new Board();
    }

    @Test
    public void moveBoard_SUCCESSFUL() {
        System.out.println("Test moveBoard successful");
        Player player = new Player("Test");
        Board beforeMove = new Board();
        beforeMove.getTileAt(0, 0).setValue(2);
        beforeMove.getTileAt(0, 1).setValue(2);
        beforeMove.getTileAt(1, 1).setValue(2);
        beforeMove.getTileAt(1, 2).setValue(4);
        beforeMove.getTileAt(1, 3).setValue(2);
        beforeMove.getTileAt(2, 1).setValue(4);
        beforeMove.getTileAt(2, 3).setValue(4);
        beforeMove.getTileAt(3, 0).setValue(2);
        beforeMove.getTileAt(3, 1).setValue(2);
        beforeMove.getTileAt(3, 2).setValue(2);
        beforeMove.getTileAt(3, 3).setValue(2);
        Board afterMove = new Board();
        afterMove.getTileAt(0, 3).setValue(4);
        afterMove.getTileAt(1, 1).setValue(2);
        afterMove.getTileAt(1, 2).setValue(4);
        afterMove.getTileAt(1, 3).setValue(2);
        afterMove.getTileAt(2, 3).setValue(8);
        afterMove.getTileAt(3, 2).setValue(4);
        afterMove.getTileAt(3, 3).setValue(4);
        beforeMove.moveBoard(Direction.RIGHT, player);
        assertTrue(this.areBoardsEqual(beforeMove, afterMove));
        assertEquals(10, player.getScore());
    }

    @Test
    public void moveBoard_STATICTHENMOVE() {
        System.out.println("Test move first move static, and then move right");
        Board beforeMove = copyOfBoard(board2);
        board2.moveBoard(Direction.UP, player);
        assertTrue(areBoardsEqual(beforeMove, board2));
        board2.moveBoard(Direction.DOWN, player);
        assertFalse(areBoardsEqual(beforeMove, board2));
        board2 = copyOfBoard(beforeMove);
    }

    @Test
    public void allTilesAreEmpty() {
        System.out.println("Test tiles are empty");
        assertTrue(emptyBoard.allTilesAreEmpty());
        assertFalse(board1.allTilesAreEmpty());
    }

    @Test
    public void playStillPossible() {
        System.out.println("Test playStillPossible");
        assertTrue(board2.playStillPossible());
        assertFalse(board3.playStillPossible());
    }

    @Test
    public void getHighestValue() {
        assertEquals(16, board2.getHighestValue());
        assertEquals(0, emptyBoard.getHighestValue());
    }

    private boolean areBoardsEqual(Board b1, Board b2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (b1.getTileAt(i, j).getValue()
                        != b2.getTileAt(i, j).getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    private Board copyOfBoard(Board b) {
        Board copy = new Board();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                copy.getTileAt(i, j).setValue(b.getTileAt(i, j).getValue());
            }
        }
        return copy;
    }
}