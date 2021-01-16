package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Parent class to the HumanPlayer and Cpu. Contains the main characteristics of
 * a Player in the game, such as a score (money), a tally for each turn, a list
 * of cards which starts empty and then gets replenished when the player draws
 * the cards and a check to block the players from playing beyond their turn.
 *
 * @author Ihab Tazi 55130.
 */
public abstract class Player {

    private int score;
    private int currentTotal;
    private List<Card> cards;
    private boolean blocked;

    /**
     * Constructor for the Player. A player starts free(not blocked), with a
     * money specified by the parameter score, with a tally of 0 and no cards
     * yet.
     *
     * @param score the amount of money the Player has.
     */
    public Player(int score) {
        if (score <= 0) {
            throw new IllegalArgumentException("The player's money cannot be negative !");
        }
        this.blocked = false;
        this.currentTotal = 0;
        this.score = score;
        this.cards = new ArrayList<>();
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public int getScore() {
        return score;
    }

    public List<Card> getCards() {
        return cards;
    }

    /**
     * Adds the given value to the current card tally.
     *
     * @param value amount to add to the card tally.
     */
    public void addToCurrentTotal(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("value must be strictly positive !");
        }
        this.currentTotal += value;
    }

    /**
     * Adds the given amount of money to the Player.
     *
     * @param money amount of money to add to the Player.
     */
    public void winsMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("money must be strictly positive !");
        }
        this.score += money;
    }

    /**
     * Removes the given amount of money from the Player.
     *
     * @param money amount of money to remove from the Player.
     */
    public void losesMoney(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("money must be strictly positive !");
        }
        this.score -= money;
    }

    /**
     * Check if the player has no more money.
     *
     * @return true if the score is 0 or negative, false otherwise.
     */
    public boolean isBroke() {
        return score <= 0;
    }

    /**
     * Checks if the player has exceeded 21.
     *
     * @return true if the tally is over 21, false otherwise.
     */
    public boolean isOver21() {
        return this.currentTotal > 21;
    }

    /**
     * Adds the card given to the list of cards that the player has.
     *
     * @param card Card to add.
     */
    public void addCardToPlayer(Card card) {
        this.cards.add(card);
    }

    /**
     * Checks if the player is blocked for this turn.
     *
     * @return true if blocked, false otherwise.
     */
    public boolean isBlocked() {
        return blocked;
    }

    /**
     * Draws a card, adds it to the player and removes it from the deck.
     *
     * @param deck the deck to draw from.
     * @return the drawn card.
     */
    public Card drawCard(Deck deck) {
        Card chosenCard = deck.drawAtTheTop();
        cards.add(chosenCard);
        this.addToCurrentTotal(chosenCard.getCardValue());
        return chosenCard;
    }

    /**
     * Blocks the player from playing for this round.
     */
    public void block() {
        this.blocked = true;
    }
}
