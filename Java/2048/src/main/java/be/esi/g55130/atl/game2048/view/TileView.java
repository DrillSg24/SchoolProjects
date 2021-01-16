package be.esi.g55130.atl.game2048.view;

import be.esi.g55130.atl.game2048.model.GameStatus;
import be.esi.g55130.atl.game2048.model.Tile;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

/**
 * Class representing the view of a tile in the JavaFX version.
 * <p>
 * Has a Tile, a height and a weight.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class TileView extends Label {
    Tile tile;
    double height = 150., width = 150.;

    /**
     * Creates and formats a label to correspond with the tile's value.
     *
     * @param tile Tile to format.
     */
    public TileView(Tile tile, GameStatus gameStatus) {
        super();
        this.tile = tile;
        this.setMinWidth(width);
        this.setMinHeight(height);
        this.setText(this.tile.getValue() == 0 ? "" :
                "" + this.tile.getValue());
        this.setAlignment(Pos.CENTER);
        this.formatColor(gameStatus);
        this.setTextAlignment(TextAlignment.CENTER);
        this.setId("tileView");
    }

    /**
     * Sets a style class on the current label, according to the value of its
     * tile. If the game is won/lost, overrides the whole board in green/red
     * to serve as a visual cue.
     * The colors are defined in the <b>style.css</b> file.
     */
    private void formatColor(GameStatus gameStatus) {
        if (gameStatus == GameStatus.WON) {
            this.getStyleClass().add("tiles_GameWon");
        } else if (gameStatus == GameStatus.LOST) {
            this.getStyleClass().add("tiles_GameLost");
        } else {
            if (this.tile.getValue() > 2048) {
                this.getStyleClass().add("value2048");
            }
            this.getStyleClass().add("value" + tile.getValue());
        }
    }
}
