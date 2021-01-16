/**
 * Main controller for the game.
 */
class GameCtrl {
    /**
     * @param {Game} game the Game.
     * @param {View} view the view .
     */
    constructor(game, view) {
        this._game = game;
        this._view = view;
        this._paddleCtrl = new PaddleCtrl(game, view);
        this._ballCtrl = new BallCtrl(game, view, this);
        view.add(game.paddle);
        view.add(game.ball);
        view.addAll(game.wall);
        view.addScore(game.player.score);
        view.addLifeCount(game.player.remainingLives);
        view.addLevelSpan(game._level);
    }

    /**
     * Starts the game by waiting for a click.
     */
    play() {
        this.ballStartWait();
    }

    /**
     * Stops the ball.
     */
    stop() {
        this._ballCtrl.stop();
    }

    /**
     * Displays message in parameters and then waits for a click to start the ball.
     * The ball follows the paddle while waiting.
     */
    ballStartWait(string) {
        this._view.displayMessage(string);
        this._moveListener2 = setInterval(() => this._ballCtrl.moveBallToPaddle(), 10);
        $(document).mouseup(
            () => this.ballStart());
    }

    /**
     * Starts the ball, and thus the game proper.
     */
    ballStart() {
        clearInterval(this._moveListener2);
        $(document).off("mouseup");
        this._view.hideText();
        this._ballCtrl.start();
    }

    /**
     * Defines the behaviour when a new level is attained. 
     * Stops the ball, calls for the game to setup the new level and then displays it.
     */
    startNextLevel() {
        this.stop();
        this._game.startNextLevel();
        this.updateView();
        this.ballStartWait("You passed level " + (this._game.level - 1) +
            ", congratulations ! New Level, click to start...");
    }

    /**
     * Defines the behaviour when the game is won.
     * Stops the ball, shows the congratulations message and final score, and then waits for the click to restart the game.
     */
    gameIsWon() {
        this.stop();
        let finalScore = this._game.player.score;
        this._game.restartGame();
        this.updateView();
        this.ballStartWait("You finished the game with the final score of " + finalScore +
            ". Thank you so much for playing ! Click if you want to restart the game.");
    }

    /**
     * Defines the behaviour when the ball has fallen but the game isn't over yet.
     * Removes a life visually, stops the ball, sets the game up for another try and waits for the click to restart the ball. 
     */
    stillAlive() {
        this._view.updateLifeCount(this._game.player.remainingLives);
        this.stop();
        this._game.tryAgain();
        this._view.update(this._game.ball);
        this.ballStartWait("One less life ! Click to continue...");
    }

    /**
     * Defines the behaviour when the ball has fallen and the game is over.
     * Removes a life visually, stops the game, removes the current wall, resets the game to all the initial values 
     * and waits for the click to restart the game.
     */
    noLivesLeft() {
        this._view.updateLifeCount(this._game.player.remainingLives);
        this.stop();
        this._view.removeAll(this._game.wall);
        let finalScore = this._game.player.score;
        this._game.restartGame();
        this.updateView();
        playFailMusic();
        this.ballStartWait("Mission failed, we'll get 'em next time. " +
            "Your score was " + finalScore + ". Click to restart...");
    }

    /**
     * Updates the wall, the level,score and lives displays, called after setting up the game with new attributes.
     */
    updateView() {
        this._view.addAll(this._game.wall);
        this._view.updateLevelSpan(this._game.level);
        this._view.updateLifeCount(this._game.player.remainingLives);
        this._view.updateScore(this._game.player.score);
    }

}