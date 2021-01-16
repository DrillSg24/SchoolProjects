package View;

import Model.Card;
import Model.Deck;
import Model.Player;

/**
 *
 * @author Ihab Tazi 55130.
 */
public interface InterfaceView {

    void displayCard(Player player, Card card);

    void displayCurrentGame(int round, Player cpu, Player human, Deck deck);

    String askHumanName();

    boolean askYesOrNo(String msg);

    void displayMessage(String msg);

    void displayError(String errorMsg);

    void displayFinalScores(int round, Player cpu, Player human);

    int askBet();

    void showDraw();

}
