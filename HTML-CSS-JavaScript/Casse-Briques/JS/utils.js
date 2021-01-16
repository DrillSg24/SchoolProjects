/**
 * Returns a random integer in the specified range.
 * @param {number} min inferiour bound of the range.
 * @param {number} max superiour bound of the range.
 */
function getRandomInteger(min, max) {
    return Math.floor(Math.random() * (max - min)) + min;
}


/**
 * Returns a random Position to start with, always starts on the middle somewhere.
 */
function getRandomPosition() {
    let randomXPosition = getRandomInteger(ballWidth, gameWidth - ballWidth);
    let middleYPosition = gameHeight / 2 - ballHeight;
    return new Position(randomXPosition, middleYPosition);
}

/**
 * Returns a random Movement (direction and speed), speed is thresholded by the maxSpeed constant.
 * 
 */
function getRandomMovement_AlwaysUP() {
    let randomDeltaX = getRandomInteger(-2, 2);
    while (randomDeltaX == 0) {
        randomDeltaX = getRandomInteger(-2, 2);
    }
    let randomDeltaY = getRandomInteger(-2, -1);
    return new Movement(randomDeltaX, randomDeltaY);
}


/**
 * Returns the array of booleans to construct the wall with.
 * @param {integer} level level to load
 */
function getLevelArray(level) {
    switch (level) {
        case 1:
            return level1;
        case 2:
            return level2;
        case 3:
            return level3;
        case 4:
            return level4;
        case 5:
            return level5;
        case 6:
            return level6;
        case 7:
            return level7;
        case 8:
            return level8;
        case 9:
            return level9;
        case 10:
            return level10;
    }
}

/**
 * Returns the closest x in the rectangle to the ball. Useful to calculate distances afterwards.
 * @param {Sprite} circleSprite A circular sprite (the ball in our game)
 * @param {Sprite} rectangleSprite A rectangular sprite to check against.       
 */
function getClosestX(circleSprite, rectangleSprite) {
    var x = circleSprite.getCenterX();
    var min = rectangleSprite.getLeftX();
    var max = rectangleSprite.getRightX();
    if (x < min) {
        x = min;
    } else if (x > max) {
        x = max;
    }
    return x;
}
/**
 * Returns the closest y in the rectangle to the ball. Useful to calculate distances afterwards.
 * @param {Sprite} circleSprite A circular sprite (the ball in our game)
 * @param {Sprite} rectangleSprite A rectangular sprite to check against.       
 */
function getClosestY(circleSprite, rectangleSpirte) {
    var y = circleSprite.getCenterY();
    var min = rectangleSpirte.getTopY();
    var max = rectangleSpirte.getBottomY();
    if (y < min) {
        y = min;
    } else if (y > max) {
        y = max;
    }
    return y;
}

/**
 * Plays a small explosion sound, for when a brick is touched.
 */
function playBrickSound() {
    document.getElementById('brickAudio').play();
}

/**
 * Plays a sound for when the paddle is hit.
 */
function playPaddleSound() {
    document.getElementById('paddleAudio').play();
}

/**
 * Plays a bumping sound, for when a wall collision is present.
 */
function playWallSound() {
    document.getElementById('wallAudio').play();
}

/**
 * Plays the mission failed sound effect.
 */
function playFailMusic() {
    document.getElementById('missionFailed').play();
}