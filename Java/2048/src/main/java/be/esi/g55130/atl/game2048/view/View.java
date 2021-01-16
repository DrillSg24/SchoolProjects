package be.esi.g55130.atl.game2048.view;

import be.esi.g55130.atl.game2048.controller.ControllerFX;
import be.esi.g55130.atl.game2048.model.Direction;
import be.esi.g55130.atl.game2048.model.Model;
import be.esi.g55130.atl.game2048.model.Tile;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Class representing the view of the game. !Implements InterfaceView!
 *
 * @author Ihab Tazi 55130 - ESI.
 */

public class View implements InterfaceView {


    /**
     * Displays the current state of the game.<br>
     * 1) Displays the current Score<br>
     * 2) Displays the current board.
     *
     * @param game the Game to display
     */
    @Override
    public void displayGame(Model game) {
        displayScore(game);
        displayBoard(game);
    }

    /**
     * Asks the user for a direction.<br>
     * Keeps asking while the given string does not correspond to a valid
     * direction string. (calling isDirectionStringValid).
     *
     * @return the Direction corresponding to the given direction string.
     */
    @Override
    public Direction askDirection() {
        String directionString =
                askString("Next direction ? (or type exit to quit)")
                        .trim().toLowerCase();
        while (!isDirectionStringValid(directionString)) {
            displayMessage("Invalid direction ! Please try again...");
            directionString =
                    askString("Next direction ? (or type exit to quit)")
                            .trim().toLowerCase();
        }
        switch (directionString) {
            case "up":
            case "8":
            case "z":
                return Direction.UP;
            case "down":
            case "2":
            case "s":
                return Direction.DOWN;
            case "left":
            case "4":
            case "q":
                return Direction.LEFT;
            case "right":
            case "6":
            case "d":
                return Direction.RIGHT;
            case "exit":
                this.displayMessage("Game was stopped, thank you for " +
                        "playing 2048 !");
                System.exit(0);
            default:
                return null;
        }
    }

    /**
     * Displays a simple formatted message.
     *
     * @param msg Message to display.
     */
    @Override
    public void displayMessage(String msg) {
        System.out.println("<<<<<<<< " + msg + " >>>>>>>>");
    }

    /**
     * Displays a hint for the different commands.
     */
    @Override
    public void displayHint() {
        this.displayMessage("For UP : Write one of 8, z and up.....\n" +
                "For LEFT : Write one of 4, q and left.....\n" +
                "For DOWN : Write one of 2, s and down.....\n" +
                "For RIGHT : Write one of 6, d and right.....\n");
    }

    /**
     * Displays a message to the error output
     *
     * @param errorMessage error message to display.
     */
    @Override
    public void displayError(String errorMessage) {
        System.err.println("!!! " + errorMessage + " !!!");
    }

    @Override
    public void linkToMC(ControllerFX controllerFX, Model game) {

    }

    @Override
    public String askPlayerName() {
        String response = askString("What is the player's name ?");
        return response.isBlank() ? "Nemo" : response;
    }

    /**
     * Asks the user for a string.
     *
     * @param message Message to display to the user
     * @return the given String.
     */
    private String askString(String message) {
        Scanner key = new Scanner(System.in);
        displayMessage(message);
        return key.nextLine();
    }

    /**
     * Displays the score in the game.
     *
     * @param game the game in which to fetch the score of the player.
     */
    private void displayScore(Model game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null!");
        }
        System.out.println(game.getPlayerName() + "'s current score : "
                + game.getPlayerScore());
    }

    /**
     * Displays the current tiles in the game.
     *
     * @param game the game in which to fetch the tiles.
     */
    private void displayBoard(Model game) {
        if (game == null) {
            throw new IllegalArgumentException("Game cannot be null!");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append("|");
            for (int j = 0; j < 4; j++) {
                Tile currentTile = game.getTileAt(i, j);
                sb.append(formattedTileString(currentTile)).append("|");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }


    /**
     * Checks if the string in parameter is a valid direction string.
     * Valid string are contained in an array.
     *
     * @param stringToCheck String to check the validity of.
     * @return true if the string is a valid direction string, false otherwise.
     */
    private boolean isDirectionStringValid(String stringToCheck) {
        String[] validStrings = new String[]{"up", "down", "left", "right"
                , "6", "8", "4", "2"
                , "z", "q", "s", "d"
                , "exit"};
        return Arrays.asList(validStrings).contains(stringToCheck);
    }

    /**
     * Formats the tile by trying to center it to 6 characters no matter how
     * big the value is (max 9999).
     *
     * @param tile Tile to format.
     * @return a formatted string, with the value of the tile centered, a
     * blank space if the value is 0, "null" if the tile is null.
     */
    private String formattedTileString(Tile tile) {
        if (tile == null) {
            return " null ";
        } else if (tile.getValue() > 9999) {
            throw new IllegalArgumentException("Tile value exceeds the " +
                    "4-character long limit");
        }
        String s = tile.getValue() == 0 ? "" : String.valueOf(tile.getValue());
        return 6 > s.length()
                ? " ".repeat((6 - s.length()) / 2)
                + s + " ".repeat((6 - s.length() + 1) / 2)
                : s;
    }

    /**
     * Called when a game has notified about a change.
     *
     * @param game The game to adapt according to.
     */
    @Override
    public void update(Model game) {
        this.displayGame(game);
    }
}
