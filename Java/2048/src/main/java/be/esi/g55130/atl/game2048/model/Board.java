package be.esi.g55130.atl.game2048.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class representing the board object.
 * A board has an array of tiles.
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class Board {

    private Tile[][] tiles;
    private Tile[][] previousTiles;

    /**
     * Constructor of the Board, initialises a 4*4 tile array, and fills it
     * with tiles of value 0;
     */
    public Board() {
        this.tiles = new Tile[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tiles[i][j] = new Tile();
            }
        }
        this.previousTiles = copy(tiles);
    }

    public void copyFirstBoard(){
        this.previousTiles = copy(tiles);
    }

    /**
     * Gets the tile at the given coordinates.
     *
     * @param i the x coordinate of the tile to get.
     * @param j the y coordinate of the tile to get.
     * @return the tile at (i,j)
     * @throws IllegalArgumentException if coordinates are outside the board.
     */
    public Tile getTileAt(int i, int j) {
        if (!areCoordsInside(i, j)) {
            throw new IllegalArgumentException("The given coordinates are " +
                    "outside the board !");
        }
        return this.tiles[i][j];
    }

    /**
     * Checks if the given coordinates are inside the board.
     *
     * @param i the x coordinate to check.
     * @param j the y coordinate to check.
     * @return true if the coords are inside, false otherwise.
     */
    private boolean areCoordsInside(int i, int j) {
        return i >= 0 && i < 4 && j >= 0 && j < 4;
    }

    /**
     * Adds a random tile to the board.
     * Generates a random couple of coordinates, and keeps doing while the
     * tile at the coordinates is not null and while the total count is less
     * than 16 (to avoid staying in the loop when the board is full).
     */
    public void addRandomTile() {
        Random r = new Random();
        int count = 0;
        int seed = r.nextInt(16);
        int i = seed / 4;
        int j = seed % 4;
        while (this.getTileAt(i, j).getValue() != 0 && count++ < 16) {
            seed = (seed + 1) % 16;
            i = seed / 4;
            j = seed % 4;
        }
        if (count < 16) {
            this.getTileAt(i, j).enableFirstTime();
        }
    }

    /**
     * Moves the board in the direction given.
     * Begins by getting the list of tiles to move in each row, then if the
     * list is not all zeros, stacks it to the wall in the given direction,
     * and merges values when possible; It then adds a random tile to the
     * board if the movement changed any values in the board.
     *
     * @param direction Direction to go to.
     * @param player    Player to add points to if tiles are merged.
     * @return true if the board has moved, false otherwise.
     */
    public boolean moveBoard(Direction direction, Player player) {
        Tile[][] copyBeforeMove = this.copy(this.tiles);
        for (int i = 0; i < 4; i++) {
            List<Tile> tilesToMove = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                switch (direction) {
                    case LEFT:
                        tilesToMove.add(this.tiles[i][j]);
                        break;
                    case RIGHT:
                        tilesToMove.add(this.tiles[i][4 - j - 1]);
                        break;
                    case UP:
                        tilesToMove.add(this.tiles[j][i]);
                        break;
                    case DOWN:
                        tilesToMove.add(this.tiles[4 - j - 1][i]);
                        break;
                    default:
                        break;
                }
            }
            if (!allAreZeros(tilesToMove)) {
                stackToWall(tilesToMove);
                fuseTilesInList(tilesToMove, player);
            }
        }
        if (!this.areEqualValues(this.tiles, copyBeforeMove)) {
            this.previousTiles = copyBeforeMove;
            return true;
        }
        return false;
    }


    /**
     * Checks if all the tiles in the two 2d arrays are equal value to value
     *
     * @param tiles  first 2D array of tiles
     * @param tiles1 second 2D array of tiles
     * @return true if all the tiles are equal value to value, false otherwise.
     */
    private boolean areEqualValues(Tile[][] tiles, Tile[][] tiles1) {
        if (tiles.length != tiles1.length
                || tiles[0].length != tiles1[0].length) {
            throw new IllegalArgumentException("Cannot compare 2d arrays of " +
                    "different sizes");
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() != tiles1[i][j].getValue()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns a basic value by value copy of the array of tiles.
     *
     * @param tiles 2d array of tiles to copy.
     * @return value to value copy of tiles.
     * @throws IllegalArgumentException if an element in tiles is null.
     */
    private Tile[][] copy(Tile[][] tiles) {
        Tile[][] newTiles = new Tile[tiles.length][tiles[0].length];
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles[0].length; j++) {
                newTiles[i][j] = new Tile();
                if (tiles[i][j] == null) {
                    throw new IllegalArgumentException("Null value cannot be " +
                            "copied !");
                }
                newTiles[i][j].setValue(tiles[i][j].getValue());
            }
        }
        return newTiles;
    }

    /**
     * Slides each element to the end of the list (by calling the slide method),
     * while the next elements are zeros. If next element is not zero, stops
     * and passes to that next element.
     *
     * @param tileList list of elements to stack.
     */
    private void stackToWall(List<Tile> tileList) {
        for (int i = 0; i < tileList.size()
                && !sublistIsAllZeros(tileList, i); i++) {
            while (tileList.get(i).getValue() == 0) {
                slide(tileList, i);
            }
        }
    }

    /**
     * Sets each element's value to the value of the next element, $
     * nullifies the last element (because the list has been slid to the
     * given direction)
     *
     * @param tileList list of tiles to slide.
     * @param from     index from which to begin sliding.
     */
    private void slide(List<Tile> tileList, int from) {
        for (int i = from; i < tileList.size() - 1; i++) {
            tileList.get(i).setValue(tileList.get(i + 1).getValue());
        }
        tileList.get(tileList.size() - 1).nullify();
    }

    /**
     * Checks if the board is full, meaning none of the tiles present is of
     * zero value
     *
     * @return false if a tile is of value zero, true otherwise.
     */
    private boolean hasNoEmptyTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() == 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if the board is empty, meaning all of the tiles present are of
     * zero value.
     *
     * @return false if a tile is of non-zero value, true otherwise.
     */
    public boolean allTilesAreEmpty() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() != 0)
                    return false;
            }
        }
        return true;
    }

    /**
     * Checks if it's still possible to play.
     * It's impossible to play when the board is full and there are no
     * neighbours that are equal.
     *
     * @return true if game is still possible, false otherwise.
     */
    public boolean playStillPossible() {
        return !hasNoEmptyTiles() || !noNeighboursAreEqual();
    }

    /**
     * Gets the highest value currently present in the board.
     *
     * @return the highest value in the board.
     */
    public int getHighestValue() {
        int max = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tiles[i][j].getValue() > max) max = tiles[i][j].getValue();
            }
        }
        return max;
    }

    /**
     * Fuses the elements in the list when possible,
     * and adds the value of the pre-fused tile to the score of the player.
     * It does so by checking if the element of the list can be fused with
     * the next element, if so, fuses the two, and slides the list again to
     * account for the new zero.
     *
     * @param tileList list of tiles to fuse when possible.
     * @param player   player to which to the score is added.
     */
    private void fuseTilesInList(List<Tile> tileList, Player player) {
        for (int i = 0; i < tileList.size() - 1; i++) {
            if (tileList.get(i).canFuseWith(tileList.get(i + 1))) {
                player.addToScore(tileList.get(i + 1).getValue());
                tileList.get(i).fuseWith(tileList.get(i + 1));
                slide(tileList, i + 1);
            }
        }
    }

    /**
     * Checks if the sublist starting at 'from' to the end of the list
     * contains only zeroes.
     *
     * @param tileList list of tiles to check.
     * @param from     index where the sublist of tileList starts.
     * @return true if all remaining tiles are of zero value, false otherwise;
     */
    private boolean sublistIsAllZeros(List<Tile> tileList, int from) {
        List<Tile> remainingTiles = new ArrayList<>();
        for (int j = from; j < tileList.size(); j++) {
            remainingTiles.add(tileList.get(j));
        }
        return (allAreZeros(remainingTiles));
    }

    /**
     * Check if all tiles in a list are zeros.
     *
     * @param tileList list of tiles to check.
     * @return true if all the tiles in the list are zeroes, false otherwise.
     */
    private boolean allAreZeros(List<Tile> tileList) {
        for (Tile t :
                tileList) {
            if (t.getValue() != 0) return false;
        }
        return true;
    }

    /**
     * Checks if there are no neighbours of equal value in the board.
     * For each tile in the board, checks if the tile immediately to the
     * right or the tile immediately down
     * are of equal value to the til in question, if so returns false.
     *
     * @return false if a tile has a neighbour of equal value, true otherwise.
     */
    private boolean noNeighboursAreEqual() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (j < 3) {
                    if (tiles[i][j].getValue() == tiles[i][j + 1].getValue())
                        return false;
                }
                if (i < 3) {
                    if (tiles[i][j].getValue() == tiles[i + 1][j].getValue())
                        return false;
                }
                if (j > 0) {
                    if (tiles[i][j].getValue() == tiles[i][j - 1].getValue())
                        return false;
                }
                if (i > 0) {
                    if (tiles[i][j].getValue() == tiles[i - 1][j].getValue())
                        return false;
                }
            }
        }
        return true;
    }

    /**
     * Rollbacks the board to the previous state.
     *
     * @return True if the board has changed with this undo, false otherwise.
     */
    public boolean rollBackTiles() {
        if (!areEqualValues(tiles, previousTiles)) {
            this.tiles = previousTiles;
            return true;
        }
        return false;
    }
}
