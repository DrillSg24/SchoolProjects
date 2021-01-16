/**
 * Game class, main game model. Contains all the elements in the game (Paddle
 * , ball, ...).
 * This is used to access the model classes.
 */
class Game {

    /**
     * Constructor of a game.
     */
    constructor() {
        this._paddle = new Paddle("paddleID", 100);
        this._ball = new Ball("ballID", new Position(this._paddle.getLeftX() + paddleWidth / 2 - ballWidth / 2, this._paddle.getTopY() - ballHeight),
            getRandomMovement_AlwaysUP());
        this._wall = this.getNewBrickWall(1);
        this._player = new Player(0, initialLives);
        this._level = 1;
    }

    /**
     * Simple getter for the ball in the game.
     */
    get ball() {
        return this._ball;
    }

    /**
     * Simple getter for the paddle in the game.
     */
    get paddle() {
        return this._paddle;
    }

    /**
     * Simple getter for the wall of bricks in the game.
     */
    get wall() {
        return this._wall;
    }

    /**
     * Simple getter for the player in the game.
     */
    get player() {
        return this._player;
    }

    get level() {
        return this._level;
    }

    /**
     * Moves the paddle to given x position
     * @param {number} centerX new x position for the paddle. 
     */
    paddleMove(centerX) {
        this._paddle.moveTo(centerX);
    }

    /**
     * Moves the ball.
     */
    ballMove() {
        this._ball.move();
        this.checkIfPaddleCollision();
        return this.checkIfBricksCollision();
    }

    /**
     * Checks if the ball hits the ball, if so, it makes it bounce off it.
     */
    checkIfPaddleCollision() {
        if (this._ball.checkBallCollisionWith(this._paddle)) {
            this._ball.hitPaddle();
        }
    }

    /**
     * Checks if the ball is hitting any bricks and reverses 
     * the movement if that's the case.
     * @return {Brick[]} the array of bricks touched by the ball.
     */
    checkIfBricksCollision() {
        let conditionReverseY = false;
        let conditionReverseX = false;
        let bricksTouched = [];
        let i = 0;
        while (i < this._wall.length && bricksTouched.length <= 2) {
            if (this._ball.checkBallCollisionWith(this._wall[i])) {
                playBrickSound();
                if (this._ball.isCollisionOnTheTops(this._wall[i])) {
                    conditionReverseY = true;
                };
                if (this._ball.isCollisionOnTheSides(this._wall[i])) {
                    conditionReverseX = true;
                }
                bricksTouched.push(this._wall[i]);
                this.removeFromWall(this._wall[i]);
                i--;
                this._player.addToScore(scorePerBrick);
            }
            i++;
        }
        if (conditionReverseY) {
            this._ball.movement.reverseY();
        } else if (conditionReverseX) {
            this._ball.movement.reverseX();
        }
        return bricksTouched;
    }

    /**
     * Constructs an array of bricks that serve as the wall in the game.
     */
    getNewBrickWall(level) {
        let wall = [];
        let brickDimension = new Dimension(brickWidth, brickHeight);
        let thisLevel = getLevelArray(level)
        for (let row = 0; row < thisLevel.length; row++) {
            for (let column = 0; column < thisLevel[0].length; column++) {
                if (thisLevel[row][column]) {
                    let thisPosition = new Position(column * brickWidth, 50 + row * brickHeight);
                    let thisId = "brickRow" + (row + 1) + "Col" + (column + 1);
                    let thisBrick = new Brick(thisId, "Brick", thisPosition, brickDimension);
                    wall.push(thisBrick);
                }
            }
        }
        return wall;
    }

    /**
     * Physically removes the given brick from the brick wall.
     * @param {Brick} brick brick to remove.
     */
    removeFromWall(brick) {
        this._wall.splice(this._wall.indexOf(brick), 1);
    }

    /**
     * Sets the level to the next level in the game. 
     * If the level is 10 (the last level), keeps on 10.
     */
    nextLevel() {
        this._level++;
        if (this._level > 10) {
            this._level = 10;
        }
        return this._level;
    }

    /**
     * Check if the game is failed. 
     * A game is failed when the player has no lives left.
     */
    isGameFailed() {
        return this._player.remainingLives == 0;
    }

    /**
     * Checks if the level is passed.
     * A level is passed when there are no bricks left on the wall.
     */
    isLevelWon() {
        return this._wall.length === 0;
    }

    /**
     * Checks if the ball fell but the player still has lives left.
     */
    hasBallFallen_stillAlive() {
        return this.hasBallFallen() && !this.isGameFailed();
    }

    /**
     * Checks if the ball fell and the player has no lives left.
     */
    hasBallFallen_noLivesLeft() {
        return this.hasBallFallen() && this.isGameFailed();
    }

    /**
     * Checks if the ball fell.
     * A ball falls if its bottom y coordinates surpasses the game pane height.
     */
    hasBallFallen() {
        return this._ball.getBottomY() >= gameHeight;
    }

    /**
     * Gets a new Ball and restarts the player with the same score, and the new lifeCount.
     */
    tryAgain() {
        this._ball = this.getNewBall_SameMovement();
        this._player = this.getNewPlayer(this._player.score, this._player.remainingLives);
    }

    /**
     * Restarts the game by loading level 1, and resetting the score and liveCount to the initial value.
     */
    restartGame() {
        this._ball = this.getNewBall();
        this._player = this.getNewPlayer(0, initialLives);
        this._wall = this.getNewBrickWall(1);
        this._level = 1;
    }

    /**
     * Sets the game up for the next level. 
     * Adds one life to the player (capped at 4 lives maximum).
     */
    startNextLevel() {
        this._ball = this.getNewBall();
        this._player.addLife();
        if (this._player.remainingLives > 4) {
            this._player.removeLife();
        }
        this._player = this.getNewPlayer(this._player.score, this._player.remainingLives);
        this._level = this.nextLevel();
        this._wall = this.getNewBrickWall(this._level);
    }

    /**
     * Gets a new player with given score and life count.
     * @param {integer} score Score of the new player instance
     * @param {integer} lives lifeCOunt of the new player instance
     */
    getNewPlayer(score, lives) {
        return new Player(score, lives);
    }

    /**
     * Gets a new ball with a random movement, but always on top of the paddle.
     */
    getNewBall() {
        return new Ball("ballID", this._ball.getPositionJustOver(this._paddle),
            getRandomMovement_AlwaysUP());
    }

    getNewBall_SameMovement() {
        return new Ball("ballID", this._ball.getPositionJustOver(this._paddle),
            this._ball.movement);
    }
}