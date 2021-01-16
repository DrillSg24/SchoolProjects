package Model;

/**
 *
 * @author Ihab Tazi 55130.
 */
public interface Model {

    Deck getCurrentDeck();

    int getRoundWinnerIndex(int cpuTotal, int humanTotal);

    HumanPlayer getHumanPlayer();

    Cpu getCpu();

    void nextRound();

    int getRound();

    boolean isAPlayerOver21();

    void addMoneyToWinner(int money);

    void removeMoneyFromLoser(int money);

    void initGame(Player[] players, Deck deck, int bet);

    boolean deckStillHasCards();

    int getBet();
    
    void setBet(int bet);

    boolean playerIsBroke(Player player);

    boolean playerIsBlocked();

    int getHumanScore();

    int getCpuScore();

    int getHumanCurrentTotal();

    int getCpuCurrentTotal();

    boolean bothHaveSameTotal();

    Player getRoundLoser(int cpuTotal, int humanTotal);

    Player getRoundWinner(int cpuTotal, int humanTotal);

    Card cpuDrawsCard();

    Card playerDrawsCard();

    boolean isCpuUnderLimit();
    
    void blockHuman();

}
