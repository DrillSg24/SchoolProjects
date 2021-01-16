/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tazi7_ukc
 */
public class CardTest {

    @BeforeEach
    public void setUp() {
    }

    @Test
    public void testConstructor_nullValue() {
        System.out.println("Test Card constructor with null value");
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(CardSuit.CLUB, null);
        });
    }

    @Test
    public void testConstructor_nullSuit() {
        System.out.println("Test Card constructor with null suit");
        assertThrows(IllegalArgumentException.class, () -> {
            Card card = new Card(null, CardValue.ACE);
        });
    }

    /**
     * Test of getValue method, of class Card.
     */
    @Test
    public void testGetValue_ACE() {
        System.out.println("Test getValue for Ace");
        Card card = new Card(CardSuit.CLUB, CardValue.ACE);
        int expected = 1;
        assertEquals(expected, card.getCardValue());
    }

    @Test
    public void testEquals_True() {
        Card card = new Card(CardSuit.CLUB, CardValue.ACE);
        Card sameCard = new Card(CardSuit.CLUB, CardValue.ACE);
        assertTrue(card.equals(sameCard));
    }

    @Test
    public void testEquals_OnlyOneOfTwoParameters() {
        Card card = new Card(CardSuit.CLUB, CardValue.ACE);
        Card sameSuitCard = new Card(CardSuit.CLUB, CardValue.FIVE);
        Card sameValueCard = new Card(CardSuit.SPADE, CardValue.ACE);
        assertFalse(card.equals(sameSuitCard));
        assertFalse(card.equals(sameValueCard));
    }

}
