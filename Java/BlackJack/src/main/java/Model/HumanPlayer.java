package Model;

/**
 * Class representing the human player, extends Player by having a name as a
 * String, that is chosen at the beginning of the game in the controller.
 *
 * @author Ihab Tazi 55130.
 */
public class HumanPlayer extends Player {

    private final String playerName;

    public HumanPlayer(String playerName, int score) {
        super(score);
        if(playerName == null){
            throw new IllegalArgumentException("Name cannot be null !");
        }
        this.playerName = playerName;
    }

    @Override
    public String toString() {
        return this.playerName;
    }

}
