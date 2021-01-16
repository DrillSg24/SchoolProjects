/**
 * Paddle object
 */
class Paddle extends Sprite {
    /**
     * Constructor of a paddle with an id and the initial left position.
     * i.e: the initial spacing from the left border
     * of the game panel.
     * @param {string} id the id for the paddle.
     * @param {Integer} left the spacing from the left border.
     */
    constructor(id, left) {
        super(id, "Paddle", new Position(left, gameHeight - paddleHeight),
            new Dimension(paddleWidth, paddleHeight));
    }

    /**
     * Pseudo-setter for the left attribute. Adjusts to the center 
     * and then moves the paddle to the given value,
     * Takes care of not getting out of bounds.
     * 
     * @param {number} val the new value for left.
     */
    moveTo(val) {
        let value = this.adjustToCenterOfPaddle(val);
        if (value < 0) {
            value = 0
        } else if (value > maximumLeft) {
            value = maximumLeft;
        }
        this.topLeft.x = value;
    }

    /**
     * Adjusts the given value to be followed 
     * on the center of the paddle, not on the left border.
     * 
     * @param {number} val 
     */
    adjustToCenterOfPaddle(val) {
        return (val - paddleWidth / 2);
    }


}