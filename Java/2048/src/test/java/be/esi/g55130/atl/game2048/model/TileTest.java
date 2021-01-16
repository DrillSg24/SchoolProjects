package be.esi.g55130.atl.game2048.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    private Tile tile1, tile2, tile3, tile4;

    @BeforeEach
    public void setUp() {
        tile1 = new Tile();
        tile2 = new Tile();
        tile3 = new Tile();
        tile3.setValue(32);
        tile4 = new Tile();
        tile4.setValue(32);
    }

    @Test
    public void setValue() {
        assertThrows(IllegalArgumentException.class, () -> tile1.setValue(-2));
        assertThrows(IllegalArgumentException.class, () -> tile1.setValue(5));
        tile2.setValue(4);
        assertEquals(4, tile2.getValue());
        tile2.setValue(0);
        assertEquals(0, tile2.getValue());
    }

    @Test
    public void enableFirstTime() {
        tile1.enableFirstTime();
        assertTrue(tile1.getValue() == 2 || tile1.getValue() == 4);
    }

    @Test
    public void nullify() {
        tile3.nullify();
        assertEquals(0, tile3.getValue());
    }

    @Test
    public void canFuseWith() {
        assertFalse(tile3.canFuseWith(tile1));
        assertFalse(tile3.canFuseWith(null));
        assertTrue(tile3.canFuseWith(tile4));
    }

    @Test
    public void fuseWith() {
        int expected3 = tile3.getValue() * 2;
        int expected4 = 0;
        tile3.fuseWith(tile4);
        assertEquals(expected3, tile3.getValue());
        assertEquals(expected4, tile4.getValue());
        assertThrows(IllegalArgumentException.class,
                () -> tile1.fuseWith(tile3));
    }
}