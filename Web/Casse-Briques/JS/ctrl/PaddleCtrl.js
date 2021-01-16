/**
 * Controller for the paddle movement.
 * It makes it follow the mouse.
 */
class PaddleCtrl {
    /**
     * Display the paddle and starts controlling it with the mouse
     * 
     * @param {Game} game : the game
     * @param {View} view : the view
     */
    constructor(game, view) {
        $(document).mousemove(evt => this.moveMouse(game, view, evt));
    }

    /**         
     * Called when the mouse is moved.
     * It moves the paddle (horizontally) to where the mouse is.
     * 
     * @param {Game} game : the game 
     * @param {View} view: the view
     * @param {MouseEvent} evt : the mouse event
     **/
    moveMouse(game, view, evt) {
        game.paddleMove(this.getMouseX(evt) - view.sceneLeft());
        view.update(game.paddle);
    }

    /**
     * Gets the current x position of the mouse.
     * @param {MouseEvent} evt a mouse event.
     * @return {number} the x position of the mouse.
     */
    getMouseX(evt) {
        return evt.clientX;
    }
}