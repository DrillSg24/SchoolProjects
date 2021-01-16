package Model;

/**
 * Enumeration of all the different values a card can have in the deck.
 *
 * @author Ihab Tazi 55130.
 */
public enum CardValue {
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;

    /**
     * Method to show the value of the card as it would be on a game card.
     *
     * @return the value of the card as a String.
     */
    @Override
    public String toString() {
        switch (this) {
            case ACE:
                return "A";
            case TWO:
                return "2";
            case THREE:
                return "3";
            case FOUR:
                return "4";
            case FIVE:
                return "5";
            case SIX:
                return "6";
            case SEVEN:
                return "7";
            case EIGHT:
                return "8";
            case NINE:
                return "9";
            case TEN:
                return "10";
            case JACK:
                return "J";
            case QUEEN:
                return "Q";
            case KING:
                return "K";
            default:
                throw new IllegalStateException("Valeur méconnue: " + this);
        }
    }

}
