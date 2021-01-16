package be.esi.g55130.atl.game2048.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void addToScore() {
        Player player = new Player("Test");
        player.addToScore(25);
        assertEquals(25, player.getScore());
        assertThrows(IllegalArgumentException.class,
                () -> player.addToScore(-10));
        assertThrows(IllegalArgumentException.class,
                () -> player.addToScore(0));
    }
}