/**
 * View class, takes care of the display of the different game elements.
 */
class View {

    /**
     * Adds the sprite to the game scene.
     * @param {Sprite} sprite The sprite to add.
     */
    add(sprite) {
        let gamePane = $("#gamePane");
        let newSpan = $("<span>");
        newSpan.attr('id', sprite.id);
        newSpan.attr('class', sprite.type);
        newSpan.css('width', sprite.getWidth() + 'px');
        newSpan.css('height', sprite.getHeight() + 'px');
        newSpan.css('left', sprite.getLeftX() + 'px');
        newSpan.css('top', sprite.getTopY() + 'px');
        newSpan.css('background-size', sprite.getWidth() +
            "px " + sprite.getHeight() + 'px');
        gamePane.append(newSpan);
    }

    /**
     * Adds an array of bricks to the game pane.
     * @param {Sprite[]} sprites The array of sprites to display. 
     */
    addAll(sprites) {
        sprites.forEach(sprite => {
            this.add(sprite);
        });
    }

    /**
     * Remove a sprite visually from the game. 
     * @param {Sprite} sprite The sprite to remove from the game pane.
     */
    remove(sprite) {
        $("#" + sprite.id).remove();
    }

    /**
     * Removes all the elements of the given array from the view.
     * @param {Sprite[]} sprites The array of sprites to remove.
     */
    removeAll(sprites) {
        sprites.forEach(sprite => {
            this.remove(sprite);
        });
    }

    /**
     * Updates the current position of the sprite.
     * @param {Sprite} sprite The sprite to update.
     */
    update(sprite) {
        let thisSpriteSpan = $("#" + sprite.id);
        thisSpriteSpan.css('left', sprite.getLeftX() + 'px');
        thisSpriteSpan.css('top', sprite.getTopY() + 'px');
    }

    /**
     * Returns the space between the game panel and the left 
     * border of the webpage.
     * 
     * @return {number} the space between the game Panel and the page.
     */
    sceneLeft() {
        return $("#gamePane").offset().left;
    }

    /**
     * Adds the score box to the game, with the initial 
     * score in params
     * @param {integer} score Score to begin the game with.
     */
    addScore(score) {
        let gamePane = $("#gamePane");
        let newScoreSpan = $("<span>");
        newScoreSpan.attr('id', "scoreBox");
        newScoreSpan.css('top', ScoreTopOffset + 'px');
        newScoreSpan.css('left', scoreLeftOffset + 'px');
        newScoreSpan.html("Score  " + score);
        gamePane.append(newScoreSpan);
    }

    /**
     * Updates the score box to the current score.
     * @param {integer} score new score to display
     */
    updateScore(score) {
        let scoreSpan = $("#scoreBox");
        scoreSpan.html("Score  " + score);
    }

    /**
     * Adds a lifeCount span to the game.
     * @param {integer} lives initial lives value.
     */
    addLifeCount(lives) {
        let gamePane = $("#gamePane");
        let newLifeCount = $("<span>");
        newLifeCount.attr('id', 'lifeCount');
        newLifeCount.css('top', lifeCountTopOffset + 'px');
        newLifeCount.css('left', lifeCountLeftOffset + 'px');
        let newLifeCountString = "Lives remaining : " + "❤️".repeat(lives);
        newLifeCount.html(newLifeCountString);
        gamePane.append(newLifeCount);
    }

    /**
     * Updates the lifeCount span in the game.
     * @param {integer} lives new life count to display.
     */
    updateLifeCount(lives) {
        let lifeCountSpan = $("#lifeCount");
        let lifeCountString = "Lives remaining : " + "❤️".repeat(lives);
        lifeCountSpan.html(lifeCountString);
    }

    /**
     * Adds a level span to the game.
     * @param {integer} level level to display.
     */
    addLevelSpan(level) {
        let gamePane = $("#gamePane");
        let newLevelSpan = $("<span>");
        newLevelSpan.attr('id', 'levelSpan');
        newLevelSpan.css('top', levelTopOffset + 'px');
        newLevelSpan.css('left', levelLeftOffset + 'px');
        newLevelSpan.html("Level " + level);
        gamePane.append(newLevelSpan);
    }

    /**
     * Updates the level span to the new level value.
     * @param {integer} level level to display.
     */
    updateLevelSpan(level) {
        let levelSpan = $("#levelSpan");
        levelSpan.html("Level " + level);
    }

    /**
     * Hides the message from the game.
     */
    hideText() {
        let span = $("#startText");
        span.css('visibility', 'hidden');
    }

    /**
     * Displays a message at the center of the screen.
     * @param {string} string message to display
     */
    displayMessage(string) {
        let span = $("#startText");
        let gamePane = $("#gamePane");
        span.css('visibility', 'visible');
        span.text(string);
        gamePane.append(span);
    }
}