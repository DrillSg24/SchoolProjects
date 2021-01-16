/**
 * Class representing a player.
 * A player has a score and a remaining lives counter.
 */
class Player {
    constructor(score, lives) {
        this._score = score;
        this._remainingLives = lives;
    }

    /**
     * Simple getter for the current score of the player.
     */
    get score() {
        return this._score;
    }

    /**
     * Simple getter for the remaining lives of the player.
     */
    get remainingLives() {
        return this._remainingLives;
    }

    /**
     * Adds the given amount of points to the player's score.
     * @param {integer} points points to add to the score.
     */
    addToScore(points) {
        this._score += points;
    }

    /**
     * Removes a life from the player.
     */
    removeLife() {
        this._remainingLives--;
    }

    /**
     * Adds a life to the player.
     */
    addLife() {
        this._remainingLives++;
    }
}