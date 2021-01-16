/**
 * Constructor for the sprite class. Sprites are objects of the game with common 
 * attributes like dimesions and positions.
 * Current sprites in the game are Paddle, Ball, Bricks. 
 */
class Sprite {

    /**
     * @param {string} id the id of the sprite
     * @param {string} type the type of the sprite (ball, paddle, brick...)
     * @param {Position} topLeft the position of the top left corner
     * @param {Dimension} dimension the dimesnions of the object.
     */
    constructor(id, type, topLeft, dimension) {
        this._id = id;
        this._type = type;
        this._topLeft = topLeft;
        this._dimension = dimension;
    }

    /**
     * Simple getter for the id of the sprite. 
     */
    get id() {
        return this._id;
    }

    /**
     * Simple getter for the type of the sprite.
     */
    get type() {
        return this._type
    }

    /**
     * Simple getter for the topLeft position.
     */
    get topLeft() {
        return this._topLeft;
    }

    /**
     * Simple getter for the dimensions.
     */
    get dimension() {
        return this._dimension;
    }

    /**
     * Gets the x of the middle of the object.
     */
    getCenterX() {
        return this._topLeft.x + this._dimension.width / 2;
    }

    /**
     * Gets the y of the middle of the object.
     */
    getCenterY() {
        return this._topLeft.y + this._dimension.height / 2;
    }

    /**
     * Gets the x of the right border of the object.
     */
    getRightX() {
        return this._topLeft.x + this._dimension.width;
    }

    /**
     * Gets the x of the left border of the object.
     */
    getLeftX() {
        return this._topLeft.x;
    }

    /**
     * Gets the y of the bottom border of the object.
     */
    getBottomY() {
        return this._topLeft.y + this._dimension.height;
    }

    /**
     * Gets the y of the top border of the object.
     */
    getTopY() {
        return this._topLeft.y;
    }

    /**
     * Gets the position of the top right corner of the Sprite
     */
    getTopRightPosition() {
        return new Position(this.getRightX(), this.getTopY());
    }

    /**
     * Gets the position of the bottom right corner of the Sprite
     */
    getBottomRightPosition() {
        return new Position(this.getRightX(), this.getBottomY());
    }

    /**
     * Gets the position of the bottom left corner of the Sprite
     */
    getBottomLeftPosition() {
        return new Position(this.getLeftX(), this.getBottomY());
    }

    /**
     * Gets the position of the bottom on the middle of the Sprite
     */
    getBottomMiddlePosition() {
        return new Position(this.getCenterX(), this.getCenterY());
    }

    /**
     * Gets the width of the sprite.
     */
    getWidth() {
        return this._dimension.width;
    }

    /**
     * Gets the height of the sprite.
     */
    getHeight() {
        return this._dimension.height;
    }

    /**
     * Checks if this sprite is in collision with another sprite.
     * @param {Spirte} sprite The other sprite to check collision with.
     */
    checkCollisionWith(sprite) {
        return this.getLeftX() <= sprite.getRightX() &&
            this.getRightX() >= sprite.getLeftX() &&
            this.getTopY() < sprite.getBottomY() &&
            this.getBottomY() > sprite.getTopY();
    }

    /**
     * Checks if this ball sprite specifically is in collision with another sprite.
     * Provides a more precise method for circular objects than just edge detection. 
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    checkBallCollisionWith(sprite) {
        var closestX = getClosestX(this, sprite);
        var closestY = getClosestY(this, sprite);
        var distanceX = this.getCenterX() - closestX;
        var distanceY = this.getCenterY() - closestY;
        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) <
            Math.pow(this.getWidth() / 2, 2);
    }

    /**
     * Checks if the collision occurred on the left
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheLeft(sprite) {
        var closestX = getClosestX(this, sprite);
        return closestX == sprite.getLeftX();
    }

    /**
     * Checks if the collision occurred on the right
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheRight(sprite) {
        var closestX = getClosestX(this, sprite);
        return closestX == sprite.getRightX();
    }

    /**
     * Checks if the collision occurred on the bottom 
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheBottom(sprite) {
        var closestY = getClosestY(this, sprite);
        return closestY == sprite.getTopY();
    }

    /**
     * Checks if the collision occurred on the top
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheTop(sprite) {
        var closestY = getClosestY(this, sprite);
        return closestY == sprite.getBottomY();
    }

    /**
     * Checks if the collision occurred on either side of the other sprite.
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheSides(sprite) {
        return this.isCollisionOnTheLeft(sprite) ||
            this.isCollisionOnTheRight(sprite);
    }

    /**
     * Checks if the collision occurred either on the top or the bottom of the other sprite
     * @param {Sprite} sprite The other sprite to check collision with.
     */
    isCollisionOnTheTops(sprite) {
        return this.isCollisionOnTheBottom(sprite) || this.isCollisionOnTheTop(sprite);
    }

    /**
     * Moves the sprite to the given position.
     * @param {Position} position the position to move to.
     */
    moveTo(position) {
        this._topLeft = position;
    }

    /**
     * Returns the position for this sprite to be just over the other sprite in the middle.
     * Useful for when the ball needs to stick on toop of the paddle.
     * @param {Sprite} sprite the sprite to get the position on top of.
     */
    getPositionJustOver(sprite) {
        return new Position(sprite.getCenterX() + sprite.getWidth() / 2 - this.getWidth() / 2, sprite.getTopY() - this.getHeight());
    }
}