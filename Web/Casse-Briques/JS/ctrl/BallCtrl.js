/**
 * Controller for the ball.
 * Gives methods to start/Stop/change the ball's movement.
 */
class BallCtrl {

    /**
     * Constructor of the ball controller.
     * @param {Game} game the game. 
     * @param {View} view the view.
     * @param {GameCtrl} gameCtrl the game controller, to stop and resume the game.
     */
    constructor(game, view, gameCtrl) {
        this._game = game;
        this._view = view;
        this._gameCtrl = gameCtrl;
    }

    /**
     * Starts the ball.
     */
    start() {
        this._moveListener = setInterval(() => this.move(), 10);
    }

    /**
     * Moves the ball a step (defined by its own movement) and updates the view.
     * Also removes every brick touched by the ball from the game.
     */
    move() {
        let bricksTouched = this._game.ballMove();
        bricksTouched.forEach(brick => {
            view.remove(brick);
        });
        this._view.update(this._game.ball);
        this._view.updateScore(this._game.player.score);
        if (this._game.isLevelWon()) {
            if (this._game.level == 10) {
                this._gameCtrl.gameIsWon();
            } else {
                this._gameCtrl.startNextLevel();
            }
        } else if (this._game.hasBallFallen()) {
            this._game.player.removeLife();
            if (this._game.hasBallFallen_stillAlive()) {
                this._gameCtrl.stillAlive();
            } else if (this._game.hasBallFallen_noLivesLeft()) {
                this._gameCtrl.noLivesLeft();
            }
        }
    }

    /**
     * Stops the ball.
     */
    stop() {
        clearInterval(this._moveListener);
    }

    /**
     * Method to move the ball to the position of the paddle.
     */
    moveBallToPaddle() {
        this._game.ball.moveTo(new Position(this._game.paddle.getLeftX() + paddleWidth / 2 - ballWidth / 2, this._game.paddle.getTopY() - ballHeight));
        this._view.update(this._game.ball);
    }




}