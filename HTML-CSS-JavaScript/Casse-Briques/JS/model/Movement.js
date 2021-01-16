/**
 * Movement class. Represents both the direction 
 * as well as the speed of the ball.
 */
class Movement {

    /**
     * Constructor of the movement.
     * the direction is given by the three possibilities 
     * of the signs of deltaX, and deltaY (-,0,+) 
     * 2³ = 8 directions possible.
     * The bigger the absolute value of deltaX and deltaY is, the faster the 
     * movement will be. 
     * @param {number} deltaX 
     * @param {number} deltaY 
     */
    constructor(deltaX, deltaY) {
        this._deltaX = deltaX;
        this._deltaY = deltaY;
    }

    /**
     * Simple getter for the current deltaX.
     */
    get deltaX() {
        return this._deltaX;
    }

    /**
     * Simple getter for the current deltaY.
     */
    get deltaY() {
        return this._deltaY;
    }

    /**
     * Simple setter to change direction and/or the speed in the x coordinate.
     */
    set deltaX(newDeltaX) {
        this._deltaX = newDeltaX;
    }

    /**
     * Simple setter to change direction and/or the speed in the x coordinate.
     */
    set deltaY(newDeltaY) {
        this._deltaY = newDeltaY;
    }

    /**
     * Reverses the movement in the x coordinate.
     */
    reverseX() {
        this._deltaX = -this._deltaX;
    }

    /**
     * Reverses the movement in the y coordinate.
     */
    reverseY() {
        this._deltaY = -this._deltaY;
    }
}