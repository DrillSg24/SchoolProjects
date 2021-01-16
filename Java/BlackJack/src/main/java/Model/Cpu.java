package Model;

/**
 * Class representing the bank in the game, extends the Player class by having a
 * check on whether the tally is still under 17.
 *
 * @author Ihab Tazi 55130.
 */
public class Cpu extends Player {

    public Cpu(int score) {
        super(score);
    }

    /**
     * Checks if the bank tally is under 17.
     *
     * @return true if the tally is smaller than or equal to 17, false
     * otherwise.
     */
    public boolean isUnderLimit() {
        return this.getCurrentTotal() <= 17;
    }

    @Override
    public String toString() {
        return "Bank";
    }

}
