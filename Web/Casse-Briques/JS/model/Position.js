/**
 * Position class, represents the position of the ball in the game.
 */
class Position {

    /**
     * Constructor of the position. A position is defined
     * by its coordinates.
     * 
     * @param {number} x the distance from the left side of the game panel.
     * @param {number} y the distance from the top side of the game panel.
     */
    constructor(x, y) {
        this._x = x;
        this._y = y;
    }

    /**
     * Simple getter for the current x position of the ball.
     */
    get x() {
        return this._x;
    }

    /**
     * Simple getter for the current y position of the ball.
     */
    get y() {
        return this._y;
    }

    /**
     * Simple setter for the x position of the ball.
     * 
     * @param {number} valueX the new x position.
     */
    set x(valueX) {
        this._x = valueX;
    }

    /**
     * Simple setter of the y position of the ball.
     * 
     * @param {number} valueY the new y position.
     */
    set y(valueY) {
        this._y = valueY;
    }
}