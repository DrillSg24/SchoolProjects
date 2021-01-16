/**
 * Dimension class, contains the different elements 
 * that make up the dimensions of an object.
 */
class Dimension {

    /**
     * Constructor for a dimension object.
     * @param {number} width width of the object
     * @param {number} height height of the object
     */
    constructor(width, height) {
        this._width = width;
        this._height = height;
    }

    /**
     * Simple getter for the width
     */
    get width() {
        return this._width;
    }

    /**
     * Simple getter for the height
     */
    get height() {
        return this._height;
    }
}