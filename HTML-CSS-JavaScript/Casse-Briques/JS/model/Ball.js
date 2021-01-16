/**
 * Ball class. A ball is represented by its position (here the position of the 
 * top left corner), and its movement.
 */
class Ball extends Sprite {

    /**
     * Constructor of a ball. 
     * Instanciates a ball with a given id, position and movement.
     * @param {string} id The ID for the ball.
     * @param {Position} topLeft The topLeft position of the ball.
     * @param {Movement} movement The movement of the ball.
     */
    constructor(id, topLeft, movement) {
        const ballDimension = new Dimension(ballWidth, ballHeight);
        super(id, "Ball", topLeft, ballDimension);
        this._movement = movement;
    }

    /**
     * Simple getter for the movment of the ball.
     * 
     * @return {Movement} the movement of the ball.
     */
    get movement() {
        return this._movement;
    }

    /**
     * Move the ball, simply adds deltaX/Y to the current x/y coordinates.
     */

    move() {
        this._topLeft.x = this.moveAndKeepInsideX();
        this._topLeft.y = this.moveAndKeepInsideY();
    }

    /**
     * Checks the x position of the ball, if hits a wall, the deltaX is reversed.
     * @return the new position x to set.
     */
    moveAndKeepInsideX() {
        let currentX = this.getLeftX();
        let deltaX = this._movement.deltaX;
        if (currentX + deltaX < 0 || currentX + deltaX > maximumFromLeft) {
            playWallSound();
            this._movement.reverseX();
        }
        return currentX + deltaX;
    }

    /**
     * Checks the y position of the ball, if hits a wall, the deltaX is reversed.
     * @return the new position y to set.
     */
    moveAndKeepInsideY() {
        let currentY = this.getTopY();
        let deltaY = this._movement.deltaY;
        if (currentY + deltaY < 0 || currentY + deltaY > maximumFromTop) {
            playWallSound();
            this._movement.reverseY();
        }
        return currentY + deltaY;
    }

    /**
     * Reverses the vertical movement of the ball and offsets the ball to
     * just over the paddle to avoid being stuck in the collision loop.
     */
    hitPaddle() {
        playPaddleSound();
        this.moveToJustOverPaddle();
        this.addABitOfSpeed();
        this.movement.reverseY();
    }

    /**
     * Offsets the ball to just over the paddle, to avoid having a loop 
     * at the collision with the paddle.
     */
    moveToJustOverPaddle() {
        this._topLeft.y = gameHeight - paddleHeight - ballHeight;
    }

    /**
     * Accelerates the given delta by the value specified in the variables file.
     * @param {integer} currentDelta the delta to accelerate.
     */
    getAcceleratedSpeed(currentDelta) {
        let newSpeed = currentDelta * accelerationFactor;
        if (Math.abs(newSpeed) > maxSpeed) {
            if (newSpeed > 0) {
                newSpeed = maxSpeed;
            } else {
                newSpeed = -maxSpeed;
            }
        }
        return newSpeed;
    }

    /**
     * Accelerates the ball by setting new accelerated deltaX and deltaY;
     */
    addABitOfSpeed() {
        let newSpeedX = this.getAcceleratedSpeed(this.movement.deltaX);
        let newSpeedY = this.getAcceleratedSpeed(this.movement.deltaY);
        this._movement = new Movement(newSpeedX, newSpeedY);
    }
}