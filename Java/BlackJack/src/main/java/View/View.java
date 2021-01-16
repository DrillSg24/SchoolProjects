package View;

import Model.Card;
import Model.Deck;
import Model.Player;
import java.util.Scanner;

/**
 * The view class, responsible for everything that displays to the console.
 *
 * @author Ihab Tazi 55130.
 */
public class View implements InterfaceView {

    /**
     * Method to display a card, using the toString() method of the Card.
     *
     * @param player player who drew the card.
     * @param card card to display
     */
    @Override
    public void displayCard(Player player, Card card) {
        displayMessage(player.toString() + " has drawn \n"
                + card + "\n");
    }

    /**
     * Display all the information of this round in its current state.
     *
     * @param round the round being played
     * @param cpu the bank player
     * @param human the human player
     * @param deck the current deck
     */
    @Override
    public void displayCurrentGame(int round, Player cpu, Player human, Deck deck) {
        displayMessage("Round " + round + " : " + cpu + " has "
                + cpu.getScore() + "€, " + human + " has "
                + human.getScore() + "€");
        int cpuTotal = cpu.getCurrentTotal();
        int humanTotal = human.getCurrentTotal();
        System.out.println(cpu + " " + cpuTotal
                + "   |||||   " + human + " " + humanTotal
                + "   |||||   Cards left : " + deck.getSize());

    }

    /**
     * Method to ask the user for a String.
     *
     * @param msg message to ask the user.
     * @return the entered String.
     */
    private String getString(String msg) {
        try {
            Scanner key = new Scanner(System.in);
            Thread.sleep(1000);
            System.out.println(msg);
            return key.nextLine();
        } catch (InterruptedException ex) {
            displayError(ex.getMessage());
            return null;
        }
    }

    /**
     * Method to ask the user for the name of the human player. Uses getString()
     *
     * @return the name of the human player as given by the user as a String.
     */
    @Override
    public String askHumanName() {
        return getString("Enter your name : ");
    }

    /**
     * General question to ask the user for a "yes/no" question. Keeps asking
     * the user as long as he doesn't enter one of the following : yes/y/no/n.
     * NOT Case sensitive.
     *
     * @param msg Message to ask the user.
     * @return true if the user entered yes/y, false if he entered no/n.
     */
    @Override
    public boolean askYesOrNo(String msg) {
        String response = getString(msg);
        while (!response.toLowerCase().matches("[yn]")
                && !response.toLowerCase().equals("yes")
                && !response.toLowerCase().equals("no")) {
            System.out.println("Y/y/yes or N/n/no only...");
            response = getString(msg);
        }
        return response.toLowerCase().equals("y")
                || response.toLowerCase().equals("yes");
    }

    /**
     * Method to display a message in a slow paced way (1 second of delay), so
     * the user gets to actually view the message slowly. USES Thread.sleep()
     * hence the potential InterruptedException.
     *
     * @param msg Message to display.
     */
    @Override
    public void displayMessage(String msg) {
        try {
            System.out.println("");
            Thread.sleep(1000);
            System.out.println("<<<< " + msg + " >>>>");
            System.out.println("");
        } catch (InterruptedException ex) {
            displayError(ex.getMessage());
        }
    }

    /**
     * Method to print the errors to the standard error output instead of the
     * standard out output.
     *
     * @param errorMsg message to display for the error
     */
    @Override
    public void displayError(String errorMsg) {
        System.err.println("ERROR : " + errorMsg);
    }

    /**
     * Display the final state of all the information of the game.
     *
     * @param round the last round
     * @param cpu the bank player
     * @param human the human player
     */
    @Override
    public void displayFinalScores(int round, Player cpu, Player human) {
        displayMessage("The game lasted for " + round + " rounds.");
        displayMessage("The final scores are :");
        System.out.println(cpu + " : " + cpu.getScore()
                + "€   |||||   " + human + " : " + human.getScore() + "€");
    }

    /**
     * Asks the user for an integer. Keeps asking a valid integer is given.
     *
     * @param msg Message to ask the user with.
     * @return the integer the user entered.
     */
    private int getInteger(String msg) {
        Scanner key = new Scanner(System.in);
        System.out.print(msg);
        while (!key.hasNextInt()) {
            key.next();
            System.out.println("That is not an integer.");
            System.out.print(msg);
        }
        return key.nextInt();
    }

    /**
     * Asks the user for the amount of money to bet for a given round. The bet
     * made must be positive. the bet is later checked in the controller against
     * the human current money to determine if it's possible to place this bet.
     *
     * @return the bet the user proposes.
     */
    @Override
    public int askBet() {
        int response = getInteger("Enter your bet : ");
        while (response <= 0) {
            System.out.println("Bet must be more than 0 ");
            response = getInteger("Enter your bet :");
        }
        return response;
    }

    /**
     * Displays the message when the player and the bank have drawn.
     */
    @Override
    public void showDraw() {
        displayMessage("It's a draw ! Both players keep their money.");
    }

}
