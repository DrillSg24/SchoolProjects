package be.esi.g55130.atl.game2048.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.effect.Glow;

/**
 * Class representing the view of the score in the JavaFX version.
 * Has an attribute score.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class ScoreLabel extends Label {
    private final String playerName;
    private int score;

    /**
     * Creates and formats a label with the current score.
     *
     * @param score Score to display, an integer.
     */
    public ScoreLabel(String playerName, int score) {
        super();
        this.playerName = playerName;
        this.setMinSize(600, 150);
        this.setMaxSize(600, 150);
        this.setAlignment(Pos.CENTER);
        this.setId("scoreLabel");
        this.setEffect(new Glow(0.7));
        this.updateScore(score);
    }

    /**
     * Updates the score in the label.
     *
     * @param score new score, an integer.
     */
    public void updateScore(int score) {
        this.score = score;
        this.setText(this.playerName + "'s current score : " + this.score);
    }
}
