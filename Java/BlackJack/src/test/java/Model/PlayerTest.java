/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tazi7_ukc
 */
public class PlayerTest {

    private Player human, cpu;

    public PlayerTest() {
    }

    @BeforeEach
    public void setUp() {
        human = new HumanPlayer("Test", 5000);
        cpu = new Cpu(5000);
    }

    @Test
    public void testGetCurrentTotal() {
        assertEquals(0, cpu.getCurrentTotal());
    }

    @Test
    public void testGetScore() {
        assertEquals(5000, human.getScore());
    }

    @Test
    public void testAddToCurrentTotal() {
        cpu.addToCurrentTotal(25);
        assertEquals(25, cpu.getCurrentTotal());
    }

    @Test
    public void testWinsMoney() {
        cpu.winsMoney(500);
        assertEquals(5000 + 500, cpu.getScore());
    }

    @Test
    public void testLosesMoney() {
        cpu.losesMoney(500);
        assertEquals(5000 - 500, cpu.getScore());
    }

    @Test
    public void testExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cpu.addToCurrentTotal(-2);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cpu.winsMoney(-200);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            cpu.losesMoney(0);
        });
    }

    @Test
    public void testIsBroke() {
        cpu.losesMoney(cpu.getScore());
        assertTrue(cpu.isBroke());
    }

    @Test
    public void testIsOver21() {
        cpu.addToCurrentTotal(new Card(CardSuit.DIAMOND, CardValue.JACK).getCardValue());
        assertFalse(cpu.isOver21());
        cpu.addToCurrentTotal(new Card(CardSuit.DIAMOND, CardValue.QUEEN).getCardValue());
        assertFalse(cpu.isOver21());
        cpu.addToCurrentTotal(new Card(CardSuit.DIAMOND, CardValue.JACK).getCardValue());
        assertTrue(cpu.isOver21());
    }

    @Test
    public void testAddCardToPlayer() {
        Card expected = new Card(CardSuit.SPADE, CardValue.NINE);
        human.addCardToPlayer(new Card(CardSuit.SPADE, CardValue.NINE));
        assertTrue(human.getCards().get(0).equals(expected));
    }

    @Test
    public void testDrawCard() {
        Deck deck = new Deck();
        Card expected = new Card(CardSuit.CLUB, CardValue.ACE);
        assertTrue(expected.equals(cpu.drawCard(deck)));
    }

}
