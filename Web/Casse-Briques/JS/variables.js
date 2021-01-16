/* Width of the Game panel */
const gameWidth = 960;
/* Height of the Game Panel */
const gameHeight = 600;
/* Paddle width */
const paddleWidth = 96;
/* Paddle Heigth */
const paddleHeight = 16;
/* Maximum value left of Paddle can be */
const maximumLeft = gameWidth - paddleWidth;
/* Ball width */
const ballWidth = 24;
/* Ball height */
const ballHeight = 24;
/* Maximum distance from the top for the ball */
const maximumFromTop = gameHeight - ballHeight;
/* Maximum  distance from the left of the panel for the ball */
const maximumFromLeft = gameWidth - ballWidth;
/*Maximum speed of the ball */
const maxSpeed = 4;
/*Number of rows for the wall*/
const wallRows = 8;
/*Width of the bricks*/
const brickWidth = 64;
/*Height of the bricks */
const brickHeight = 24;
/*Score box offset from the top*/
const ScoreTopOffset = 2;
/*Score box offset from the left*/
const scoreLeftOffset = gameWidth - 50;
/*Life counter box offset from the top*/
const lifeCountTopOffset = 2;
/*Life counter box offset from the left*/
const lifeCountLeftOffset = 2;
/*Level box offset from the top*/
const levelTopOffset = 2;
/*Level box offset from the left*/
const levelLeftOffset = gameWidth / 2 - 30;
/*Score to add if brick is hit*/
const scorePerBrick = 10;
/*Initial lives at the beginnig of the game*/
const initialLives = 3;
/**
 * Factor to accelerate the ball with every time it hits the paddle 
 * (1.05 means 105%)
 */
const accelerationFactor = 1.05;