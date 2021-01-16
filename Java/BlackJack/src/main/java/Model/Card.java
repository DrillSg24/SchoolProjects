package Model;

import com.vdurmont.emoji.EmojiParser;
import jlibs.core.lang.Ansi;

/**
 * Class representing a playing card. A playing card has a specific value and
 * suit.
 *
 * @author Ihab Tazi 55130.
 */
public class Card {

    private final CardSuit suit;
    private final CardValue value;

    /**
     * Constructor : creates a Card with the given value and suit. Throws an
     * Exception if the value or the suit are invalid.
     *
     * @param suit the suit of the card, a value in the CardSuit enumeration.
     * @param value the value of the Card, a value in the CardValue enumeration.
     */
    public Card(CardSuit suit, CardValue value) {
        if (suit == null || value == null) {
            throw new IllegalArgumentException("Values not permitted");
        }
        this.suit = suit;
        this.value = value;
    }

    public CardSuit getSuit() {
        return suit;
    }

    public CardValue getValue() {
        return value;
    }

    /**
     * Method to display the card, Uses Emojis for the card suits and terminal
     * colours to display the different cards.
     *
     *
     * @return the visual representation of the card.
     */
    @Override
    public String toString() {
        Ansi redCardAnsi = new Ansi(Ansi.Attribute.BRIGHT, Ansi.Color.RED, Ansi.Color.WHITE);
        Ansi blackCardAnsi = new Ansi(Ansi.Attribute.BRIGHT, Ansi.Color.BLACK, Ansi.Color.WHITE);
        Ansi defaultAnsi = new Ansi(Ansi.Attribute.NORMAL, Ansi.Color.WHITE, Ansi.Color.BLACK);
        String cardSuitEmoji = "";
        StringBuilder sb = new StringBuilder();
        String lineBreak = defaultAnsi.colorize(" \n");
        switch (this.suit) {
            case CLUB:
                cardSuitEmoji = EmojiParser.parseToUnicode(":clubs:");
                break;
            case DIAMOND:
                cardSuitEmoji = EmojiParser.parseToUnicode(":diamonds:");
                break;
            case SPADE:
                cardSuitEmoji = EmojiParser.parseToUnicode(":spades:");
                break;
            case HEART:
                cardSuitEmoji = EmojiParser.parseToUnicode(":hearts:");
                break;
        }
        String firstLine = "|----|";
        String secondLine = "|" + value + "  " + cardSuitEmoji + "|";
        if (value == CardValue.TEN) {
            secondLine = "|" + value + " " + cardSuitEmoji + "|";
        }
        String thirdLine = "|    |";
        String fourthLine = "|" + cardSuitEmoji + "  " + value + "|";
        if (value == CardValue.TEN) {
            fourthLine = "|" + cardSuitEmoji + " " + value + "|";
        }
        String fifthLine = "|----|";

        switch (this.suit) {
            case CLUB:
            case SPADE:
                return sb.append(blackCardAnsi.colorize(firstLine))
                        .append(lineBreak)
                        .append(blackCardAnsi.colorize(secondLine))
                        .append(lineBreak)
                        .append(blackCardAnsi.colorize(thirdLine))
                        .append(lineBreak)
                        .append(blackCardAnsi.colorize(fourthLine))
                        .append(lineBreak)
                        .append(blackCardAnsi.colorize(fifthLine))
                        .toString();
            case DIAMOND:
            case HEART:
                return sb.append(redCardAnsi.colorize(firstLine))
                        .append(lineBreak)
                        .append(redCardAnsi.colorize(secondLine))
                        .append(lineBreak)
                        .append(redCardAnsi.colorize(thirdLine))
                        .append(lineBreak)
                        .append(redCardAnsi.colorize(fourthLine))
                        .append(lineBreak)
                        .append(redCardAnsi.colorize(fifthLine))
                        .toString();
            default:
                return "";
        }
    }

    /**
     * Returns the value of the card when counting the tally of the cards in the
     * current round. *Note : the Ace could be either 1 or 11, 1 was chosen
     * because it makes for a longer game.
     *
     * @return the value of the card.
     */
    public int getCardValue() {
        switch (this.value) {
            case ACE:
                return 1;
            case TEN:
            case JACK:
            case QUEEN:
            case KING:
                return 10;
            case TWO:
                return 2;
            case THREE:
                return 3;
            case FOUR:
                return 4;
            case FIVE:
                return 5;
            case SIX:
                return 6;
            case SEVEN:
                return 7;
            case EIGHT:
                return 8;
            case NINE:
                return 9;
            default:
                throw new IllegalArgumentException("Illegal Value " + this.value);
        }
    }

    public boolean equals(Card otherCard) {
        return this.value == otherCard.getValue()
                && this.suit == otherCard.getSuit();
    }

}
