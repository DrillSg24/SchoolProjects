package be.esi.g55130.atl.game2048.model;

/**
 * Class representing a player.
 * A player has a name and a positive score.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Player {
    private final String playerName;
    private int score;
    private int previousScore;

    /**
     * Constructor for the player.
     * Instantiates a player with the name given as parameter and an initial
     * score of 0.
     *
     * @param playerName Name of the player, a String;
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.score = this.previousScore = 0;
    }

    /**
     * Simple getter for the current score.
     *
     * @return the current score, a positive integer.
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds the value given to the score of the player.
     *
     * @param value amount to add to the score.
     * @throws IllegalArgumentException if value in parameter is negative or 0.
     */
    public void addToScore(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Cannot add a negative or null" +
                    " value ! ");
        }
        this.previousScore = this.score;
        this.score += value;
    }

    /**
     * Only the name of the player will be displayed by the view.
     *
     * @return the player's name as String ready to be displayed by the view.
     */
    @Override
    public String toString() {
        return this.playerName;
    }

    public void resetScore() {
        this.score = this.previousScore = 0;
    }

    public void rollBackScore() {
        this.score = this.previousScore;
    }
}
