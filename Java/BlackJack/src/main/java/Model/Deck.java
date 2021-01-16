package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class representing a deck. A deck always has 52 different cards, represented
 * here by a list of Card objects.
 *
 * @author Ihab Tazi 55130.
 */
public class Deck {

    private final List<Card> cards;

    /**
     * Constructor. Creates all the different cards and adds them to the list.
     */
    public Deck() {
        cards = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for (CardSuit suit : CardSuit.values()) {
                cards.add(new Card(suit, value));
            }
        }
    }

    public int getSize(){
        return cards.size();
    }
    /**
     * Method to shuffle the cards in the deck. Uses the Collections utility
     * class.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Checks if the deck is empty or not.
     *
     * @return true id deck is empty (i.e the list is empty), false otherwise.
     */
    public boolean isEmpty() {
        return cards.isEmpty();
    }

    /**
     * Represents the cards present in the deck at the current state.
     *
     * @return String outlining the current cards in the game.
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        cards.forEach(card -> {
            stringBuilder.append(card).append(" ; ");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1).deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append(".");
        return stringBuilder.toString();
    }

    /**
     * Picks a the top card in the deck, and then removes it from the deck.
     *
     * @return the Card that was picked and removed.
     */
    public Card drawAtTheTop() {
        return cards.remove(0);
    }
}
