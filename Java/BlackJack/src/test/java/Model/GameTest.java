/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author tazi7_ukc
 */
public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.initGame(new Player[]{
            new Cpu(500), new HumanPlayer("Test", 50)}, new Deck(), 150);
    }

    @Test
    public void testInitGame_Exceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.initGame(null, new Deck(), 150);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.initGame(new Player[]{new Cpu(50), new Cpu(500)}, null, 150);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            game.initGame(new Player[]{new Cpu(50), new Cpu(500)}, new Deck(), -150);
        });
    }

    @Test
    public void tesstInitGame() {
        assertEquals(500, game.getCpuScore());
        assertEquals(50, game.getHumanScore());
        assertTrue(game.deckStillHasCards());
        assertEquals(150, game.getBet());
    }

    @Test
    public void testGetRoundWinnerIndex() {
        System.out.println("Case bank wins");
        assertEquals(0, game.getRoundWinnerIndex(15, 14));
        System.out.println("Case player wins");
        assertEquals(1, game.getRoundWinnerIndex(14, 19));
        System.out.println("Case player has higher but over 21");
        game.getCpu().addToCurrentTotal(14);
        game.getHumanPlayer().addToCurrentTotal(25);
        assertEquals(0, game.getRoundWinnerIndex(game.getCpuCurrentTotal(),
                game.getHumanCurrentTotal()));
        System.out.println("Case Equal totals");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundWinnerIndex(15, 15);
        });
        System.out.println("Case negativeTotal");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundWinnerIndex(-14, 15);
        });
    }

    @Test
    public void testPlayerIsBlocked() {
        assertFalse(game.playerIsBlocked());
        game.blockHuman();
        assertTrue(game.playerIsBlocked());
    }

    @Test
    public void testGetRoundLoser() {
        System.out.println("Case bank wins");
        assertEquals(game.getHumanPlayer(), game.getRoundLoser(15, 14));
        System.out.println("Case player wins");
        assertEquals(game.getCpu(), game.getRoundLoser(14, 19));
        System.out.println("Case player has higher but over 21");
        game.getCpu().addToCurrentTotal(14);
        game.getHumanPlayer().addToCurrentTotal(25);
        assertEquals(game.getHumanPlayer(), game.getRoundLoser(game.getCpuCurrentTotal(),
                game.getHumanCurrentTotal()));
        System.out.println("Case Equal totals");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundLoser(15, 15);
        });
        System.out.println("Case negativeTotal");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundLoser(-14, 15);
        });
    }

    @Test
    public void testGetRoundWinner() {
        System.out.println("Case bank wins");
        assertEquals(game.getCpu(), game.getRoundWinner(15, 14));
        System.out.println("Case player wins");
        assertEquals(game.getHumanPlayer(), game.getRoundWinner(14, 19));
        System.out.println("Case player has higher but over 21");
        game.getCpu().addToCurrentTotal(14);
        game.getHumanPlayer().addToCurrentTotal(25);
        assertEquals(game.getCpu(), game.getRoundWinner(game.getCpuCurrentTotal(),
                game.getHumanCurrentTotal()));
        System.out.println("Case Equal totals");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundWinner(15, 15);
        });
        System.out.println("Case negativeTotal");
        assertThrows(IllegalArgumentException.class, () -> {
            game.getRoundWinner(-14, 15);
        });
    }

    @Test
    public void testDeckStillHasCards() {
        assertTrue(game.deckStillHasCards());
        int timesToRemove = game.getCurrentDeck().getSize();
        for (int i = 0; i < timesToRemove; i++) {
            game.getCurrentDeck().drawAtTheTop();
        }
        assertFalse(game.deckStillHasCards());
    }

    @Test
    public void testIsAPlayerOver21() {
        game.getCpu().addToCurrentTotal(21);
        game.getHumanPlayer().addToCurrentTotal(1);
        assertFalse(game.isAPlayerOver21());
        game.getCpu().addToCurrentTotal(1);
        assertTrue(game.isAPlayerOver21());
        game.getHumanPlayer().addToCurrentTotal(22);
        assertTrue(game.isAPlayerOver21());
    }

    @Test
    public void testAddMoneyToWinner() {
        int before = game.getCpu().getScore();
        game.getCpu().winsMoney(1500);
        assertEquals(before + 1500, game.getCpuScore());
        assertThrows(IllegalArgumentException.class, () -> {
            game.getCpu().winsMoney(-1500);
        });
    }

    @Test
    public void testRemoveMoneyFromLoser() {
        int before = game.getCpu().getScore();
        game.getCpu().losesMoney(1500);
        assertEquals(before - 1500, game.getCpuScore());
        assertThrows(IllegalArgumentException.class, () -> {
            game.getCpu().losesMoney(-1500);
        });
    }

    @Test
    public void testPlayerIsBroke() {
        game.getCpu().losesMoney(game.getCpuScore());
        assertTrue(game.playerIsBroke(game.getCpu()));
    }

    @Test
    public void testGetCpuScore() {
        assertEquals(500, game.getCpuScore());
    }

    @Test
    public void testGetPlayerScore() {
        assertEquals(50, game.getHumanScore());
    }

    @Test
    public void testGetHumanCurrentTotal() {
        game.getHumanPlayer().addToCurrentTotal(15);
        assertEquals(15, game.getHumanCurrentTotal());
    }

    @Test
    public void testGetCpuCurrentTotal() {
        game.getCpu().addToCurrentTotal(15);
        assertEquals(15, game.getCpuCurrentTotal());
    }

    @Test
    public void testBothHaveSameTotal() {
        assertTrue(game.bothHaveSameTotal());
        game.getCpu().addToCurrentTotal(5);
        assertFalse(game.bothHaveSameTotal());
    }

}
