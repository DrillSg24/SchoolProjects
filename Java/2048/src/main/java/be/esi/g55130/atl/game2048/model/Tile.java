package be.esi.g55130.atl.game2048.model;

import java.util.Random;

/**
 * Class representing a tile in the game.
 * A Tile has a integer value, which can only be 0 or a positive power of 2.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Tile {
    private static final Random random = new Random();
    private int value;

    /**
     * Constructor, creates a new Tile of value 0.
     */
    public Tile() {
        this.value = 0;
    }

    /**
     * Simple getter for the value of the tile.
     *
     * @return the value of the tile, a positive integer that is either 0 or
     * a power of 2.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the tile to the given value.
     *
     * @param value the value to set to.
     * @throws IllegalArgumentException if the value is not either 0 or a
     * positive power of 2;
     */
    public void setValue(int value) {
        if (value % 2 != 0 || value < 0) {
            throw new IllegalArgumentException("Value must be positive and a power of 2 !");
        } else if (value == 0) {
            this.nullify();
        } else {
            this.value = value;
        }
    }

    /**
     * Sets the value of the tile to either 2 or 4 (90% chance of 2, 10%
     * chance of 4).
     * Used for a tile that is initially a zero.
     */
    public void enableFirstTime() {
        this.value = random.nextInt(10) < 9 ? 2 : 4;
    }

    /**
     * Sets this tile's value to 0.
     */
    public void nullify() {
        this.value = 0;
    }

    /**
     * Increments this value by a power of 2;
     */
    private void incrementPower() {
        this.value *= 2;
    }

    /**
     * Checks if this tile can be fused with the tile given as parameter.
     *
     * @param otherTile Tile to check against.
     * @return true if otherTile is not null, its value is not 0 and has the
     * same value as this Tile, false otherwise.
     */
    public boolean canFuseWith(Tile otherTile) {
        return otherTile != null
                && otherTile.getValue() != 0
                && this.value == otherTile.getValue();
    }

    /**
     * Fuses this tile with the otherTile by incrementing the power of this
     * tile, and nullifying the otherTile. !Checks before if tile can be fused!
     *
     * @param otherTile tile to fuse with.
     * @throws IllegalArgumentException if this tile and otherTile cannot be
     * fused.
     */
    public void fuseWith(Tile otherTile) {
        if (!this.canFuseWith(otherTile)) {
            throw new IllegalArgumentException("Cannot fuse with this tile !");
        }
        this.incrementPower();
        otherTile.nullify();
    }

}
