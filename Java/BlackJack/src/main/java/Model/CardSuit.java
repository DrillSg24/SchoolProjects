package Model;

/**
 * Enumeration of all the different suits that a card can have.
 * @author Ihab Tazi 55130.
 */
public enum CardSuit {
    CLUB, DIAMOND, HEART, SPADE;

    /**
     * Method to translate the different suits to French.
     *
     * @return the corresponding suit in French.
     */
    @Override
    public String toString() {
        switch (this) {
            case CLUB : return "trefle";
            case HEART : return "coeur";
            case SPADE : return "pique";
            case DIAMOND : return "carreau";
            default : throw new IllegalStateException("Couleur méconnue " + this);
        }
        
    }
}
